package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.BarrelHouse;
import ru.spbau.opeykin.drunkard.game.objects.Lamp;
import ru.spbau.opeykin.drunkard.game.objects.Pole;
import ru.spbau.opeykin.drunkard.game.objects.PoliceDepartment;

/**
 * User: Alexander Opeykin
 * Date: 3/17/12
 */
public class RectangularFieldCreator {
    public static RectangularField create () {
        RectangularField field = new RectangularField();

        Position [][] positions = field.getAllPositions();


        int pY = GameConstants.policemanCreatingLocationY;
        int pX = GameConstants.policemanCreatingLocationX;

        PoliceDepartment policeDepartment = new PoliceDepartment(
                new GameObjectAdder(positions[pY][pX]));
        field.addNonFiledGameObject(policeDepartment);

        int dY = GameConstants.drunkardCreatingLocationY;
        int dX = GameConstants.drunkardCreatingLocationX;

        BarrelHouse barrelHouse = new BarrelHouse(
                new GameObjectAdder(positions[dY][dX]), GameConstants.drunkardCreatingPeriod);
        field.addNonFiledGameObject(barrelHouse);

        Position polePosition = positions[7][7];
        new GameObjectAdder(polePosition).add(new Pole(polePosition));

        Position lampPosition = positions[3][10];
        new GameObjectAdder(lampPosition).add(
                new Lamp(lampPosition, policeDepartment, GameConstants.lampLightRadius));

        return field;
    }
}
