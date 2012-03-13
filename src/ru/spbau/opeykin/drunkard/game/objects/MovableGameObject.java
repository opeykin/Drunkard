package ru.spbau.opeykin.drunkard.game.objects;

import java.util.LinkedList;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;
import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;

abstract class MovableGameObject extends GameObject {
	
	private Position position;
	
	
	protected MovableGameObject(Position position) {
		this.position = position;
	}
	
	protected void step(int shiftX, int shiftY) {
		step(position.getPosition(shiftX, shiftY));
	}
	
	protected void step(Position destination) {
		Interaction move = new Interaction(position);
        Position newPosition = move.move(destination);
        
		if (!position.equals(newPosition)) { // moved
			GameObjectCreator creator = new GameObjectCreator(position);
			if (creator.canCreate()) {
				leavePosition(creator);
			}
            position = newPosition;
		}
	}
	
	protected Interaction interact() {
		return new Interaction(position);
	}
	
	protected void leavePosition(GameObjectCreator creator) {
		
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
