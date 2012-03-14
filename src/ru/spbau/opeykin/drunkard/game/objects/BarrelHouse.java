package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;
import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;


public class BarrelHouse extends CreatingGameObject {

	private int counter = 0;
	
	private int drunkardCreatingPeriod;
	
	public BarrelHouse(GameObjectCreator creator, int drunkardCreatingPeriod) {
		super(creator);
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
