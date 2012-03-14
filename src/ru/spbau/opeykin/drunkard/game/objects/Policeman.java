package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;
import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;
import ru.spbau.opeykin.drunkard.game.Position;

import java.util.LinkedList;

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
	public InteractionResult affect(AffectableGameObject gameObject) {
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
			getPosition().releaseHost(); // mission accomplished
			return;
		}
		
		if (route == null || route.isEmpty()) {
			updateRoute();
		}
		
		if (route == null) {
			// no route exists for the moment
			return;
		}

        boolean isMoved = step(route.getFirst());
        if (!isMoved) {
            updateRoute();
        }
	}


	@Override
	protected void leavePosition(GameObjectCreator creator) {
        route.pollFirst();
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
