package game.entities.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.gfx.Assets;
import game.states.battle.BattleState;

public class BattleEnemy extends BattleEntity
{

	private float dir = 1;
	private float orig;

	public BattleEnemy(BattleState battle, float x, float y, String name, int health, int attack, int speed, int mp,
			int intelligence)
	{
		super(battle, x, y, name, health, attack, speed, mp, intelligence);
		orig = x;
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (!isDead())
		{
			g.setColor(Color.red);
			g.drawImage(Assets.skeleton, x, y);
		}
		
		else
		{
			g.drawImage(Assets.deadSkeleton, x, y);
		}
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		animate = true;
		x += dir;
		
		if (x > orig + 64)
		{
			dir = -1;
			battleAction(gc, delta);
		}
		
		if (x == orig)
		{
			dir = 1;
			animate = false;
		}
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
