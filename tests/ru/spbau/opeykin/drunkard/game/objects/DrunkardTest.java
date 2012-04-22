package ru.spbau.opeykin.drunkard.game.objects;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import ru.spbau.opeykin.drunkard.game.BFS;
import ru.spbau.opeykin.drunkard.game.Interaction;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.RectangularField;

import java.util.Arrays;

/**
 * User: Alexander Opeykin
 * Date: 3/19/12
 */
public class DrunkardTest {
    private RectangularField field;
    private Position[][] positions;
    private Drunkard drunkard;
    private Position position;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField(15, 15, new BFS());
        positions = field.getAllPositions();
        position = positions[1][1];
        drunkard = new Drunkard(position);
    }

    @Test
    public void testSleepAgainstPole() throws Exception {
        Pole mPole = mock(Pole.class);
        assertEquals(drunkard.getAffected(mPole), Interaction.InteractionResult.KEEP_BOTH);
        assertEquals(drunkard.getState(), Drunkard.State.SLEEP_STANDING);
        assertEquals(drunkard.getSymbol(), '1');
    }


    @Test
    public void testSleepAgainstBottle() throws Exception {
        Bottle mBottle = mock(Bottle.class);
        assertEquals(drunkard.getAffected(mBottle), Interaction.InteractionResult.REPLACE_HOST);
        assertEquals(drunkard.getState(), Drunkard.State.SLEEP_LYING);
        assertEquals(drunkard.getSymbol(), '&');
    }

    
    @Test
    public void testFreeMove() throws Exception {
        Position mPosition1 = mock(Position.class);
        Position mPosition2 = mock(Position.class);

        when(mPosition1.getNeighbours()).thenReturn(Arrays.asList(mPosition2));

        when(mPosition2.isFree()).thenReturn(true);

        Drunkard drunkard1 = new Drunkard(mPosition1);
        drunkard1.doTurn();

        assertEquals(drunkard1.getPosition(), mPosition2);
        assertEquals(drunkard1.getState(), Drunkard.State.ACTIVE);
        assertEquals(drunkard1.getSymbol(), '@');
    }


    @Test
    public void testDoNotSleepAgainstLamp() throws Exception {
        Lamp mLamp = mock(Lamp.class);

        drunkard.getAffected(mLamp);

        assertEquals(drunkard.getState(), Drunkard.State.ACTIVE);
        assertEquals(drunkard.getSymbol(), '@');
    }

    @Test
    public void testDoNotSleepAgainstDrunkard() throws Exception {
        Drunkard mDrunkard = mock(Drunkard.class);
        when(mDrunkard.getState()).thenReturn(Drunkard.State.ACTIVE);

        drunkard.getAffected(mDrunkard);

        assertEquals(drunkard.getState(), Drunkard.State.ACTIVE);
        assertEquals(drunkard.getSymbol(), '@');
    }
}
