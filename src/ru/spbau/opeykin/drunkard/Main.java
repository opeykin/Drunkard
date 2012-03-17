package ru.spbau.opeykin.drunkard;



import ru.spbau.opeykin.drunkard.game.Field;
import ru.spbau.opeykin.drunkard.game.RectangularFieldCreator;
import ru.spbau.opeykin.drunkard.game.objects.GameObject;

public class Main {

	public static void main(String[] args) {
		Field field = RectangularFieldCreator.create();
		//field.draw();
	
		for (Integer i = 0; i < 501; ++i) {
			for (GameObject gameObject : field.getObjects()) {
				gameObject.doTurn();
			}
			System.out.println(i.toString());
			field.draw();
		}
	}
}
