package ru.spbau.opeykin.drunkard.game.objects;

import java.util.LinkedList;
import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

abstract class MovableGameObject extends OnFieldGameObject {

    protected MovableGameObject(Position position) {
        super(position);
    }
	
	protected boolean step(int shiftX, int shiftY) {
        if (!position.hasNeighbour(shiftX, shiftY)) {
            return false;
        }

        return step(position.getPosition(shiftX, shiftY));
	}


    /**
     * try to step to another position
     *
     * @param destination position to move on
     * @return true if moved
     */
	protected boolean step(Position destination) {
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

    protected void leavePosition(Position leavedPosition) {
		
	}

    protected LinkedList<Position> getRoute(Position destination) {
		return position.getField().getRoute(position, destination);
	}
}
