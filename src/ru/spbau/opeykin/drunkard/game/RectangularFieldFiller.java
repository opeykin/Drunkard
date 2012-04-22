package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.*;

/**
 * User: Alexander Opeykin
 * Date: 3/17/12
 */
public class RectangularFieldFiller {
    private static int fieldWidth = 15;
    private static int fieldHeight = 15;
    static final int drunkardCreatingPeriod = 5;
    static final int policemanCreatingLocationX = 15;
    static final int policemanCreatingLocationY = 4;
    static final int lampLightRadius = 3;

    static final int drunkardCreatingLocationX = 10;
    static final int drunkardCreatingLocationY = 1;


    public static void fill (RectangularBasedField field) {
        if (field.height - 2 != fieldHeight || field.width - 2 != fieldWidth) {
            throw new IllegalArgumentException("wrong field size");
        }

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
        new Lamp(lampPosition, lampLightRadius).addListener(policeDepartment);

        positions[16][4].releaseHost(); // destroy wall
        BottleBase base = new BottleBase(positions[16][4], positions[15][5]);
        field.addNonFiledGameObject(base);

        new Beggar(positions[15][5], positions[16][4]);

        new Bottle(positions[13][1]);
    }
}
