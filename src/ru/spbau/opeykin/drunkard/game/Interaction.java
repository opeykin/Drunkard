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
	

	public Interaction(Position source) {
		this.source = source;
	}	
	
	
	public void setListener(int shiftX, int shiftY, Listener listener) {
		Position position = source.getPosition(shiftX, shiftY);
		
		if (position != null) {
			position.setListener(listener);
		}
	}

	
	public Position move(Position destination) {
		
		if (destination == null) { // wrong move(out of field)
			// TODO: may be needed to do some thing after wrong move
			return source;
		}
        if (destination.isFree()) {
            destination.denudeContent(source);
            return destination;
        }
		
		switch (destination.getHost().affect(source.getHost())) {
			case DESTROY_VISITOR:
				source.releaseHost();
				return null;
			case DESTROY_HOST:
				destination.releaseHost();
				return source;
			case DESTROY_BOTH:
				source.releaseHost();
				destination.releaseHost();
				return null;
			case KEEP_BOTH:
				// do nothing
				return source;
			case REPLACE_HOST:
				destination.denudeContent(source);
				//source.releaseHost();
				return destination;
			default:
				System.err.println(
						"Unhandeled InteractionResult in Interaction.make(...)");
				System.exit(1);
				return null;
		}
	}
}
