package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.PoliceDepartment;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 3/14/12
 */
public interface Field {
    Position getPosition(Position source, int shiftX, int shiftY);

    void draw();

    List<GameObject> getObjects();

    PoliceDepartment getPoliceDepartment();

    LinkedList<Position> getRoute(Position source, Position destination);
}
