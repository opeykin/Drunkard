package ru.spbau.opeykin.drunkard.game.objects;

import java.util.List;
import java.util.Random;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;
import ru.spbau.opeykin.drunkard.game.Position;


public class Drunkard extends MovableGameObject {

	public enum State { ACTIVE, SLEEP_LYING, SLEEP_STANDING }

	private State state = State.ACTIVE;

	private boolean haveBottle = true;

    private final int drunkardBottleDropProb = 30;
	
	
	public Drunkard(Position position) {
		super(position);
	}
	

	public char getSymbol() {
		switch (state) {
		case ACTIVE:
			return '@';
		case SLEEP_LYING:
			return '&';
		case SLEEP_STANDING:
			return '1';
		default:
			throw new UnsupportedOperationException(
                    "Unsupported drunkard state: " + state);
		}
	}
	
	@Override
	public void doTurn() {
		if (state != State.ACTIVE) {
			return;
		}
		
		Random random = new Random();
        List<Position> neighbors = position.getNeighbours();

        if (neighbors.size() > 0) {
            step(neighbors.get(random.nextInt(neighbors.size())));
        }
	}
	

	public State getState() {
		return state;
	}
		

	@Override
	public InteractionResult affect(AffectableGameObject gameObject) {
		return gameObject.getAffected(this);		
	}


	@Override
    InteractionResult getAffected(Drunkard drunkard) {
		switch (drunkard.getState()) {
			case ACTIVE:
				// do nothing. just skip this turn.
				return InteractionResult.KEEP_BOTH;
			case SLEEP_LYING:
				return InteractionResult.KEEP_BOTH;
			case SLEEP_STANDING:
				state = State.SLEEP_STANDING;
				return InteractionResult.KEEP_BOTH;
			default:
                throw new UnsupportedOperationException(
                        "Unhandled state in Drunkard.getAffected(Drunkard)");
		}
	}


	@Override
    InteractionResult getAffected(Pole pole) {
		state = State.SLEEP_STANDING;
		return InteractionResult.KEEP_BOTH;
	}


	@Override
    InteractionResult getAffected(Bottle actor) {
		state = State.SLEEP_LYING;
		return InteractionResult.REPLACE_HOST;
	}



	@Override
	protected void leavePosition(Position leavedPosition) {
		if (haveBottle) {
			Random random = new Random();
			if (random.nextInt(drunkardBottleDropProb) == 0) {
                // leavedPosition is not checked for being free. It has to be so.
                new Bottle(leavedPosition);
				haveBottle = false;
			}
		}
	}	
}
