package ru.spbau.opeykin.drunkard.game.objects;

import org.junit.Assert;
import org.junit.Test;
import ru.spbau.opeykin.drunkard.game.GameObjectAdder;
import ru.spbau.opeykin.drunkard.game.Position;
import ru.spbau.opeykin.drunkard.game.RectangularField;

/**
 * User: Alexander Opeykin
 * Date: 3/20/12
 */
public class PolicemanTest {
    @Test
    public void testAffect() throws Exception {

    }

    @Test
    public void testDestroyDrunkard() throws Exception {
        RectangularField field = new RectangularField();

        Position[][] positions = field.getAllPositions();

        // set bottles around drunkard
        new GameObjectAdder(positions[1][0]).add(new Bottle(positions[1][0]));
        new GameObjectAdder(positions[1][2]).add(new Bottle(positions[1][2]));
        new GameObjectAdder(positions[0][1]).add(new Bottle(positions[0][1]));
        new GameObjectAdder(positions[2][1]).add(new Bottle(positions[2][1]));

        Drunkard drunkard = new Drunkard(positions[1][1]);
        new GameObjectAdder(positions[1][1]).add(drunkard);

        drunkard.doTurn(); // should sleep lying

        Policeman policeman = new Policeman(positions[0][0], drunkard.getPosition(),positions[0][0]);
        new GameObjectAdder(positions[0][0]).add(policeman);

        Assert.assertNotNull(drunkard.getPosition());
        policeman.doTurn();
        policeman.doTurn();
        Assert.assertNull(drunkard.getPosition());
    }
}
