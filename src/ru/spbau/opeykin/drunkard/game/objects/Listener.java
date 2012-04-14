package ru.spbau.opeykin.drunkard.game.objects;


/**
 * Interface for objects that can be notified about some events 
 * like falling of a drunkard.
 * 
 * @author Opeykin Alexander
 *
 */
public interface Listener {
	public void onEvent(GameObject object);
}
