package game.states;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.battle.actions.Action;

public class BattleExecute extends State
{

	private List<Action> actions;
	private BattleState parent;
	private Action[] actionArray;
	
	public BattleExecute(BattleState parent, List<Action> actions)
	{
		this.actions = actions;
		this.parent = parent;
		
		actionArray = toArray(this.actions);
		bubbleSort(actionArray);
		
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		parent.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		for (int i = 0; i < actionArray.length; i++)
		{
			if(!actionArray[i].getUser().isDead())
				actionArray[i].doAction(gc, delta);
		}
		
		parent.getActions().clear();
		State.enterState(parent);
	}
	
	public void bubbleSort(Action[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr.length - i - 1; j++)
			{
				if (arr[j].getUser().getSpeed() < arr[j + 1].getUser().getSpeed())
				{
					Action temp = arr[j];
					arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
				}
			}
		}
		
	}
	
	private Action[] toArray(List<Action> actions)
	{
		int n = actions.size();
		
		Action[] arr = new Action[n];
		
		for (int i = 0; i < n; i++)
			arr[i] = actions.get(i);
		
		return arr;
	}

	@Override
	public void onEnter()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void onExit()
	{
		// TODO Auto-generated method stub
	}
	
}
