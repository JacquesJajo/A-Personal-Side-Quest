package game.entities.battle;

import game.entities.Entity;
import game.states.Battle;

public abstract class BattleEntity extends Entity
{
	
	protected int health, attack;
	protected Battle battle;
	
	public BattleEntity(Battle battle, float x, float y, int health, int attack)
	{
		super(x, y);
		this.health = health;
		this.attack = attack;
		this.battle = battle;
	}
	
	public abstract void battleAction();
	
	public boolean isDead()
	{
		if (health <= 0) return true;
		
		return false;
	}
	
	public void damage(BattleEntity enemy)
	{
		this.health -= enemy.getAttack();
	}

	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getAttack()
	{
		return attack;
	}

	public void setAttack(int attack)
	{
		this.attack = attack;
	}
	
}
