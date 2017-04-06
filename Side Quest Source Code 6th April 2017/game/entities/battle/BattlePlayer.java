package game.entities.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.gfx.Assets;
import game.states.BattleState;

public class BattlePlayer extends BattleEntity
{

	public BattlePlayer(BattleState battle, float x, float y, int health, int attack, int speed, String name)
	{
		super(battle, x, y, health, attack, speed, name, 10);
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if (!isDead())
		{
			g.setColor(Color.white);
			
			if (choosing) g.setColor(Color.green);
			
			//g.drawString("Health: " + health, x, y + 64);
			g.drawImage(Assets.player, x, y);
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
		return true;
	}

}
