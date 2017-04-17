package game.entities.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;

import game.entities.Entity;
import game.entities.battle.actions.Action;
import game.states.battle.BattleState;

public abstract class BattleEntity extends Entity
{
	// Stats
	protected int health, maxHealth,
	mp, maxMp,
	attack,
	speed,
	intelligence;
	
	
	protected float initX;
	protected boolean ready, animate;
	protected Random random;
	protected BattleState battle;
	protected Action action;
	protected List<Action> techList;
	protected String name;
	
	public boolean choosing = false, mustChoose = true;
	
	public BattleEntity(BattleState battle, float x, float y, String name, int health, int maxHealth, int attack, int speed, int mp, int maxMp, int intelligence)
	{
		super(x, y);
		
		this.battle = battle;
		this.health = health;
		this.maxHealth = maxHealth;
		this.maxMp = maxMp;
		this.attack = attack;
		this.speed = speed;
		this.name = name;
		this.mp = mp;
		this.intelligence = intelligence;
		initX = x;
		ready = false;
		random = new Random();
		techList = new ArrayList<Action>();
	}
	
	public BattleEntity(BattleState battle, float x, float y, String name, int health, int attack, int speed, int mp, int intelligence)
	{
		super(x, y);
		this.battle = battle;
		this.health = health;
		this.maxHealth = health;
		this.attack = attack;
		this.speed = speed;
		this.name = name;
		this.mp = mp;
		this.maxMp = mp;
		this.intelligence = intelligence;
		initX = x;
		ready = false;
		random = new Random();
		techList = new ArrayList<Action>();
	}
	
	public abstract void battleAction(GameContainer gc, int delta);
	public abstract boolean playerControlled();
	
	public void move()
	{
		x--;
	}
	
	public void setAction(Action action)
	{
		this.action = action;
	}
	
	public boolean isDead()
	{
		if (health <= 0) return true;
		
		return false;
	}
	
	public void attack(BattleEntity target)
	{
		int hit = random.nextInt(4);
		
		if (hit != 0)
			target.damage(this);
	}
	
	public void decreaseMP(int amnt)
	{
		mp -= amnt;
	}
	
	public void damage(BattleEntity enemy)
	{
		this.health -= enemy.getAttack();
	}
	
	public void damage(float dmg)
	{
		this.health -= dmg;
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

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public Action getAction()
	{
		return action;
	}

	public boolean isReady()
	{
		return ready;
	}

	public void setReady(boolean ready)
	{
		this.ready = ready;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Action> getTechList()
	{
		return techList;
	}

	public int getMp()
	{
		return mp;
	}

	public boolean isAnimate()
	{
		return animate;
	}

	public void setAnimate(boolean animate)
	{
		this.animate = animate;
	}

	public int getIntelligence()
	{
		return intelligence;
	}

	public void setMp(int mp)
	{
		this.mp = mp;
	}

	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}
	
	public float getWeaponStrength()
	{
		return 5;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}

	public int getMaxMp()
	{
		return maxMp;
	}

	public void setMaxMp(int maxMp)
	{
		this.maxMp = maxMp;
	}
}
