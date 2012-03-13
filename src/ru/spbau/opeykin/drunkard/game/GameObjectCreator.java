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
		position.setObject(gameObject);
	}
	
	public void createBottle() {
		GameObject gameObject = new Bottle(position);
		position.setObject(gameObject);
	}
	
	public void createLamp(int lightRadius) {
		GameObject gameObject = new Lamp(position, 
										 position.getField().getPoliceDeparment(), 
										 lightRadius);
		position.setObject(gameObject);
	}
	
	public void createPole() {
		GameObject gameObject = new Pole(position);
		position.setObject(gameObject);
	}
	
	public void createPoliceman(Position target) {
		GameObject gameObject = new Policeman(position, target, position);
		position.setObject(gameObject);
	}
	
	public BarrelHouse createBarrelHouse(int druncardCreatingPeriod) {
		return new BarrelHouse(this, druncardCreatingPeriod);
	}	
	
	public PoliceDepartment createPoliceDepartment() {
		return new PoliceDepartment(this);
	}
}
