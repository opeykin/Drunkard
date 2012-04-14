package ru.spbau.opeykin.drunkard.game.objects;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;
import ru.spbau.opeykin.drunkard.game.Position;


public class Pole extends OnFileldGameObject {

	public Pole(Position position) {
		super(position);
	}

	@Override
	public InteractionResult affect(AffectableGameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
	public char getSymbol() {
		return '#';
	}
	
	

}
