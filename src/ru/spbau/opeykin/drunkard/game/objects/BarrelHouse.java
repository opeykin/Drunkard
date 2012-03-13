package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;


public class BarrelHouse extends CreatingGameObject {

	private int counter = 0;
	
	private int druncardCreatingPeriod;
	
	public BarrelHouse(GameObjectCreator creator, int druncardCreatingPeriod) {
		super(creator);
		this.druncardCreatingPeriod = druncardCreatingPeriod;
		counter = druncardCreatingPeriod; // to create one at first turn
	}

	@Override
	public InteractionResult affect(GameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
	public void doTurn() {
		if (counter < druncardCreatingPeriod) {
			++counter;
		} else {
			if (creator.canCreate()) {
				creator.createDrunkard();
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
