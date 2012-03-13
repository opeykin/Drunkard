package ru.spbau.opeykin.drunkard;



import ru.spbau.opeykin.drunkard.game.Field;
import ru.spbau.opeykin.drunkard.game.RectangularField;
import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.GameConstants;

public class Main {

	public static void main(String[] args) {
		Field field = new RectangularField(GameConstants.fieldHeight, GameConstants.fieldWidth);
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
