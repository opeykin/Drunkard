package ru.spbau.opeykin.drunkard.game.objects;

import java.util.LinkedList;

import ru.spbau.opeykin.drunkard.game.GameObjectAdder;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.objects.Drunkard.State;


public class PoliceDepartment 
	extends CreatingGameObject implements Listener {
	
	private LinkedList<GameObject> objectsToAffect = new LinkedList<GameObject>();
	
	private LinkedList<Position> targetQueue = new LinkedList<Position>();
	
	public PoliceDepartment(GameObjectAdder adder) {
		super(adder);
	}

	@Override
	public InteractionResult affect(AffectableGameObject gameObject) {
		return gameObject.getAffected(this);		
	}

	@Override
    InteractionResult getAffected(Drunkard drunkard) {
		//System.out.println("Police see a drunkard");
		if (drunkard.getState() == State.SLEEP_LYING) {
			targetQueue.add(drunkard.getPosition());
		}
		return InteractionResult.KEEP_BOTH;
	}

	@Override
	public void doTurn() {
		while (!objectsToAffect.isEmpty()) {
			objectsToAffect.poll().affect(this);
		}
		
		if (!targetQueue.isEmpty()) {
			if (adder.canAdd()) {
                adder.add(new Policeman(
                        adder.position, targetQueue.pollFirst(), adder.position));
			}
		}
	}

	@Override
	public void onEvent(GameObject object) {
		objectsToAffect.add(object);
	}
	
	
	public static char getChar() {
		return 'П';
	}
}
