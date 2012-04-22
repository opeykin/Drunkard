package ru.spbau.opeykin.drunkard.game.objects;

import ru.spbau.opeykin.drunkard.game.Position;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;

/**
 * User: Alexander Opeykin
 * Date: 3/14/12
 */
public interface GameObject {
    void doTurn();
    void destroy();
    char getSymbol();
    public Position getPosition();
    InteractionResult askToAffect(GameObject gameObject);
    InteractionResult affect(AffectableGameObject gameObject);
}
