package ru.spbau.opeykin.drunkard.game.objects;

import static ru.spbau.opeykin.drunkard.game.Interaction.InteractionResult;
import ru.spbau.opeykin.drunkard.game.Position;


public class Policeman extends RouteGoingGameObject {

    Position returnPosition;
	
	private boolean complete = false;


    public Policeman(Position position, Position target, Position returnPosition) {
		super(position, target);
        this.returnPosition = returnPosition;
	}


	@Override
	public InteractionResult affect(AffectableGameObject gameObject) {
		return gameObject.getAffected(this);		
	}
	
	@Override
    protected void updateRoute() {
		if (complete) {
			route = getRoute(returnPosition);
		} else {
			route = getRoute(target);
		}
	}


	@Override
	public void doTurn() {
		if (complete && getPosition() == returnPosition) {
			finish();
			return;
		}

        goRoute();
	}

    protected void finish() {
        getPosition().releaseHost(); // mission accomplished
    }

    @Override
    protected void leavePosition(Position leavedPosition) {
        route.pollFirst();
    }

	@Override
    InteractionResult getAffected(Drunkard drunkard) {
		if (drunkard.getPosition() == target) {
			complete = true;
			return InteractionResult.REPLACE_HOST;
		}
		updateRoute();
		return InteractionResult.KEEP_BOTH;
	}



    //
//	@Override
//    InteractionResult getAffected(Lamp lamp) {
//		updateRoute();
//		return InteractionResult.KEEP_BOTH;
//	}
//
//
//	@Override
//    InteractionResult getAffected(Pole pole) {
//		updateRoute();
//		return InteractionResult.KEEP_BOTH;
//	}
//
//
//	@Override
//    InteractionResult getAffected(Policeman policeman) {
//		updateRoute();
//		return InteractionResult.KEEP_BOTH;
//	}
//
//
//	@Override
//    InteractionResult getAffected(Bottle bottle) {
//		updateRoute();
//		return InteractionResult.KEEP_BOTH;
//	}


	@Override
	public char getSymbol() {
		return '!';
	}
}
