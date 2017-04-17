package game.states;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class StateMachine
{
	
	private Map<String, State> states;
	public State currentState;

	public StateMachine()
	{
		states = new HashMap<String, State>();
	}
	
	public void render(GameContainer gc, Graphics g)
	{
		currentState.render(gc, g);
	}

	public void update(GameContainer gc, int delta)
	{
		currentState.update(gc, delta);
	}
	
	public void add(String name, State state)
	{
		states.put(name, state);
	}
	
	public void enterState(String name)
	{
		currentState = states.get(name);
	}
	
	public State get(String name)
	{
		return states.get(name);
	}

}
