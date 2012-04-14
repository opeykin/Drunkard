package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.*;

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
        new Lamp(lampPosition, 4).addListener(policeDepartment);

        Position beggarPosition = positions[14][4];
        new Beggar(beggarPosition, beggarPosition);

        new Bottle(positions[12][0]);

        return field;
    }
}
