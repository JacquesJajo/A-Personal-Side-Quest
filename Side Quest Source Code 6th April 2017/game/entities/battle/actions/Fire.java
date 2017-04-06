package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;

import game.entities.battle.BattleEntity;

public class Fire extends Action
{

	private int mpCost = 5;
	
	public Fire(BattleEntity user)
	{
		super(user);
	}
	
	public Fire(BattleEntity user, BattleEntity target)
	{
		super(user, target);
	}

	@Override
	public void doAction(GameContainer gc, int delta)
	{
		if (user.getMp() >= mpCost)
		{
			user.decreaseMP(mpCost);
			target.damage(10);
		}
		
	}

	@Override
	public String name()
	{
		return "Fire";
	}
	
}
