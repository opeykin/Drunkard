package ru.spbau.opeykin.drunkard.game.objects;

import java.util.LinkedList;

import ru.spbau.opeykin.drunkard.game.GameObjectAdder;
import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

abstract class MovableGameObject extends AffectableGameObject {
	
	private Position position;
	
	
	protected MovableGameObject(Position position) {
		this.position = position;
	}
	
	protected void step(int shiftX, int shiftY) {
        if (position.hasNeighbour(shiftX, shiftY)) {
            step(position.getPosition(shiftX, shiftY));
        }
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
        
		if (!position.equals(newPosition)) { // moved
			GameObjectAdder adder = new GameObjectAdder(position);
			if (adder.canAdd()) {
				leavePosition(adder);
			}
            position = newPosition;
            return true;
		}

        return false;
	}
	
	protected Interaction interact() {
		return new Interaction(position);
	}
	
	protected void leavePosition(GameObjectAdder adder) {
		
	}
	
	public void destroy()
	{
		position = null;
	}

	public Position getPosition() {
		return position;
	}
	
	protected LinkedList<Position> getRoute(Position destination) {
		return position.getField().getRoute(position, destination);
	}
}
