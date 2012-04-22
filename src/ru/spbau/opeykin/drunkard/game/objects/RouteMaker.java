package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;
import java.util.LinkedList;

/**
 * User: Alexander Opeykin
 * Date: 4/22/12
 */
public interface RouteMaker {
    public LinkedList<Position> getRoute(Position source, Position destination);
}
