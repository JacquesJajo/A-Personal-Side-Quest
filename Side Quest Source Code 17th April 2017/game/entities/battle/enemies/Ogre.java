package game.entities.battle.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.battle.BattleEntity;
import game.gfx.Assets;
import game.states.battle.BattleState;

public class Ogre extends BattleEntity
{
	
	private static int maxHealth = 60, health = 60,
			maxMp = 0, mp = 0,
			attack = 12,
			speed = 1,
			intelligence = 1;
	
	private Animation animation;
	private float dir = 1;
	private float orig;

	public Ogre(BattleState battle, float x, float y)
	{
		super(battle, x, y, "Ogre", health, maxHealth, attack, speed, mp, maxMp, intelligence);
		orig = x;
		animation = new Animation(Assets.ogreWalkRight, 125);
	}

	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (!isDead())
		{
			g.setColor(Color.red);
			if (animate)
				animation.draw(x, y);
			else g.drawImage(Assets.ogre, x, y);
		}
		
		else
		{
			g.drawImage(Assets.deadOgre, x, y);
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
