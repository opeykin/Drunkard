package ru.spbau.opeykin.drunkard.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import ru.spbau.opeykin.drunkard.game.objects.Pole;

/**
 * User: Alexander Opeykin
 * Date: 3/18/12
 */
public class RectangularFieldTest {

    private RectangularField field;

    @Before
    public void setup() {
        field = new RectangularField();
    }

    @Test
    public void testGetPosition() throws Exception {
        Position base = field.getAllPositions()[0][0];

        for (int i = 0; i < GameConstants.fieldHeight; ++i) {
            for (int j = 0; j < GameConstants.fieldWidth; ++j) {
                Position position = field.getPosition(base, j, i);
                Assert.assertEquals(position.getY(), i);
                Assert.assertEquals(position.getX(), j);
            }
        }
    }

    @Test
    public void testGetAllPositions() throws Exception {
        Position [][] positions = field.getAllPositions();

        Assert.assertEquals(positions.length, GameConstants.fieldHeight);
        Assert.assertEquals(positions[0].length, GameConstants.fieldWidth);

        
        for (int i = 0; i < GameConstants.fieldHeight; ++i) {
            for (int j = 0; j < GameConstants.fieldWidth; ++j) {
                Assert.assertEquals(positions[i][j].getY(), i);
                Assert.assertEquals(positions[i][j].getX(), j);
            }
        }
    }

    @Test
    public void testBFSNoPathExist() throws Exception {
        Position [][] positions = field.getAllPositions();
        new GameObjectAdder(positions[0][1]).add(new Pole(positions[0][1]));
        new GameObjectAdder(positions[1][1]).add(new Pole(positions[1][1]));
        new GameObjectAdder(positions[1][0]).add(new Pole(positions[1][0]));

        Assert.assertNull(field.BFS(positions[0][0], positions[2][2]));
    }

    @Test
    public void testBFSDummyPathLength() throws Exception {
        Position [][] positions = field.getAllPositions();

        // horizontal
        Assert.assertEquals(field.BFS(positions[0][0], positions[0][14]).size(), 14);
        Assert.assertEquals(field.BFS(positions[14][14], positions[14][0]).size(), 14);

        // vertical
        Assert.assertEquals(field.BFS(positions[0][10], positions[14][10]).size(), 14);
        Assert.assertEquals(field.BFS(positions[14][14], positions[0][14]).size(), 14);

        // diagonal
        Assert.assertEquals(field.BFS(positions[0][0], positions[14][14]).size(), 14);
        Assert.assertEquals(field.BFS(positions[14][0], positions[0][14]).size(), 14);
    }
}
