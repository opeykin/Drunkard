package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.Lamp;
import ru.spbau.opeykin.drunkard.game.objects.Pole;

/**
 * User: Alexander Opeykin
 * Date: 3/17/12
 */
public class RectangularFieldCreator {
    public static RectangularField create () {
        RectangularField field = new RectangularField();

        Position [][] positions = field.getAllPositions();

        Position polePosition = positions[7][7];
        new GameObjectAdder(polePosition).add(new Pole(polePosition));

        Position lampPosition = positions[3][10];
        new GameObjectAdder(lampPosition).add(
                new Lamp(lampPosition, field.getPoliceDepartment(), GameConstants.lampLightRadius));

        return field;
    }
}
