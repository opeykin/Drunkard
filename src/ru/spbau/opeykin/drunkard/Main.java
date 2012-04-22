package ru.spbau.opeykin.drunkard;


import ru.spbau.opeykin.drunkard.game.*;
import ru.spbau.opeykin.drunkard.game.objects.GameObject;

public class Main {

	public static void main(String[] args) {
        RectangularBasedField field;

        //noinspection ConstantIfStatement
        if (true) { // Field can be changed here
            field = new HexagonalField(15, 15, new BFS());
        } else {
            field = new RectangularField(15, 15, new BFS());
        }

        RectangularFieldFiller.fill(field);

		for (Integer i = 0; i < 501; ++i) {
			for (GameObject gameObject : field.getObjects()) {
				gameObject.doTurn();
			}
			System.out.println(i.toString());
			field.draw();
		}
	}
}
