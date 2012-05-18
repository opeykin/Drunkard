package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 3/14/12
 */
public interface Field extends Iterable<GameObject> {
    @Override
    Iterator<GameObject> iterator();

    void draw();
    Position getPosition(Position source, int shiftX, int shiftY);
    List<Position> getNeighbours(Position position);
    boolean hasPosition(Position source, int shiftX, int shiftY);
    LinkedList<Position> getRoute(Position source, Position destination);
}
