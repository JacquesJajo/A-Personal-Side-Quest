package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.MainGame;
import game.entities.battle.BattleEntity;

public class Heal extends Action
{

	public Heal(BattleEntity user)
	{
		super(user);
	}
	
	@Override
	public void doAction(GameContainer gc, int delta)
	{
		effect = 3 * user.getIntelligence();
		if (user.getMp() >= 10)
		{
			user.decreaseMP(10);
			user.setHealth((int) (user.getHealth() + effect));
			
			if (user.getHealth() > user.getMaxHealth()) user.setHealth(user.getMaxHealth());
		}
	}

	@Override
	public String name()
	{
		return "Heal";
	}

	@Override
	public void onChoose()
	{
		user.mustChoose = false;
	}

	@Override
	public void renderEffect(GameContainer gc, Graphics g)
	{
		g.drawString("Healed " + (int) effect, 128 + 64, MainGame.SCREEN_HEIGHT - 128 + 16);
	}
	
}
