package game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import game.Main;
import game.entities.battle.BattleEnemy;
import game.entities.battle.BattlePlayer;

public class Battle extends BasicGameState
{

	private int enemyIndex;
	private float arrowY;
	
	private BattlePlayer player;
	private BattleEnemy[] enemies;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException
	{
		player = new BattlePlayer(this, 480 - 128, 160, 100, 1);
		
		BattleEnemy[] enemies = new BattleEnemy[3];
		enemies[0] = new BattleEnemy(this, 64, 64, 3, 1);
		enemies[1] = new BattleEnemy(this, 64, 160, 3, 1);
		enemies[2] = new BattleEnemy(this, 64, 256, 3, 1);
		
		addEnemies(enemies);
		
		arrowY = 64;
		enemyIndex = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
	{
		g.setColor(new Color(0, 128, 192));
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		for (int i = 0; i < enemies.length; i++)
		{
			if (!enemies[i].isDead())
				enemies[i].render(gc, game, g);
		}
		
		player.render(gc, game, g);
		
		if (enemyIndex == 0) arrowY = 64;
		else if (enemyIndex == 1) arrowY = 160;
		else if (enemyIndex == 2) arrowY = 256;
		
		g.fillRect(32, arrowY, 32, 32);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
	{
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN) && enemyIndex < enemies.length - 1)
		{
			enemyIndex++;
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_UP) && enemyIndex > 0)
		{
			enemyIndex--;
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER))
		{
			this.executeActions();
		}
		
		if (player.isDead() || noEnemies())
		{
			this.init(gc, game);
		}
		
	}
	
	public void addEnemies(BattleEnemy[] enemies)
	{
		this.enemies = enemies;
	}
	
	private void executeActions()
	{
		player.battleAction();
		
		for (int i = 0; i < enemies.length; i++)
		{
			if (!enemies[i].isDead())
				enemies[i].battleAction();
		}
	}
	
	@Override
	public int getID()
	{
		return 0;
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

}
