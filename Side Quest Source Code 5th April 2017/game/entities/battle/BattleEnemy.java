package game.entities.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.gfx.Assets;
import game.states.BattleState;

public class BattleEnemy extends BattleEntity
{

	public BattleEnemy(BattleState battle, float x, float y, int health, int attack, int speed)
	{
		super(battle, x, y, health, attack, speed, "Skeleton");
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (!isDead())
		{
			g.setColor(Color.red);
			g.drawImage(Assets.skeleton, x, y);
			//g.drawString("Health: " + health, x, y + 32);
		}
		
		else
		{
			g.drawImage(Assets.deadSkeleton, x, y);
		}
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		
	}

	@Override
	public void battleAction(GameContainer gc, int delta)
	{
		action.doAction(gc, delta);
	}

	@Override
	public boolean playerControlled()
	{
		return false;
	}
}
