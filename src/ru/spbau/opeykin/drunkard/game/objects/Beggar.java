package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;


/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public class Beggar extends RouteGoingGameObject {

    private int xDirection = 1;
    private int yDirection = -1;
    private boolean lastTurnHorizontal;
    private boolean lastTurnResult = true;
    private boolean hasBottle = false;
    private final int bottleCost;
    private int money = 0;


    public Beggar(Position position, Position target, int bottleCost) {
        super(position, target);
        this.bottleCost = bottleCost;
    }

    void setPosition(Position position) {
        this.position = position;
        position.setHost(this);
    }

    public int getMoney() {
        return money;
    }

    public void decreaseMoney() {
        if (money < 1) {
            throw new IllegalStateException(this.toString() + " has no money");
        }

        --money;
    }

    @Override
    public Interaction.InteractionResult affect(AffectableGameObject gameObject) {
        return gameObject.getAffected(this);
    }

    @Override
    protected void updateRoute() {
        route = getRoute(target);
    }

    /**
     * go through the entire field from left to right
     */
    @Override
    public void doTurn() {
        if (hasBottle) {
            goRoute();
            return;
        }

        if (lastTurnResult) {
            lastTurnResult = step(xDirection, 0);
            lastTurnHorizontal = true;
        } else {
            if (lastTurnHorizontal) {
                xDirection = -xDirection;
                lastTurnResult = step(0, yDirection);
                lastTurnHorizontal = false;
            } else {
                yDirection = -yDirection;
                lastTurnResult = step(xDirection, 0);
                lastTurnHorizontal = true;
            }
        }
    }

    @Override
    public char getSymbol() {
        return 'z';
    }

    @Override
    Interaction.InteractionResult getAffected(BottleBase base) {
        if (!hasBottle) {
            return Interaction.InteractionResult.KEEP_BOTH;
        }

        hasBottle = false;
        money += bottleCost;
        if (money > 0) {
            base.addBeggar(this);
            return Interaction.InteractionResult.RELEASE_VISITOR;
        } else {
            return Interaction.InteractionResult.KEEP_BOTH;
        }
    }

    @Override
    Interaction.InteractionResult getAffected(Bottle bottle) {
        hasBottle = true;
        return Interaction.InteractionResult.REPLACE_HOST;
    }
}
