package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;

import game.entities.battle.BattleEntity;

public class Fire extends Action
{

	public Fire(BattleEntity user, BattleEntity target)
	{
		super(user, target);
	}

	@Override
	public void doAction(GameContainer gc, int delta)
	{
		target.damage(10);
	}

}
