package ru.spbau.opeykin.drunkard.game;

import java.util.Iterator;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.Wall;

/**
 * User: Alexander Opeykin (alexander.opeykin@gmail.com)
 * Date: 5/19/12
 * Time: 12:18 AM
 */
public class GameObjectIterator implements Iterator<GameObject> {
    private Position cur;
    private Position next;
    private final Position [][] positions;


    public GameObjectIterator(Position[][] positions) {
        this.positions = positions;
        next = getNext(-1, 0);
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public GameObject next() {
        cur = next;
        next = getNext(cur);
        return cur.getHost();
    }

    @Override
    public void remove() {
        cur.releaseHost();
    }

    private Position getNext(Position pos) {
        if (pos == null) {
            return null;
        } else {
            return getNext(pos.getX(), pos.getY());
        }
    }

    private Position getNext(int curX, int curY) {
        for (int y = curY; y < positions.length; ++y) {
            for (int x = (y == curY) ? curX + 1 : 0; x < positions[y].length; ++x) {
                if (filter(positions[y][x])) {
                    return positions[y][x];
                }
            }
        }

        return null;
    }

    private boolean filter(Position pos) {
        return !pos.isFree() && !pos.getHost().getClass().equals(Wall.class);
    }
}
