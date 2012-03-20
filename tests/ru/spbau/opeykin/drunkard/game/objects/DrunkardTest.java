package ru.spbau.opeykin.drunkard.game.objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.GameObjectAdder;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.RectangularField;

/**
 * User: Alexander Opeykin
 * Date: 3/19/12
 */
public class DrunkardTest {
    RectangularField field;
    Position[][] positions;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField();
        positions = field.getAllPositions();
    }

    @Test
    public void testSleepAgainstPole() throws Exception{
        new GameObjectAdder(positions[1][0]).add(new Pole(positions[1][0]));
        new GameObjectAdder(positions[1][2]).add(new Pole(positions[1][2]));
        new GameObjectAdder(positions[0][1]).add(new Pole(positions[0][1]));
        new GameObjectAdder(positions[2][1]).add(new Pole(positions[2][1]));

        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        drunkard.doTurn();

        assertEquals(drunkard.getState(), Drunkard.State.SLEEP_STANDING);
        assertEquals(drunkard.getSymbol(), '1');
    }


    @Test
    public void testSleepAgainstBottle() throws Exception{
        new GameObjectAdder(positions[1][0]).add(new Bottle(positions[1][0]));
        new GameObjectAdder(positions[1][2]).add(new Bottle(positions[1][2]));
        new GameObjectAdder(positions[0][1]).add(new Bottle(positions[0][1]));
        new GameObjectAdder(positions[2][1]).add(new Bottle(positions[2][1]));

        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        drunkard.doTurn();

        assertEquals(drunkard.getState(), Drunkard.State.SLEEP_LYING);
        assertEquals(drunkard.getSymbol(), '&');
    }

    
    @Test
    public void testFreeMove() throws Exception{
        Position startPosition = positions[1][1];
        Drunkard drunkard = new Drunkard(startPosition);
        new GameObjectAdder(startPosition).add(drunkard);

        drunkard.doTurn();

        assertThat(startPosition, not(equalTo(drunkard.getPosition())));
        assertEquals(drunkard.getState(), Drunkard.State.ACTIVE);
        assertEquals(drunkard.getSymbol(), '@');
    }


    @Test
    public void testDoNotSleepAgainstLamp() throws Exception{
        new GameObjectAdder(positions[1][0]).add(new Lamp(positions[1][0], field.getPoliceDepartment(),1));
        new GameObjectAdder(positions[1][2]).add(new Lamp(positions[1][2], field.getPoliceDepartment(),1));
        new GameObjectAdder(positions[0][1]).add(new Lamp(positions[0][1], field.getPoliceDepartment(),1));
        new GameObjectAdder(positions[2][1]).add(new Lamp(positions[2][1], field.getPoliceDepartment(),1));

        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        drunkard.doTurn();

        assertEquals(drunkard.getState(), Drunkard.State.ACTIVE);
        assertEquals(drunkard.getSymbol(), '@');
    }

    @Test
    public void testDoNotSleepAgainstDrunkard() throws Exception{
        new GameObjectAdder(positions[1][0]).add(new Drunkard(positions[1][0]));
        new GameObjectAdder(positions[1][2]).add(new Drunkard(positions[1][2]));
        new GameObjectAdder(positions[0][1]).add(new Drunkard(positions[0][1]));
        new GameObjectAdder(positions[2][1]).add(new Drunkard(positions[2][1]));

        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        drunkard.doTurn();

        assertEquals(drunkard.getState(), Drunkard.State.ACTIVE);
        assertEquals(drunkard.getSymbol(), '@');
    }
}
