package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public class Beggar extends RouteGoingGameObject {

    private int xDirection = 1;
    private int yDirection = 1;
    private boolean lastTurnHorizontal;
    private boolean lastTurnResult = true;
    private boolean hasBottle = false;
    private final int bottleCost = 40;
    private int money = 0;

    public Beggar(Position position, Position target) {
        super(position, target);
    }

    void setMoney(int money) {
        this.money = money;
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

    @Override
    public void doTurn() {
        if (hasBottle) {
            goRoute();
            return;
        }

        // walking algorithm
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
        base.addBeggar(this);
        hasBottle = false;
        money += 40;
        base.addBeggar(this);
        return Interaction.InteractionResult.RELEASE_VISITOR;
    }

    @Override
    Interaction.InteractionResult getAffected(Bottle bottle) {
        hasBottle = true;
        return Interaction.InteractionResult.REPLACE_HOST;
    }
}
