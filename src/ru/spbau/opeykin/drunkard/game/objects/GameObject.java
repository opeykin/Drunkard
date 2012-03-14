package ru.spbau.opeykin.drunkard.game.objects;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;

/**
 * User: Alexander Opeykin
 * Date: 3/14/12
 */
public interface GameObject {
    void doTurn();
    void destroy();
    char getSymbol();
    InteractionResult askToAffect(GameObject gameObject);
    InteractionResult affect(AffectableGameObject gameObject);
}
