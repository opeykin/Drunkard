package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;


/**
 * Class of objects able to create another objects (like PoliceDepartment)
 * 
 * @author Opeykin Alexander
 *
 */
abstract class CreatingGameObject extends OnFieldGameObject {
	
    final Position creatingPosition;

    CreatingGameObject(Position position, Position creatingPosition) {
        super(position);
        this.creatingPosition = creatingPosition;
    }
}
