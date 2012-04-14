package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;


public class BarrelHouse extends CreatingGameObject {

	private int counter = 0;
	
	private int drunkardCreatingPeriod;

    public BarrelHouse(Position position, Position creatingPosition, int drunkardCreatingPeriod) {
        super(position, creatingPosition);
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
			if (creatingPosition.isFree()) {
                new Drunkard(creatingPosition);
				counter = 0;
			} else {
				// skip this turn then
			}
		}
	}

    @Override
    public char getSymbol() {
        return 'T';
    }
}
