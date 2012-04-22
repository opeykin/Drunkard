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
    static final int policemanCreatingLocationX = 15;
    static final int policemanCreatingLocationY = 4;

    static final int drunkardCreatingLocationX = 10;
    static final int drunkardCreatingLocationY = 1;


    public static RectangularField create () {
        RectangularField field = new RectangularField(fieldHeight, fieldWidth, new BFS());

        Position [][] positions = field.getAllPositions();

        int pY = policemanCreatingLocationY;
        int pX = policemanCreatingLocationX;

        positions[4][16].releaseHost(); // destroy wall
        PoliceDepartment policeDepartment = new PoliceDepartment(
                positions[4][16], positions[pY][pX]);
        field.addNonFiledGameObject(policeDepartment);

        int dY = drunkardCreatingLocationY;
        int dX = drunkardCreatingLocationX;

        positions[0][10].releaseHost(); // destroy wall
        BarrelHouse barrelHouse = new BarrelHouse(
                positions[0][10], positions[dY][dX], drunkardCreatingPeriod);
        field.addNonFiledGameObject(barrelHouse);

        Position polePosition = positions[8][8];
        new Pole(polePosition);

        Position lampPosition = positions[4][11];
        new Lamp(lampPosition, 5).addListener(policeDepartment);

        positions[16][4].releaseHost(); // destroy wall
        BottleBase base = new BottleBase(positions[16][4], positions[15][5]);
        field.addNonFiledGameObject(base);

        new Beggar(positions[15][5], positions[16][4]);

        new Bottle(positions[13][1]);

        return field;
    }
//
//    public static RectangularField createTest () {
//
//    }
}
