package ru.spbau.opeykin.drunkard.game.objects;

import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.Position;

import static org.mockito.Mockito.*;


/**
 * User: Alexander Opeykin
 * Date: 3/20/12
 */
public class LampTest {
    @Test
    public void testAddingListener() throws Exception {
        Position mPosition = mock(Position.class);
        PoliceDepartment mPolice = mock(PoliceDepartment.class);

        when(mPosition.hasNeighbour(anyInt(), anyInt())).thenReturn(true);
        when(mPosition.getPosition(anyInt(), anyInt())).thenReturn(mPosition);

        Lamp lamp = new Lamp(mPosition, 1);
        lamp.addListener(mPolice);

        verify(mPosition, times(8)).addListener(mPolice);
    }
}
