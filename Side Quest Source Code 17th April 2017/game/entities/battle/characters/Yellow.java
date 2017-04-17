package game.entities.battle.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.battle.BattleEntity;
import game.entities.battle.actions.Fire;
import game.entities.battle.actions.Heal;
import game.gfx.Assets;
import game.states.battle.BattleState;

public class Yellow extends BattleEntity
{
	private static int maxHealth = 125,
			maxMp = 30,
			attack = 25,
			speed = 10,
			intelligence = 15;
			
			private Animation animation;
			private float dir = -1;
			private float orig;
			
			public Yellow(BattleState battle, float x, float y, String name, int health, int mp)
			{
				super(battle, x, y, name, health, maxHealth, attack, speed, mp, maxMp, intelligence);
				animation = new Animation(Assets.yellowWalkLeft, 125);
				orig = x;
				techList.add(new Fire(this));
				techList.add(new Heal(this));
			}

			@Override
			public void render(GameContainer gc, Graphics g)
			{
				if (!isDead())
				{
					if (animate)
						animation.draw(x, y);
					
					else g.drawImage(Assets.yellow, x, y);
				}
			}

			@Override
			public void update(GameContainer gc, int delta)
			{
				animate = true;
				x += dir;
				
				if (x < orig - 64)
				{
					battleAction(gc, delta);
					dir = 1;
				}
				
				if (x == orig)
				{
					dir = -1;
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
				return true;
			}
}
