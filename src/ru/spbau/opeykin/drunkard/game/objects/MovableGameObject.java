package ru.spbau.opeykin.drunkard.game.objects;

import java.util.LinkedList;
import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

abstract class MovableGameObject extends OnFieldGameObject {

    MovableGameObject(Position position) {
        super(position);
    }


    /**
     * try to step to another position
     *
     * @param destination position to move on
     * @return true if moved
     */
    boolean step(Position destination) {
		Interaction move = new Interaction(position);
        Position newPosition = move.move(destination);

        if (position == null) { //this object was destroyed
            return true;
        }
        
		if (!position.equals(newPosition)) { // moved
			if (position.isFree()) {
				leavePosition(position);
			}
            position = newPosition;
            return true;
		}

        return false;
	}

    void leavePosition(Position leavedPosition) {
		
	}

    LinkedList<Position> getRoute(Position destination) {
		return position.getField().getRoute(position, destination);
	}
}
