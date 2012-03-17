package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.GameObjectAdder;


/**
 * Class of objects able to create another objects (like PoliceDepartment)
 * 
 * @author Opeykin Alexander
 *
 */
abstract class CreatingGameObject extends AffectableGameObject {
	
	protected GameObjectAdder adder;
	
	
	protected CreatingGameObject(GameObjectAdder adder) {
		super();
		this.adder = adder;
	}

	@Override
	public void destroy() {
		// do nothing
	}
}
