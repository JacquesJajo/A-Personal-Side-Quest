package game.states;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class State
{
	
	private static State currentState;
	
	public static void enterState(State state)
	{
		if (currentState != null)
			currentState.onExit();
		currentState = state;
		currentState.onEnter();
	}
	
	public static State getCurrentState()
	{
		return currentState;
	}
	
	protected Random random = new Random();
	
	public abstract void render(GameContainer gc, Graphics g);
	public abstract void update(GameContainer gc, int delta);
	public abstract void onEnter();
	public abstract void onExit();
}
