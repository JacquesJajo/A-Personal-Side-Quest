package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;

import game.entities.battle.BattleEntity;

public class Heal extends Action
{

	public Heal(BattleEntity user, BattleEntity target)
	{
		super(user, target);
	}

	@Override
	public void doAction(GameContainer gc, int delta)
	{
		
	}

}
