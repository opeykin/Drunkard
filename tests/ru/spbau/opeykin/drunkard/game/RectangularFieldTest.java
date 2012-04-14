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
    int fieldWidth = 15;
    int fieldHeight = 15;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField(fieldHeight, fieldWidth);
        positions = field.getAllPositions();
    }

    @Test
    public void testGetPosition() throws Exception {
        Position base = positions[0][0];

        for (int i = 0; i < fieldHeight; ++i) {
            for (int j = 0; j < fieldWidth; ++j) {
                Position position = field.getPosition(base, j, i);
                Assert.assertEquals(position.getY(), i);
                Assert.assertEquals(position.getX(), j);
            }
        }
    }

    @Test
    public void testWrongGetPosition() throws Exception {
        Position base = positions[0][0];

        try {
            field.getPosition(base, -1, 0);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(
                    e.getMessage(),
                    "Position [x=0, y=0] doesn't have neighbour with shifts X: -1 Y: 0");
        }
    }

    @Test
    public void testGetAllPositions() throws Exception {
        Assert.assertEquals(positions.length, 17);
        Assert.assertEquals(positions[0].length, 17);

        for (int i = 0; i < fieldHeight; ++i) {
            for (int j = 0; j < fieldWidth; ++j) {
                Assert.assertEquals(positions[i][j].getY(), i);
                Assert.assertEquals(positions[i][j].getX(), j);
            }
        }
    }

    @Test
    public void testBFSNoPathExist() throws Exception {
        new Pole(positions[1][2]);
        new Pole(positions[2][2]);
        new Pole(positions[2][1]);

        Assert.assertNull(field.BFS(positions[1][1], positions[3][3]));
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
        new Pole(positions[1][1]);
        new Pole(positions[2][1]);
        new Pole(positions[3][1]);
        new Pole(positions[4][1]);
        new Pole(positions[5][1]);

        new Pole(positions[1][3]);
        new Pole(positions[2][3]);
        new Pole(positions[3][3]);
        new Pole(positions[4][3]);
        new Pole(positions[5][3]);

        List<Position> BFSPath = field.BFS(positions[1][2], positions[4][2]);

        List<Position> truePath = new ArrayList<Position>();
        truePath.add(positions[2][2]);
        truePath.add(positions[3][2]);
        truePath.add(positions[4][2]);

        Assert.assertEquals(BFSPath, truePath);
    }
}
