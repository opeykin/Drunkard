package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;


/**
 * Class of objects able to create another objects (like PoliceDepartment)
 * 
 * @author Opeykin Alexander
 *
 */
abstract class CreatingGameObject extends OnFieldGameObject {
	
    protected Position creatingPosition;

    protected CreatingGameObject(Position position, Position creatingPosition) {
        super(position);
        this.creatingPosition = creatingPosition;
    }

    @Override
	public void destroy() {
		// do nothing
	}
}
