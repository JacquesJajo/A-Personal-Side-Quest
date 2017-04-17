package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.MainGame;
import game.entities.battle.BattleEntity;

public class Attack extends Action
{

	public Attack(BattleEntity user, BattleEntity target)
	{
		super(user, target);
	}
	
	public Attack(BattleEntity user)
	{
		super(user);
	}

	@Override
	public void doAction(GameContainer gc, int delta)
	{
		effect = user.getWeaponStrength() + user.getAttack() / 2;
		target.damage(effect); 
	}
	
	@Override
	public String name()
	{
		return "Attack";
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
