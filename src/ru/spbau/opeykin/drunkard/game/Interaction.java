package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.Listener;


public class Interaction {

	private Position source;

    public static enum InteractionResult {
        DESTROY_VISITOR, DESTROY_HOST, DESTROY_BOTH, KEEP_BOTH, REPLACE_HOST }
	

	public Interaction(Position source) {
		this.source = source;
	}	
	
	
	public void setListener(int shiftX, int shiftY, Listener listener) {
        if (source.hasNeighbour(shiftX, shiftY)) {
            source.getPosition(shiftX, shiftY).setListener(listener);

        }
	}

	
	public Position move(Position destination) {
        if (destination.isFree()) {
            destination.denudeContent(source);
            return destination;
        }
		
		switch (source.getHost().askToAffect(destination.getHost())) {
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
                throw new UnsupportedOperationException(
                        "Unhandled InteractionResult in Interaction.make(...)");
		}
	}


}
