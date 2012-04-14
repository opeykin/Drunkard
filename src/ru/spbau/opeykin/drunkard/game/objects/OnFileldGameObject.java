package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public abstract class OnFileldGameObject extends AffectableGameObject {
    protected Position position;

    public OnFileldGameObject(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    protected Interaction interact() {
        return new Interaction(position);
    }

    public void destroy()
    {
        position = null;
    }
}
