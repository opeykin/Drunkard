package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.Lamp;
import ru.spbau.opeykin.drunkard.game.objects.Pole;

/**
 * User: Alexander Opeykin
 * Date: 3/17/12
 */
public class RectangularFieldCreator {
    public static Field create () {
        RectangularField field = new RectangularField(
                GameConstants.fieldHeight, GameConstants.fieldWidth);

        Position [][] positions = field.getAllPositions();

        // TODO swap lamp and pole according to rules
        Position polePosition = positions[10][7];
        new GameObjectAdder(polePosition).add(new Pole(polePosition));

        Position lampPosition = positions[6][7];
        new GameObjectAdder(lampPosition).add(
                new Lamp(lampPosition, field.getPoliceDepartment(), GameConstants.lampLightRadius));

        return field;
    }
}
