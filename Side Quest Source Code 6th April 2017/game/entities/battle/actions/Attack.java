package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;

import game.entities.battle.BattleEntity;

public class Attack extends Action
{

	public Attack(BattleEntity user, BattleEntity target)
	{
		super(user, target);
	}
	
	public Attack(BattleEntity user)
	{
		super(user);
	}

	@Override
	public void doAction(GameContainer gc, int delta)
	{
		user.attack(target);
	}
	
	@Override
	public String name()
	{
		return "Attack";
	}
}
