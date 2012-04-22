package ru.spbau.opeykin.drunkard.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.objects.Pole;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 4/22/12
 */
public class BFSTest {
    private RectangularField field;
    private Position[][] positions;
    private final int fieldWidth = 15;
    private final int fieldHeight = 15;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField(fieldHeight, fieldWidth, new BFS());
        positions = field.getAllPositions();
    }

    @Test
    public void testNoPathExist() throws Exception {
        new Pole(positions[1][2]);
        new Pole(positions[2][2]);
        new Pole(positions[2][1]);

        Assert.assertNull(field.getRoute(positions[1][1], positions[3][3]));
    }

    @Test
    public void testDummyPathLength() throws Exception {
        // horizontal
        Assert.assertEquals(14, field.getRoute(positions[1][1], positions[1][15]).size());
        Assert.assertEquals(14, field.getRoute(positions[15][15], positions[15][1]).size());

        // vertical
        Assert.assertEquals(14, field.getRoute(positions[1][11], positions[15][11]).size());
        Assert.assertEquals(14, field.getRoute(positions[15][15], positions[1][15]).size());

        // diagonal
        Assert.assertEquals(28, field.getRoute(positions[1][1], positions[15][15]).size());
        Assert.assertEquals(28, field.getRoute(positions[15][1], positions[1][15]).size());
    }

    @Test
    public void testSearchOnRectangularField() throws Exception {
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

        List<Position> BFSPath = field.getRoute(positions[1][2], positions[4][2]);

        List<Position> truePath = new ArrayList<Position>();
        truePath.add(positions[2][2]);
        truePath.add(positions[3][2]);
        truePath.add(positions[4][2]);

        Assert.assertEquals(BFSPath, truePath);
    }
}
