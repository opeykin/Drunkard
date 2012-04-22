package ru.spbau.opeykin.drunkard.game;

import java.util.*;
import ru.spbau.opeykin.drunkard.game.objects.*;


public class RectangularField implements Field {

	private int height;
	
	private int width;
	
	private Position [][] field;

    private RouteMaker routeMaker;

    private List<GameObject> nonFiledGameObjects = new ArrayList<GameObject>();


	public RectangularField(int fieldHeight, int fieldWidth, RouteMaker routeMaker) {
		super();
        this.routeMaker = routeMaker;
		this.height = fieldHeight + 2;
		this.width = fieldWidth + 2;
		field = new Position [height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				field[i][j] = new Position(this, j, i);				
			}
		}

        for (int i = 0; i < height; ++i) {
            new Wall(field[i][0]);
            new Wall(field[i][width - 1]);
        }

        for (int i = 1; i < width - 1; ++i) {
            new Wall(field[0][i]);
            new Wall(field[height - 1][i]);
        }

	}


    @Override
    public LinkedList<Position> getRoute(Position source, Position destination) {
        return routeMaker.getRoute(source, destination);
    }


    @Override
    public List<Position> getNeighbours(Position position) {
        List<Position> neighbours = new ArrayList<Position>();

        int [] shiftX = {1, -1, 0, 0};
        int [] shiftY = {0, 0, 1, -1};

        for (int i = 0; i < 4; ++i) {
            if (hasPosition(position, shiftX[i], shiftY[i])) {
                neighbours.add(getPosition(position, shiftX[i], shiftY[i]));
            }
        }

//        for (int shiftY = -1; shiftY < 2; ++shiftY) {
//            for (int shiftX = -1; shiftX < 2; ++shiftX) {
//                if (shiftX == 0 && shiftY == 0) {
//                    continue;
//                }
//
//                if (hasPosition(position, shiftX, shiftY)) {
//                    neighbours.add(getPosition(position, shiftX, shiftX));
//                }
//            }
//        }
        return neighbours;
    }

    public void addNonFiledGameObject(GameObject gameObject) {
        nonFiledGameObjects.add(gameObject);
    }

    @Override
    public boolean hasPosition(Position source, int shiftX, int shiftY) {
        int x = source.getX() + shiftX;
        int y = source.getY() + shiftY;

        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    @Override
	public Position getPosition(Position source, int shiftX, int shiftY) {
        if (!hasPosition(source, shiftX, shiftY)) {
            throw new IllegalArgumentException(
                    source.toString() + " doesn't have neighbour with shifts X: " +
                    shiftX + " Y: " + shiftY);
        }

        return field[source.getY() + shiftY][source.getX() + shiftX];
	}


	@Override
	public void draw() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				System.out.print(field[i][j].getSymbol());
			}
            System.out.println();
		}
        System.out.println();
	}


    public Position[][] getAllPositions() {
        return field;
    }


	@Override
	public List<GameObject> getObjects() {
		LinkedList<GameObject> objectList =	new LinkedList<GameObject>(nonFiledGameObjects);

		for (int i = 1; i < height - 1; ++i) {
			for (int j = 1; j < width - 1; ++j) {
				if (!field[i][j].isFree()) {
					objectList.add(field[i][j].getHost());
				}
			}
		}
		return Collections.unmodifiableList(objectList);
	}
}
