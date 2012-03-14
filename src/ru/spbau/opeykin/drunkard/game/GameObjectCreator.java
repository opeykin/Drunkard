package ru.spbau.opeykin.drunkard.game;


import ru.spbau.opeykin.drunkard.game.objects.*;


public class GameObjectCreator {
	
	private Position position;
	
	public GameObjectCreator(Position position) {
		this.position = position;
	}
	
	
	public boolean canCreate() {
		return position.isFree();
	}
	
	
	public void createDrunkard() {
		AffectableGameObject gameObject = new Drunkard(position);
		position.setHost(gameObject);
	}
	
	public void createBottle() {
		AffectableGameObject gameObject = new Bottle(position);
		position.setHost(gameObject);
	}
	
	public void createLamp(int lightRadius) {
		AffectableGameObject gameObject = new Lamp(position,
										 position.getField().getPoliceDepartment(),
										 lightRadius);
		position.setHost(gameObject);
	}
	
	public void createPole() {
		AffectableGameObject gameObject = new Pole(position);
		position.setHost(gameObject);
	}
	
	public void createPoliceman(Position target) {
		AffectableGameObject gameObject = new Policeman(position, target, position);
		position.setHost(gameObject);
	}
	
	public BarrelHouse createBarrelHouse(int druncardCreatingPeriod) {
		return new BarrelHouse(this, druncardCreatingPeriod);
	}	
	
	public PoliceDepartment createPoliceDepartment() {
		return new PoliceDepartment(this);
	}
}
