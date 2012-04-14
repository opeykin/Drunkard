package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

import java.util.LinkedList;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public class BottleBase extends CreatingGameObject {
    LinkedList<Beggar> beggars = new LinkedList<Beggar>();

    public BottleBase(Position creatingPosition) {
        super(creatingPosition);
    }

    public void addBeggar(Beggar beggar) {

        beggars.add(beggar);
    }

    @Override
    public Interaction.InteractionResult affect(AffectableGameObject gameObject) {
        return gameObject.getAffected(this);
    }
}
