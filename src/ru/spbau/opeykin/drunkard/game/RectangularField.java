package ru.spbau.opeykin.drunkard.game;

import java.util.ArrayList;
import java.util.List;


public class RectangularField extends RectangularBasedField {

	public RectangularField(int fieldHeight, int fieldWidth, RouteMaker routeMaker) {
		super(fieldWidth, fieldHeight, routeMaker);

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

        return neighbours;
    }


    @Override
	public void draw() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				System.out.print(positions[i][j].getSymbol());
			}
            System.out.println();
		}
        System.out.println();
	}
}
