package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.RouteMaker;
import ru.spbau.opeykin.drunkard.game.objects.Wall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 4/22/12
 */
public abstract class RectangularBasedField implements Field {
    private final RouteMaker routeMaker;
    final int height;
    final int width;
    final Position [][] positions;
    private final List<GameObject> nonFiledGameObjects = new ArrayList<GameObject>();

    RectangularBasedField(int fieldWidth, int fieldHeight, RouteMaker routeMaker) {
        super();
        this.routeMaker = routeMaker;
        this.width = fieldWidth + 2;
        this.height = fieldHeight + 2;
        positions = new Position [height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                positions[i][j] = new Position(this, j, i);
            }
        }

        for (int i = 0; i < height; ++i) {
            new Wall(positions[i][0]);
            new Wall(positions[i][width - 1]);
        }

        for (int i = 1; i < width - 1; ++i) {
            new Wall(positions[0][i]);
            new Wall(positions[height - 1][i]);
        }
    }

    @Override
    public LinkedList<Position> getRoute(Position source, Position destination) {
        return routeMaker.getRoute(source, destination);
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

        return positions[source.getY() + shiftY][source.getX() + shiftX];
	}

    public Position[][] getAllPositions() {
        return positions;
    }

    @Override
	public List<GameObject> getObjects() {
		LinkedList<GameObject> objectList =	new LinkedList<GameObject>(nonFiledGameObjects);

		for (int i = 1; i < height - 1; ++i) {
			for (int j = 1; j < width - 1; ++j) {
				if (!positions[i][j].isFree()) {
					objectList.add(positions[i][j].getHost());
				}
			}
		}
		return Collections.unmodifiableList(objectList);
	}
}
