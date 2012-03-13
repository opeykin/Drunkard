package ru.spbau.opeykin.drunkard.game.objects;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;
import ru.spbau.opeykin.drunkard.game.Position;

public class Policeman extends MovableGameObject {
	
	private Position target;
	
	Position returnPosition;
	
	private boolean complete = false;
	
	private LinkedList<Position> route;

	public Policeman(Position position, Position target, Position returnPosition) {
		super(position);
		this.target = target;
		this.returnPosition = returnPosition;
	}

	@Override
	public InteractionResult affect(GameObject gameObject) {
		return gameObject.getAffected(this);		
	}
	
	private void updateRoute() {
		if (complete) {
			route = getRoute(returnPosition);
		} else {
			route = getRoute(target);
		}
	}

	@Override
	public void doTurn() {
		if (complete && getPosition() == returnPosition) {
			getPosition().destroyObject();
			return;
		}
		
		if (route == null || route.isEmpty()) {
			updateRoute();
		}
		
		if (route == null) {
			//System.out.println("Policeman see no path");
			//no route exist for the moment
			return;
		}
		
		try {
			step(route.getFirst());
		} catch (NoSuchElementException e) {
			System.err.println("Policeman tried to get a position from empty route list");
			System.exit(1);
		}	
	}

	@Override
	protected void leavePosition(GameObjectCreator creator) {
		try {
			route.removeFirst();
		} catch (NoSuchElementException e) {
			System.err.println("Policeman tried to pop a position from empry route list");
			System.exit(1);
		}	
	}


	@Override
	InteractionResult getAffected(Drunkard drunkard) {
		if (drunkard.getPosition() == target) {
			complete = true;
			return InteractionResult.REPLACE_HOST;
		}
		updateRoute();
		return InteractionResult.KEEP_BOTH;
	}

	@Override
	InteractionResult getAffected(Lamp lamp) {
		updateRoute();
		return InteractionResult.KEEP_BOTH;
	}

	@Override
	InteractionResult getAffected(Pole pole) {
		updateRoute();
		return InteractionResult.KEEP_BOTH;
	}

	@Override
	InteractionResult getAffected(Policeman policeman) {
		updateRoute();
		return InteractionResult.KEEP_BOTH;
	}

	@Override
	InteractionResult getAffected(Bottle bottle) {
		updateRoute();
		return InteractionResult.KEEP_BOTH;
	}

	@Override
	public char getSymbol() {
		return '!';
	}

}
