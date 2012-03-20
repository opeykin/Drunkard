package ru.spbau.opeykin.drunkard.game.objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.GameObjectAdder;
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
        field = new RectangularField();
        positions = field.getAllPositions();
    }

    @Test
    public void testDestroyDrunkard() throws Exception {
        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        Policeman policeman = new Policeman(positions[0][0], drunkard.getPosition(),positions[0][0]);
        new GameObjectAdder(positions[0][0]).add(policeman);

        Assert.assertNotNull(drunkard.getPosition());
        policeman.doTurn();
        Assert.assertNull(drunkard.getPosition());
    }

    @Test
    public void testDoNothingIfPathIsBroken() throws Exception {
        Drunkard drunkard = new Drunkard(positions[2][2]);
        new GameObjectAdder(positions[2][2]).add(drunkard);

        Position policemanPosition = positions[0][0];
        Policeman policeman = new Policeman(policemanPosition, drunkard.getPosition(),policemanPosition);
        new GameObjectAdder(policemanPosition).add(policeman);
        
        Position pole1Position = positions[1][0];
        Position pole2Position = positions[0][1];
        Position pole3Position = positions[1][1];
        
        Pole pole1 = new Pole(pole1Position);
        Pole pole2 = new Pole(pole2Position);
        Pole pole3 = new Pole(pole3Position);

        // broke path with poles
        new GameObjectAdder(pole1Position).add(pole1);
        new GameObjectAdder(pole2Position).add(pole2);
        new GameObjectAdder(pole3Position).add(pole3);

        // stood still
        policeman.doTurn();
        Assert.assertEquals(policemanPosition, policeman.getPosition());

        // broke nothing
        Assert.assertNotNull(pole1.getPosition());
        Assert.assertNotNull(pole2.getPosition());
        Assert.assertNotNull(pole3.getPosition());
        Assert.assertNotNull(drunkard.getPosition());

        // open path again
        pole3Position.releaseHost();

        // go
        policeman.doTurn();
        policeman.doTurn();

        // drunkard destroyed
        Assert.assertNull(drunkard.getPosition());
    }
}
