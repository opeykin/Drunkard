package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 3/14/12
 */
public interface Field {
    Position getPosition(Position source, int shiftX, int shiftY);

    boolean hasPosition(Position source, int shiftX, int shiftY);

    void draw();

    List<GameObject> getObjects();

    LinkedList<Position> getRoute(Position source, Position destination);

    List<Position> getNeighbours(Position position);
}
