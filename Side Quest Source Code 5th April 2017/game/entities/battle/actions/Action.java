package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;

import game.entities.battle.BattleEntity;

public abstract class Action
{
	
	protected BattleEntity user, target;
	
	public Action(BattleEntity user, BattleEntity target)
	{
		this.user = user;
		this.target = target;
	}
	
	public abstract void doAction(GameContainer gc, int delta);

	public BattleEntity getUser()
	{
		return user;
	}

	public BattleEntity getTarget()
	{
		return target;
	}
	
}
