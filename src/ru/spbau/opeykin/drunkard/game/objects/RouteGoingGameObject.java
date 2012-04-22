package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

import java.util.LinkedList;

/**
 * User: Alexander Opeykin
 * Date: 4/14/12
 */
public abstract class RouteGoingGameObject extends MovableGameObject {
    final Position target;
    LinkedList<Position> route;

    RouteGoingGameObject(Position position, Position target) {
        super(position);
        this.target = target;
    }

    protected abstract void updateRoute();

    void goRoute() {
        if (route == null || route.isEmpty()) {
            updateRoute();
        }

        if (route == null) {
            // no route exists for the moment
            return;
        }

        boolean isMoved = step(route.getFirst());
        if (!isMoved) {
            updateRoute();
        }
    }

    @Override
    public Interaction.InteractionResult askToAffect(GameObject gameObject) {
        updateRoute();
        return super.askToAffect(gameObject);
    }
}
