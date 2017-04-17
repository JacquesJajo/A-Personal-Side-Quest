package game.entities.battle.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.battle.BattleEntity;

public abstract class Action
{
	
	/*
	 * Formulae:
	 * Spell Strength = intelligence * modifier
	 * Attack Strength = weapon attack + attack / 2
	 */
	
	protected BattleEntity user, target;
	protected float effect;
	
	public Action(BattleEntity user, BattleEntity target)
	{
		this.user = user;
		this.target = target;
	}
	
	public Action(BattleEntity user)
	{
		this.user = user;
	}
	
	public abstract void doAction(GameContainer gc, int delta);
	public abstract void onChoose();
	public abstract void renderEffect(GameContainer gc, Graphics g);	

	public BattleEntity getUser()
	{
		return user;
	}

	public BattleEntity getTarget()
	{
		return target;
	}

	public void setUser(BattleEntity user)
	{
		this.user = user;
	}

	public void setTarget(BattleEntity target)
	{
		this.target = target;
	}
	
	public String name()
	{
		return "Action";
	}

	public float getEffect()
	{
		return effect;
	}
	
}
