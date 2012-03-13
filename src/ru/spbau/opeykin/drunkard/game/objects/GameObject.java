package ru.spbau.opeykin.drunkard.game.objects;


public abstract class GameObject {
	
	
	public enum InteractionResult {
		DESTROY_VISITOR, DESTROY_HOST, DESTROY_BOTH, KEEP_BOTH, REPLACE_HOST}
	
	
	public abstract void destroy();
	

	public abstract InteractionResult affect(GameObject gameObject);
	
	
	public char getSymbol() {
		return '?';
	}
	

	public void doTurn() {

	}
	
	/*
	public void releasePosition() {
		position = null;
	}
	*/

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
