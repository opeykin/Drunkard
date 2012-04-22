package ru.spbau.opeykin.drunkard.game.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.BFS;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.RectangularField;

/**
 * User: Alexander Opeykin
 * Date: 3/20/12
 */
public class PolicemanTest {
    RectangularField field;
    Position[][] positions;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField(15, 15, new BFS());
        positions = field.getAllPositions();
    }

    @Test
    public void testDestroyDrunkard() throws Exception {
        Drunkard drunkard = new Drunkard(positions[2][1]);

        Policeman policeman = new Policeman(positions[1][1], drunkard.getPosition(),positions[1][1]);

        Assert.assertNotNull(drunkard.getPosition());
        policeman.doTurn();
        Assert.assertEquals(policeman.getPosition(), positions[2][1]);
        Assert.assertNull(drunkard.getPosition());
    }

    @Test
    public void testDoNothingIfPathIsBroken() throws Exception {
        Drunkard drunkard = new Drunkard(positions[1][3]);

        Position policemanPosition = positions[1][1];
        Policeman policeman = new Policeman(policemanPosition, drunkard.getPosition(),policemanPosition);

        Position pole1Position = positions[2][1];
        Position pole2Position = positions[1][2];
        Position pole3Position = positions[2][2];
        
        Pole pole1 = new Pole(pole1Position);
        Pole pole2 = new Pole(pole2Position);
        Pole pole3 = new Pole(pole3Position);

        // stood still
        policeman.doTurn();
        Assert.assertEquals(policemanPosition, policeman.getPosition());

        // broke nothing
        Assert.assertNotNull(pole1.getPosition());
        Assert.assertNotNull(pole2.getPosition());
        Assert.assertNotNull(pole3.getPosition());
        Assert.assertNotNull(drunkard.getPosition());

        // open path again
        pole2Position.releaseHost();

        // go
        policeman.doTurn();
        policeman.doTurn();

        // drunkard destroyed
        Assert.assertNull(drunkard.getPosition());
    }
}
