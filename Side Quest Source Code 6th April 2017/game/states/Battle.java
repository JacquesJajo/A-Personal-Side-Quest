/*package game.states;

import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.Main;
import game.entities.battle.BattleEnemy;
import game.entities.battle.BattleEntity;
import game.entities.battle.BattlePlayer;

public class Battle extends State
{

	private int enemyIndex;
	private float arrowY, arrowX;
	
	private BattlePlayer player;
	private BattleEnemy[] enemies;
	private BattleEntity[] entities;
	private BasicOverworld over;
	private BattleExecute execute;
	private Map<Integer, BattleEnemy> enemyMap;
	
	public boolean doActions;
	
	public Battle(BasicOverworld over)
	{
		this.over = over;
		execute = new BattleExecute(this);
		/*player = new BattlePlayer(this, 480 - 128, 160, 100, 1, 5);
		
		BattleEnemy[] enemies = new BattleEnemy[4];
		enemies[0] = new BattleEnemy(this, 64, 64, 3, 1, 4);
		enemies[1] = new BattleEnemy(this, 64, 160, 3, 1, 3);
		enemies[2] = new BattleEnemy(this, 64, 256, 3, 1, 2);
		enemies[3] = new BattleEnemy(this, 128 + 32, 64, 3, 1, 1);
	
		//addEnemies(enemies);
		
		entities = new BattleEntity[] {player, enemies[0], enemies[1], enemies[2], enemies[3]};
		bubbleSort(entities);
		
		arrowY = 64;
		arrowX = 32;
		enemyIndex = 0;
		doActions = false;
	}

	public void render(GameContainer gc, Graphics g)
	{
		g.setColor(new Color(0, 128, 192));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		for (int i = 0; i < entities.length; i++)
		{
			entities[i].render(gc, g);
		}
		
		g.setColor(Color.white);
		g.drawString("->", arrowX, arrowY);
	}

	public void update(GameContainer gc, int delta)
	{
		
		for (int i = 0; i < entities.length; i++)
		{
			entities[i].update(gc, delta);
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER))
		{
			doActions = true;
		}
		
		if (player.isDead())
		{
			State.enterState(over);
		}
		
		execute.update(gc, delta);
		
	}
	
	public void addEnemies(BattleEnemy[] enemies)
	{
		this.enemies = enemies;
	}
	
	public void bubbleSort(BattleEntity[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr.length - i - 1; j++)
			{
				if (arr[j].getSpeed() < arr[j + 1].getSpeed())
				{
					int temp = arr[j].getSpeed();
					arr[j].setSpeed(arr[j+1].getSpeed());
					arr[j + 1].setSpeed(temp);
				}
			}
		}
		
	}
	
	public int getID()
	{
		return Main.BATTLE;
	}

	public int getEnemyIndex()
	{
		return enemyIndex;
	}

	public BattlePlayer getPlayer()
	{
		return player;
	}

	public BattleEnemy[] getEnemies()
	{
		return enemies;
	}
	
	private boolean noEnemies()
	{
		for (int i = 0; i < enemies.length; i++)
		{
			if (!enemies[i].isDead()) return false;
		}
		
		return true;
	}

	public BattleEntity[] getEntities()
	{
		return entities;
	}
	
}*/
