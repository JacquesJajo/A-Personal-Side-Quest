package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.MainGame;
import game.entities.battle.BattleEntity;

public class Fire extends Action
{

	private int mpCost = 5;
	
	public Fire(BattleEntity user)
	{
		super(user);
	}
	
	public Fire(BattleEntity user, BattleEntity target)
	{
		super(user, target);
	}

	@Override
	public void doAction(GameContainer gc, int delta)
	{
		effect = user.getIntelligence() * 2 - target.getIntelligence() / 4;
		if (user.getMp() >= mpCost)
		{
			user.decreaseMP(mpCost);
			target.damage(effect);
		}
		
	}

	@Override
	public String name()
	{
		return "Fire";
	}

	@Override
	public void onChoose()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderEffect(GameContainer gc, Graphics g)
	{
		g.drawString("Damaged " + (int) effect, 128 + 64, MainGame.SCREEN_HEIGHT - 128 + 16);
	}
	
}
