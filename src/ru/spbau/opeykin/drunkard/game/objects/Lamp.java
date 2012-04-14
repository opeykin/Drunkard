package ru.spbau.opeykin.drunkard.game.objects;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;
import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;


public class Lamp extends OnFileldGameObject {

	public Lamp(Position position, Listener policeDepartment, int lightRadius) {
		super(position);
		Interaction listenerSetter = interact();
		
		for (int x = -lightRadius; x <= lightRadius; ++x) {
			for (int y = -lightRadius; y <= lightRadius; ++y) {
                if (x != 0 || y != 0) {
                    listenerSetter.setListener(x, y, policeDepartment);
                }
			}
		}
	}

	@Override
	public InteractionResult affect(AffectableGameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
	public char getSymbol() {
		return 'Ф';
	}
}
