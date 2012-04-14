package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;


/**
 * Class of objects able to create another objects (like PoliceDepartment)
 * 
 * @author Opeykin Alexander       k
 *
 */
abstract class CreatingGameObject extends AffectableGameObject {
	
    protected Position creatingPosition;

    protected CreatingGameObject(Position creatingPosition) {
        this.creatingPosition = creatingPosition;
    }

    @Override
	public void destroy() {
		// do nothing
	}
}
