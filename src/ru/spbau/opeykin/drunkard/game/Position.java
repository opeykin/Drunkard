package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.Listener;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents cell of the game field. Store objects staying on it.
 *
 * @author Opeykin Alexander
 */
public class Position {

    private GameObject host;

    private List<Listener> listeners = new ArrayList<Listener>();

    private Field field;

    private int x;

    private int y;


    Position(Field field, int x, int y) {
        this.field = field;
        this.x = x;
        this.y = y;
    }


    public Position getPosition(int shiftX, int shiftY) {
        return field.getPosition(this, shiftX, shiftY);
    }

    public boolean hasNeighbour(int shiftX, int shiftY) {
        return field.hasPosition(this, shiftX, shiftY);
    }


    void denudeContent(Position position) {
        releaseHost();
        setHost(position.host);
        position.host = null;
    }


    GameObject getHost() {
        return host;
    }


    void setHost(GameObject visitor) {
        if (!isFree()) {
            throw new IllegalStateException(
                    "trying to set " + visitor.toString() + " to " + this.toString() +
                    "while already having host: " + host.toString());
        }

        host = visitor;

        for (Listener listener : listeners) {
            listener.register(host);
        }
    }


    public void releaseHost() {
        if (host != null) {
            host.destroy();
            host = null;
        }
    }


    public Field getField() {
        return field;
    }


    public boolean isFree() {
        return host == null;
    }


    void addListener(Listener listener) {
        listeners.add(listener);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Position [x=" + Integer.toString(x)
                + ", y=" + Integer.toString(y) + "]";
    }


    char getSymbol() {
        if (host == null) {
            return 'o';
        } else {
            return host.getSymbol();
        }
    }


    int getX() {
        return x;
    }


    int getY() {
        return y;
    }
}
