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
    private static int fieldWidth = 15;
    private static int fieldHeight = 15;
    static final int drunkardCreatingPeriod = 5;
    static final int policemanCreatingLocationX = 14;
    static final int policemanCreatingLocationY = 3;

    static final int drunkardCreatingLocationX = 9;
    static final int drunkardCreatingLocationY = 0;


    public static RectangularField create () {
        RectangularField field = new RectangularField(fieldHeight, fieldWidth);

        Position [][] positions = field.getAllPositions();


        int pY = policemanCreatingLocationY;
        int pX = policemanCreatingLocationX;

        PoliceDepartment policeDepartment = new PoliceDepartment(positions[pY][pX]);
        field.addNonFiledGameObject(policeDepartment);

        int dY = drunkardCreatingLocationY;
        int dX = drunkardCreatingLocationX;

        BarrelHouse barrelHouse = new BarrelHouse(positions[dY][dX], drunkardCreatingPeriod);
        field.addNonFiledGameObject(barrelHouse);

        Position polePosition = positions[7][7];
        new Pole(polePosition);

        Position lampPosition = positions[3][10];
        new Lamp(lampPosition, policeDepartment, 4);

        return field;
    }
}
