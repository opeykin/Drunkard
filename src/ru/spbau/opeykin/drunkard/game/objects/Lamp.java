package ru.spbau.opeykin.drunkard.game.objects;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;

import ru.spbau.opeykin.drunkard.game.Position;


public class Lamp extends OnFieldGameObject {
    private int lightRadius;

    public Lamp(Position position, int lightRadius) {
        super(position);
        this.lightRadius = lightRadius;
    }
//
//    public Lamp(Position position, Listener policeDepartment, int lightRadius) {
//		super(position);
//		Interaction listenerSetter = interact();
//
//		for (int x = -lightRadius; x <= lightRadius; ++x) {
//			for (int y = -lightRadius; y <= lightRadius; ++y) {
//                if (x != 0 || y != 0) {
//                    listenerSetter.setListener(x, y, policeDepartment);
//                }
//			}
//		}
//	}

    public void addListener(Listener listener) {
        for (int x = -lightRadius; x <= lightRadius; ++x) {
            for (int y = -lightRadius; y <= lightRadius; ++y) {
                if ((x != 0 || y != 0) && position.hasNeighbour(x, y)) {
                    position.getPosition(x, y).addListener(listener);
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
