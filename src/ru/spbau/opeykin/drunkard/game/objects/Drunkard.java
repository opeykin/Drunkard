package ru.spbau.opeykin.drunkard.game.objects;

import java.util.Random;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.GameConstants;


public class Drunkard extends MovableGameObject {

	public enum State { ACTIVE, SLEEP_LYING, SLEEP_STANDING }
	
	
	private State state = State.ACTIVE;
	
	
	boolean haveBottle = true;
	
	
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
			return '!';
		}
	}
	
	@Override
	public void doTurn() {
		if (state != State.ACTIVE) {
			return;
		}
		
		Random random = new Random();
		
		int value;
		
		if (random.nextBoolean()) {
			value = 1;
		} else {
			value = -1;
		}
		
		int x;
		int y;
		
		if (random.nextBoolean()) {
			x = value;
			y = 0;
		} else {
			x = 0;
			y = value;
		}
		
		//System.out.println("x = " + new Integer(x).toString());
		//System.out.println("y = " + new Integer(y).toString());
		step(x,y);
	}
	

	public State getState() {
		return state;
	}
		

	@Override
	public InteractionResult affect(GameObject gameObject) {
		return gameObject.getAffected(this);		
	}


	@Override
	InteractionResult getAffected(Drunkard drunkard) {
		switch (drunkard.state) {
			case ACTIVE:
				// do nothing. just skip this turn.
				return InteractionResult.KEEP_BOTH;
			case SLEEP_LYING:
				//state = State.SLEEP_LYING;
				return InteractionResult.KEEP_BOTH;
			case SLEEP_STANDING:
				state = State.SLEEP_STANDING;
				return InteractionResult.KEEP_BOTH;
			default:
				System.err.println(
					"Unhandeled state in Drunkard.getAffected(Drunkard)");
				System.exit(1);
				return InteractionResult.KEEP_BOTH;
		}
	}


	@Override
	InteractionResult getAffected(Lamp lamp) {
		state = State.SLEEP_STANDING;
		return InteractionResult.KEEP_BOTH;
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
	protected void leavePosition(GameObjectCreator creator) {
		if (haveBottle) {
			Random random = new Random();
			if (random.nextInt(GameConstants.drunkardBottleDropProb) == 0) {
				creator.createBottle();
				haveBottle = false;
			}
		}
	}	
}
