package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public class Beggar extends RouteGoingGameObject {

    private int xDireсtion = 1;
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
            if (position.equals(target)) {
                money += bottleCost;
                hasBottle = false;
            } else {
                goRoute();
            }
            return;
        }

        // walking algorithm
        if (lastTurnResult) {
            lastTurnResult = step(xDireсtion, 0);
            lastTurnHorizontal = true;
        } else {
            if (lastTurnHorizontal) {
                xDireсtion = -xDireсtion;
                lastTurnResult = step(0, yDirection);
                lastTurnHorizontal = false;
            } else {
                yDirection = -yDirection;
                lastTurnResult = step(xDireсtion, 0);
                lastTurnHorizontal = true;
            }
        }
    }

    @Override
    public char getSymbol() {
        return 'z';
    }

    @Override
    Interaction.InteractionResult getAffected(Bottle bottle) {
        hasBottle = true;
        return Interaction.InteractionResult.REPLACE_HOST;
    }
}
