package ru.spbau.opeykin.drunkard.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 4/22/12
 */
public class HexagonalField extends RectangularBasedField {

    public HexagonalField(int fieldWidth, int fieldHeight, RouteMaker routeMaker) {
        super(fieldWidth, fieldHeight, routeMaker);
    }


    @Override
    public void draw() {
        for (int i = 0; i < height; ++i) {
            if (i % 2 == 1) {
                System.out.print(" ");
            }
            for (int j = 0; j < width; ++j) {
                System.out.print(positions[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }


    @Override
    public List<Position> getNeighbours(Position position) {
        List<Position> neighbours = new ArrayList<Position>();

        List<Integer> shiftY = Arrays.asList(-1, -1, 0, 0, 1, 1);
        List<Integer> shiftX;

        if (position.getY() % 2 == 0) {
            shiftX = Arrays.asList(-1, 0, -1, 1, -1, 0);
        } else {
            shiftX = Arrays.asList(0, 1, -1, 1, 0, 1);
        }

        for (int i = 0; i < 6; ++i) {
            if (hasPosition(position, shiftX.get(i), shiftY.get(i))) {
                neighbours.add(getPosition(position, shiftX.get(i), shiftY.get(i)));
            }
        }

        return neighbours;
    }
}
