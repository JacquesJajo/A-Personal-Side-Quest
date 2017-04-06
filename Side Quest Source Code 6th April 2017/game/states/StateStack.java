package game.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class StateStack
{

	public StateMachine machine;
	private List<State> states;
	
	public StateStack()
	{
		states = new ArrayList<State>();
	}
	
	public void render(GameContainer gc, Graphics g)
	{
		State top = states.get(0);
		top.render(gc, g);
	}

	public void update(GameContainer gc, int delta)
	{
		State top = states.get(0);
		top.update(gc, delta);
	}
	
	public void push(String name)
	{
		State state = machine.get(name);
		states.add(0, state);
	}
	
	public void pop()
	{
		states.remove(0);
	}
}
