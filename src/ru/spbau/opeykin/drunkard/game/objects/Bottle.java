package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;


public class Bottle extends MovableGameObject {

	public Bottle(Position position) {
		super(position);
	}

	@Override
	public InteractionResult affect(GameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
	public char getSymbol() {
		return 'B';
	}

	

}
