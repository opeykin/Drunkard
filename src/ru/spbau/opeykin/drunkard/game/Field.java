package ru.spbau.opeykin.drunkard.game;

import java.util.LinkedList;
import java.util.List;
import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.PoliceDepartment;


public abstract class Field {	

	abstract Position getPosition(Position source, int shiftX, int shiftY);
	
	public abstract void draw();
	
	public abstract List<GameObject> getObjects(); 
	
	public abstract PoliceDepartment getPoliceDeparment();
	
	public abstract LinkedList<Position> getRoute(Position source, Position destination);
}
