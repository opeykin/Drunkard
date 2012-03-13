package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;


public class Pole extends MovableGameObject {

	public Pole(Position position) {
		super(position);
	}

	@Override
	public InteractionResult affect(GameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
	public char getSymbol() {
		return '#';
	}
	
	

}
