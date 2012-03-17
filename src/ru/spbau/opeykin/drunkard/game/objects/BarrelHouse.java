package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.GameObjectAdder;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;


public class BarrelHouse extends CreatingGameObject {

	private int counter = 0;
	
	private int drunkardCreatingPeriod;
	
	public BarrelHouse(GameObjectAdder adder, int drunkardCreatingPeriod) {
		super(adder);
		this.drunkardCreatingPeriod = drunkardCreatingPeriod;
		counter = drunkardCreatingPeriod; // to create one at first turn
	}

	@Override
	public InteractionResult affect(AffectableGameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
	public void doTurn() {
		if (counter < drunkardCreatingPeriod) {
			++counter;
		} else {
			if (adder.canAdd()) {
                adder.add(new Drunkard(adder.position));
				counter = 0;
			} else {
				// skip this turn then
			}
		}
	}
	
	public static char getChar() {
		return 'Т';
	}
}
