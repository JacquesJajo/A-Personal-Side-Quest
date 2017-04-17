package game.entities.battle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.gfx.Assets;
import game.states.battle.BattleState;

public class BattlePlayer extends BattleEntity
{

	public BattlePlayer(BattleState battle, float x, float y, String name, int health, int attack, int speed, int mp,
			int intelligence)
	{
		super(battle, x, y, name, health, attack, speed, mp, intelligence);
		animation = new Animation(Assets.pinkWalkLeft, 125);
		orig = x;
	}

	private Animation animation;
	private float dir = -1;
	private float orig;
	
	

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (!isDead())
		{
			//g.setColor(Color.white);
			
			//if (choosing) g.setColor(Color.green);
			
			//g.drawString("Health: " + health, x, y + 64);
			
			if (animate)
				animation.draw(x, y);
			
			else g.drawImage(Assets.pink, x, y);
		}  
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		this.setAnimate(true);
		this.setX(this.getX() + dir);
		
		if (this.getX() < orig - 64)
		{
			action.doAction(gc, delta); 
			dir = 1;
		}
		
		if (this.getX() == orig)
		{
			dir = -1;
			this.setAnimate(false);
			//State.enterState(parent);
			//if (parent.getParent().getNumEnemies() <= 0 || parent.getParent().numPlayers() <= 0)  
				//State.enterState(parent.getParent().over);
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
