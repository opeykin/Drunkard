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
		GameObject gameObject = new Drunkard(position);
		position.setHost(gameObject);
	}
	
	public void createBottle() {
        GameObject gameObject = new Bottle(position);
		position.setHost(gameObject);
	}
	
	public void createLamp(int lightRadius) {
        GameObject gameObject = new Lamp(position,
										 position.getField().getPoliceDepartment(),
										 lightRadius);
		position.setHost(gameObject);
	}
	
	public void createPole() {
		GameObject gameObject = new Pole(position);
		position.setHost(gameObject);
	}
	
	public void createPoliceman(Position target) {
		GameObject gameObject = new Policeman(position, target, position);
		position.setHost(gameObject);
	}
	
	public BarrelHouse createBarrelHouse(int drunkardCreatingPeriod) {
		return new BarrelHouse(this, drunkardCreatingPeriod);
	}	
	
	public PoliceDepartment createPoliceDepartment() {
		return new PoliceDepartment(this);
	}
}
