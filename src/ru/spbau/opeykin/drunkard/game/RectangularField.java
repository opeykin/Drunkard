package ru.spbau.opeykin.drunkard.game;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashMap;


import ru.spbau.opeykin.drunkard.game.objects.BarrelHouse;
import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.PoliceDepartment;


public class RectangularField implements Field {

	private int height;
	
	private int width;
	
	private Position [][] field;
	
	private PoliceDepartment policeDepartment;
	
	private GameObject barrelHouse;
	

	public RectangularField(int height, int width) {
		super();
		this.height = height;
		this.width = width;
		field = new Position [height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				field[i][j] = new Position(this, j, i);				
			}
		}
	
		createGameObjects();
	}
	
	private void createGameObjects() {
		
		int dY = GameConstants.druncardCreatingLocationY;
		int dX = GameConstants.druncardCreatingLocationX;
		barrelHouse = 
				new GameObjectCreator(field[dY][dX]).createBarrelHouse(5);

		int pY = GameConstants.policemanCreatingLocationY;
		int pX = GameConstants.policemanCreatingLocationX;
		policeDepartment = 
				new GameObjectCreator(field[pY][pX]).createPoliceDepartment();
		
		new GameObjectCreator(field[6][7]).createPole();
		
		new GameObjectCreator(field[10][7]).createLamp(GameConstants.lampLightRadius);
		/*
		new GameObjectCreator(field[9][7]).createBottle();
		new GameObjectCreator(field[10][6]).createBottle();
		new GameObjectCreator(field[11][7]).createBottle();
		new GameObjectCreator(field[10][8]).createBottle();
		*/
	}
	
	
	@Override
	public Position getPosition(Position source, int shiftX, int shiftY) {
		int x = source.getX() + shiftX;
		int y = source.getY() + shiftY;
		
		if ((x >= 0) && (x < width) && (y >= 0) && (y < height)) {
			return field[y][x];
		} else {
			return null;
		}
	}


	@Override
	public
	void draw() {
		System.out.println(getIdent(GameConstants.barrelHouseDrawLocation) + 
						   BarrelHouse.getChar());
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				System.out.print(field[i][j].getSymbol());
			}
			System.out.println();
		}
		System.out.println(getIdent(GameConstants.policeDepartmentDrawLocation) + 
				   		   PoliceDepartment.getChar() + "\n");
	}
	
	public PoliceDepartment getPoliceDeparment() {
		return policeDepartment;
	}


	@Override
	public List<GameObject> getObjects() {
		LinkedList<GameObject> objectList =	new LinkedList<GameObject>();
		objectList.add(barrelHouse);
		objectList.add(policeDepartment);
		
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				if (field[i][j].isFree() == false) {
					objectList.add(field[i][j].getObject());
				}
			}
		}
		return Collections.unmodifiableList(objectList);
	}
	
	private LinkedList<Position> BSFResults(Position source, Position destitation, HashMap<Position, Position> res) {
		if (res.containsKey(destitation) == false) {
			return null;
		}
		
		LinkedList<Position> route = new LinkedList<Position>();
		Position cur = destitation;
		
		do {
			route.addFirst(cur);
			cur = res.get(cur);
		} while (cur != source);
		
		return route;
	}
	
	public LinkedList<Position> BFS(Position source, Position destination) {
		LinkedList<Position> queue = new LinkedList<Position>();
		HashMap<Position, Position> res = new HashMap<Position, Position>();
		queue.add(source);
		res.put(source, source);
		
		while (queue.isEmpty() == false) {
			Position cur = queue.pollFirst();
			if (cur == destination) {
				return BSFResults(source, destination, res);
			}
			for (int shiftY = -1; shiftY < 2; ++shiftY) {
				for (int shiftX = -1; shiftX < 2; ++shiftX) {
					if (shiftX == 0 && shiftY == 0) {
						continue;
					}
					Position next = cur.getPosition(shiftX, shiftY);
					if (next == null || 
							(next.isFree() == false && next != destination)) {
						continue;
					}
					if (res.containsKey(next) == false) {
						res.put(next, cur);
						queue.add(next);
					}
				}
			}
		}
		return null;
	}
	
	public LinkedList<Position> getRoute(Position source, Position destination)
	{
		return BFS(source, destination);
	}
	
	
	private static String getIdent(int length) {
		char[] charArray = new char[length];
		Arrays.fill(charArray,' ');
		return new String(charArray);
	}
}
