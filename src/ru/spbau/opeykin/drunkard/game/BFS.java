package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.RouteMaker;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * User: Alexander Opeykin
 * Date: 4/22/12
 */
public class BFS implements RouteMaker {
    @Override
    public LinkedList<Position> getRoute(Position source, Position destination) {
        LinkedList<Position> queue = new LinkedList<Position>();
        HashMap<Position, Position> res = new HashMap<Position, Position>();
        queue.add(source);
        res.put(source, source);

        while (!queue.isEmpty()) {
            Position cur = queue.pollFirst();
            if (cur == destination) {
                return BSFResults(source, destination, res);
            }

            for (Position next : cur.getNeighbours()) {
                if (!next.isFree() && next != destination) {
                    continue;
                }

                if (!res.containsKey(next)) {
                    res.put(next, cur);
                    queue.add(next);
                }
            }
        }
        return null;
    }

    private static LinkedList<Position> BSFResults(Position source, Position destination, HashMap<Position, Position> res) {
        if (!res.containsKey(destination)) {
            return null;
        }

        LinkedList<Position> route = new LinkedList<Position>();
        Position cur = destination;

        do {
            route.addFirst(cur);
            cur = res.get(cur);
        } while (cur != source);

        return route;
    }
}
