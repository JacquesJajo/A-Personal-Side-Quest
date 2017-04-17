package game.entities.battle.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.battle.BattleEntity;
import game.entities.battle.actions.Fire;
import game.gfx.Assets;
import game.states.battle.BattleState;

public class Skeleton extends BattleEntity
{
	
	private static int maxHealth = 50, health = 50,
			maxMp = 15, mp = 15,
			attack = 6,
			speed = 4,
			intelligence = 8;
	
	private Animation animation;
	private float dir = 1;
	private float orig;

	public Skeleton(BattleState battle, float x, float y)
	{
		super(battle, x, y, "Skeleton", health, maxHealth, attack, speed, mp, maxMp, intelligence);
		orig = x;
		animation = new Animation(Assets.skeletonWalkRight, 125);
		techList.add(new Fire(this));
		//techList.add(new Heal(this));
	}

	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (!isDead())
		{
			g.setColor(Color.red);
			if (animate)
				animation.draw(x, y);
			else g.drawImage(Assets.skeleton, x, y);
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
