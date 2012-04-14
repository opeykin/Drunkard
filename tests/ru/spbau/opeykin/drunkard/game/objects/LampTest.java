package ru.spbau.opeykin.drunkard.game.objects;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.GameObjectAdder;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.RectangularField;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: Alexander Opeykin
 * Date: 3/20/12
 */
public class LampTest {
    RectangularField field;
    Position[][] positions;

    @Before
    public void prepare() throws Exception {
        field = new RectangularField();
        positions = field.getAllPositions();
    }

    @Test
    public void testPoliceDepartmentNotification() throws Exception {
        // set bottles around to guarantee drunkard falling
        new GameObjectAdder(positions[1][0]).add(new Bottle(positions[1][0]));
        new GameObjectAdder(positions[1][2]).add(new Bottle(positions[1][2]));
        new GameObjectAdder(positions[0][1]).add(new Bottle(positions[0][1]));
        new GameObjectAdder(positions[2][1]).add(new Bottle(positions[2][1]));

        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        PoliceDepartment policeDepartment = mock(PoliceDepartment.class);

        new GameObjectAdder(
                positions[2][2]).add(new Lamp(positions[2][2], policeDepartment, 2));

        drunkard.doTurn();

        verify(policeDepartment).onEvent(drunkard);
    }
}
