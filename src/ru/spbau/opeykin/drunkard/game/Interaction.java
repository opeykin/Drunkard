package ru.spbau.opeykin.drunkard.game;


public class Interaction {

	private final Position source;

    public static enum InteractionResult {
        RELEASE_VISITOR, DESTROY_HOST, DESTROY_BOTH, KEEP_BOTH, REPLACE_HOST }
	

	public Interaction(Position source) {
		this.source = source;
	}	

	
	public Position move(Position destination) {
        if (destination.isFree()) {
            destination.setOthersHost(source);
            return destination;
        }
		
		switch (source.getHost().askToAffect(destination.getHost())) {
			case RELEASE_VISITOR:
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
				destination.setOthersHost(source);
				return destination;
			default:
                throw new UnsupportedOperationException(
                        "Unhandled InteractionResult in Interaction.make(...)");
		}
	}
}
