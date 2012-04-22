package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

import java.util.LinkedList;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public class BottleBase extends CreatingGameObject {
    private final LinkedList<Beggar> beggars = new LinkedList<Beggar>();

    public BottleBase(Position position, Position creatingPosition) {
        super(position, creatingPosition);
    }

    public void addBeggar(Beggar beggar) {
        beggars.add(beggar);
    }

    @Override
    public Interaction.InteractionResult affect(AffectableGameObject gameObject) {
        return gameObject.getAffected(this);
    }

    @Override
    public char getSymbol() {
        return 'Z';
    }

    @Override
    public void doTurn() {
        for (Beggar beggar : beggars) {
            if (beggar.getMoney() > 0) {
                beggar.decreaseMoney();
            }
        }

        while (beggars.size() > 0 &&
                beggars.peek().getMoney() <= 0 &&
                creatingPosition.isFree()) {
            beggars.poll().setPosition(creatingPosition);
        }
    }
}
