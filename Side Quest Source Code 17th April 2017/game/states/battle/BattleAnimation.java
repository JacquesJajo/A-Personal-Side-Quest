package game.states.battle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.battle.actions.Action;
import game.states.State;

public class BattleAnimation extends State
{

	private Action action;
	private BattleExecute parent;
	
	public BattleAnimation(Action action, BattleExecute parent)
	{
		this.action = action;
		this.parent = parent;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		parent.render(gc, g);
		
		if (action.getEffect() != 0)
			action.renderEffect(gc, g);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		
		action.getUser().update(gc, delta);
		
		if (!action.getUser().isAnimate())
		{
			action.getUser().mustChoose = true;
			State.enterState(parent);
			if (parent.getParent().getNumEnemies() <= 0 || parent.getParent().numPlayers() <= 0)  
				State.enterState(parent.getParent().over);
		}
	}

	@Override
	public void onEnter()
	{
		
	}

	@Override
	public void onExit()
	{
		
	}

}
