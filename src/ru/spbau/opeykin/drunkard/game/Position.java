package ru.spbau.opeykin.drunkard.game;

import ru.spbau.opeykin.drunkard.game.objects.GameObject;
import ru.spbau.opeykin.drunkard.game.objects.GameObject.InteractionResult;
import ru.spbau.opeykin.drunkard.game.objects.Listener;



/**
 * Represents cell of the game field. Store objects staying on it.
 * 
 * @author Opeykin Alexander
 *
 */
public class Position {
		
	private GameObject host;
	
	private Listener listener;
	
	private Field field;
	
	private int x;
	
	private int y;
	

	Position(Field field, int x, int y) {
		this.field = field;
		this.x = x;
		this.y = y;
	}
	
	
	public Position getPosition(int shiftX, int shiftY) {
		return field.getPosition(this, shiftX, shiftY);
	}
	
	
	char getSymbol() {
		if (host == null) {
			return 'o';
		} else {
			return host.getSymbol();
		}
	}
	
	int getX() {
		return x;
	}
	

	int getY() {
		return y;
	}
	
	public Field getField() {
		return field;
	}
	

	boolean isFree() {
		return host == null;
	}
	
	GameObject getObject() {
		return host;
	}
	

	void releaseObject() {
		host = null;
	}
	
	
	void setListener(Listener listener) {
		if (this.listener != null) {
			System.err.println("tryint to replace existing listener: " +
							   this.listener.toString() + 
							   " with " + listener.toString());
			System.exit(1);
		}
		
		
		//System.out.println("set listener to " + this.toString());
		if (listener == null) {
			System.out.println("NULL");
			System.exit(1);
		}
		
		this.listener = listener;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    void setObject(GameObject visitor) {
		if (host != null) {
			System.err.println(
					"Attempt to place " + visitor.toString() + 
					" at busy " + this.toString());
			System.exit(1);
		}
		host = visitor;
		
		
		if (listener != null) {
			listener.register(host);
		}
	}
	

	public void destroyObject() {
		if (host != null) {
			host.destroy();
			host = null;	
		}
	}

	
	/**
	 * try to make step to this position
	 * 
	 * @param source position from which step is made
	 * @return {@link InteractionResult}
	 */
	GameObject.InteractionResult step(Position source) {
		if (host == null) {
			return GameObject.InteractionResult.REPLACE_HOST;
		} else {
			return host.affect(source.host);			
		}		
	}
	
	
	void denudeContent(Position position) {
		destroyObject();
		setObject(position.host);
		position.host = null;
	}


	@Override
	public String toString() {
		return "Position [x=" + new Integer(x).toString()
				+ ", y=" + new Integer(y).toString() + "]";
	}


}
