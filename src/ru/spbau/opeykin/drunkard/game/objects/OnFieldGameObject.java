package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public abstract class OnFieldGameObject extends AffectableGameObject {
    Position position;

    OnFieldGameObject(Position position) {
        position.setHost(this);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
