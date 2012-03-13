package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.Listener;


/**
 * Layer between {@link GameObject} and filed. Although objects should not 
 * know their {@link Position} they should use it to make next step.
 * 
 * @author Alexander Opeykin
 *
 */
public class Interaction {

	private Position source;		
	
	boolean isMoved = false;
	
	/**
	 * Reference to game field
	 */
	
	public Interaction(Position source) {
		this.source = source;
	}	
	
	
	public boolean isMoved() {
		return isMoved;
	}
	
	
	public GameObjectCreator getLeavedPositionCreator() {
		if (isMoved()) {
			return new GameObjectCreator(source);
		} else {
			return null;
		}
	}
	
	
	public void setListener(int shiftX, int shiftY, Listener listener) {
		Position position = source.getPosition(shiftX, shiftY);
		
		if (position != null) {
			position.setListener(listener);
		}
	}
	
	public Position move(int shiftX, int shiftY) {
		return move(source.getPosition(shiftX, shiftY));
	}
	
	
	public Position move(Position destination) {
		
		if (destination == null) { // wrong move(out of field)
			// TODO: may be needed to do some thing after wrong move
			return source;
		} 
		
		switch (destination.step(source)) {
			case DESTROY_VISITOR:
				source.destroyObject();
				return null;
			case DESTROY_HOST:
				destination.destroyObject();
				return source;
			case DESTROY_BOTH:
				source.destroyObject();
				destination.destroyObject();
				return null;
			case KEEP_BOTH:
				// do nothing
				return source;
			case REPLACE_HOST:
				isMoved = true;
				destination.denudeContent(source);
				//source.destroyObject();
				return destination;
			default:
				System.err.println(
						"Unhandeled InteractionResult in Interaction.make(...)");
				System.exit(1);
				return null;
		}
	}
}
