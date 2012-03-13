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


    GameObject getHost() {
        return host;
    }


    public Position getPosition(int shiftX, int shiftY) {
        return field.getPosition(this, shiftX, shiftY);
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


    public Field getField() {
        return field;
    }


    boolean isFree() {
        return host == null;
    }


    GameObject getObject() {
        return host;
    }


    void setListener(Listener listener) {
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

    void setObject(GameObject visitor) {
        host = visitor;

        for (Listener listener : listeners) {
            listener.register(host);
        }
    }


    public void destroyObject() {
        if (host != null) {
            host.destroy();
            host = null;
        }
    }


    void denudeContent(Position position) {
        destroyObject();
        setObject(position.host);
        position.host = null;
    }


    @Override
    public String toString() {
        return "Position [x=" + Integer.toString(x)
                + ", y=" + Integer.toString(y) + "]";
    }
}
