package game.states;

import java.util.ArrayList;
import java.util.List;

public class StateStack
{

	private List<State> states;
	
	public StateStack()
	{
		states = new ArrayList<State>();
	}
	
	public void setStateToTop()
	{
		State top = states.get(0);
		State.enterState(top);
	}
	
	public void push(State state)
	{
		states.add(0, state);
	}
	
	public void pop()
	{
		states.remove(0);
	}
}
