package game.entities.battle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.states.battle.BattleState;

public class Placeholder extends BattleEntity
{

	public static final String name = "Empty";
	
	public Placeholder(BattleState battle)
	{
		super(battle, 0, 0, name, 1000, 0, 0, 0, 0);
	}

	@Override
	public void battleAction(GameContainer gc, int delta)
	{
		
	}

	@Override
	public boolean playerControlled()
	{
		return false;
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		
	}

}
