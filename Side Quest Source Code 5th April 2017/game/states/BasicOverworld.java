package game.states;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.Main;
import game.gfx.Assets;

public class BasicOverworld extends State
{

	private float x, y;
	public int health = 10;
	private Random random;

	public BasicOverworld()
	{
		x = 64;
		y = 64;
		random = new Random();
	}
	
	public void init(GameContainer gc)
	{
		health = 10;
		x = 64;
		y = 64;
		random = new Random();
	}

	public void render(GameContainer gc, Graphics g)
	{
		g.drawImage(Assets.playerOver, x, y);
	}

	public void update(GameContainer gc, int delta)
	{
		
		if (health <= 0) this.init(gc);
		
		if (gc.getInput().isKeyPressed(Input.KEY_UP))
		{
			y -= 32;
			checkCombat();

		} else if (gc.getInput().isKeyPressed(Input.KEY_DOWN))
		{
			y += 32;
			checkCombat();

		} else if (gc.getInput().isKeyPressed(Input.KEY_LEFT))
		{
			x -= 32;
			checkCombat();

		} else if (gc.getInput().isKeyPressed(Input.KEY_RIGHT))
		{
			x += 32;
			checkCombat();
		}
	}
	
	private void checkCombat()
	{
		int combat = random.nextInt(10);
		
		if (combat == 0)
		{
			BattleState battle = new BattleState(this);
			battle.addEntities(battle.generateEntities());
			State.enterState(battle);
		}
	}

	public int getID()
	{
		return Main.OVER;
	}

	@Override
	public void onEnter()
	{
		
	}

	@Override
	public void onExit()
	{
		
	}

}
