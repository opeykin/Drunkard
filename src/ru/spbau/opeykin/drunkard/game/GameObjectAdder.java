package ru.spbau.opeykin.drunkard.game;


import ru.spbau.opeykin.drunkard.game.objects.*;

public class GameObjectAdder {
	
	public final Position position;
	
	public GameObjectAdder(Position position) {
		this.position = position;
	}


    public void add(GameObject gameObject) {
        position.setHost(gameObject);
    }

	
	public boolean canAdd() {
		return position.isFree();
	}
}
