package game.entities.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import game.entities.Entity;
import game.entities.battle.actions.Action;
import game.states.BattleState;

public abstract class BattleEntity extends Entity
{
	
	protected int health, mp, attack, speed;
	protected float initX;
	protected boolean ready;
	protected Random random;
	protected BattleState battle;
	protected Action action;
	protected List<Action> techList;
	protected String name;
	
	public boolean choosing = false;
	
	public BattleEntity(BattleState battle, float x, float y, int health, int attack, int speed, String name, int mp)
	{
		super(x, y);
		this.battle = battle;
		this.health = health;
		this.attack = attack;
		this.speed = speed;
		this.name = name;
		this.mp = mp;
		initX = x;
		ready = false;
		random = new Random();
		techList = new ArrayList<Action>();
	}
	
	public abstract void battleAction(GameContainer gc, int delta);
	public abstract boolean playerControlled();
	
	public void setAction(Action action)
	{
		this.action = action;
	}
	
	public boolean isClicked(GameContainer gc, float width, float height)
	{
		
		Rectangle bounds = new Rectangle(x, y, width, height);
		Vector2f mousePos = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		
		if (gc.getInput().isMousePressed(1) && bounds.contains(mousePos.x, mousePos.y))
			return true;
		return false;
		
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
	
	public void damage(int dmg)
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
}
