package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.GameObjectCreator;


/**
 * Class of objects able to create another objects (like PoliceDepartment)
 * 
 * @author Opeykin Alexander
 *
 */
abstract class CreatingGameObject extends AffectableGameObject {
	
	protected GameObjectCreator creator;
	
	
	protected CreatingGameObject(GameObjectCreator creator) {
		super();
		this.creator = creator;
	}

	@Override
	public void destroy() {
		// do nothing
	}
}
