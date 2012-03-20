package ru.spbau.opeykin.drunkard.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;
import ru.spbau.opeykin.drunkard.game.objects.Pole;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 3/18/12
 */
public class RectangularFieldTest {
    RectangularField field;
    Position[][] positions;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField();
        positions = field.getAllPositions();
    }

    @Test
    public void testGetPosition() throws Exception {
        Position base = positions[0][0];

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
        new GameObjectAdder(positions[0][1]).add(new Pole(positions[0][1]));
        new GameObjectAdder(positions[1][1]).add(new Pole(positions[1][1]));
        new GameObjectAdder(positions[1][0]).add(new Pole(positions[1][0]));

        Assert.assertNull(field.BFS(positions[0][0], positions[2][2]));
    }

    @Test
    public void testBFSDummyPathLength() throws Exception {
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

    @Test
    public void testBFS() throws Exception {
        new GameObjectAdder(positions[1][1]).add(new Pole(positions[1][1]));
        new GameObjectAdder(positions[2][1]).add(new Pole(positions[2][1]));    
        new GameObjectAdder(positions[3][1]).add(new Pole(positions[3][1]));    
        new GameObjectAdder(positions[4][1]).add(new Pole(positions[4][1]));    
        new GameObjectAdder(positions[5][1]).add(new Pole(positions[5][1]));

        new GameObjectAdder(positions[1][3]).add(new Pole(positions[1][3]));
        new GameObjectAdder(positions[2][3]).add(new Pole(positions[2][3]));
        new GameObjectAdder(positions[3][3]).add(new Pole(positions[3][3]));
        new GameObjectAdder(positions[4][3]).add(new Pole(positions[4][3]));
        new GameObjectAdder(positions[5][3]).add(new Pole(positions[5][3]));

        List<Position> BFSPath = field.BFS(positions[1][2], positions[4][2]);

        List<Position> truePath = new ArrayList<Position>();
        truePath.add(positions[2][2]);
        truePath.add(positions[3][2]);
        truePath.add(positions[4][2]);

        Assert.assertEquals(BFSPath, truePath);
    }
}
