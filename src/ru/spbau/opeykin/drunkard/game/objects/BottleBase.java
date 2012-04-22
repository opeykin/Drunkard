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
}
