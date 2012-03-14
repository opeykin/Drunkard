package ru.spbau.opeykin.drunkard.game.objects;


import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;

public abstract class AffectableGameObject implements GameObject {

    @Override
    public char getSymbol() {
		return '?';
	}
	

	@Override
    public void doTurn() {

	}

    public InteractionResult askToAffect(GameObject gameObject) {
        return gameObject.affect(this);
    }


	InteractionResult getAffected(Drunkard drunkard) {
		return InteractionResult.KEEP_BOTH;
	}

	InteractionResult getAffected(Lamp lamp) {
		return InteractionResult.KEEP_BOTH;
	}

	InteractionResult getAffected(Pole pole) {
		return InteractionResult.KEEP_BOTH;
	}

	InteractionResult getAffected(Policeman policeman) {
		return InteractionResult.KEEP_BOTH;
	}

	InteractionResult getAffected(Bottle bottle) {
		return InteractionResult.KEEP_BOTH;
	}

	InteractionResult getAffected(PoliceDepartment policeDepartment) {
		return InteractionResult.KEEP_BOTH;
	}
	
	InteractionResult getAffected(BarrelHouse barrelHouse) {
        throw new UnsupportedOperationException(
                this.toString() + " requested to get affected by " +
                barrelHouse.toString());
    }
}
