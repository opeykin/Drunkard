package ru.spbau.opeykin.drunkard.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;


/**
 * User: Alexander Opeykin
 * Date: 3/18/12
 */
public class RectangularFieldTest {
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


}
