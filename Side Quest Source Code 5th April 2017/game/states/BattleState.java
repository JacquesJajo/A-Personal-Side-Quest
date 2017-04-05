package game.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.MainGame;
import game.entities.battle.BattleEnemy;
import game.entities.battle.BattleEntity;
import game.entities.battle.BattlePlayer;
import game.entities.battle.actions.Action;
import game.entities.battle.actions.Attack;
import game.entities.battle.actions.Fire;

public class BattleState extends State
{

	private float arrowX, arrowY;
	private int enemyIndex, optionIndex, playerID = 0;
	private int[] options;
	private boolean attack, choice;
	
	private List<BattleEntity> entities;
	private List<Action> actions;
	private BattleEntity[] players;
	public BasicOverworld over;
	
	public BattleState(BasicOverworld over)
	{
		this.over = over;
		attack = false;
		choice = true;
		options = new int[] {0, 1, 2};
	}
	
	public void addEntities(List<BattleEntity> entities)
	{
		this.entities = entities;
		players = generatePlayers();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		g.setColor(new Color(0, 128, 192));
		g.fillRect(0, 0, MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);
		
		for (BattleEntity e : entities)
			e.render(gc, g);
		
		g.setColor(new Color(3, 6, 148));
		g.fillRect(0, 480 - 128, 480, 128);
		
		g.setColor(Color.white);
		g.drawString("->", arrowX, arrowY);
		g.drawString("Fight", 480 - 96, 480 - 128 + 16);
		g.drawString("Magic", 480 - 96, 480 - 128 + 32);
		g.drawString("Run", 480 - 96, 480 - 128 + 48);
		
		for (int y = 0; y < players.length; y++)
		{
			g.drawString(players[y].getName() + ": " + players[y].getHealth(), 480 - 256, 480 - 128 + 16 + 16 * y);
		}
		
		g.drawString("Skeleton x" + getNumEnemies(), 32, 480 - 128 + 16);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		List<BattleEntity> enemyList = getEnemies();
		BattleEntity[] enemies = toArray(enemyList);
		
		players = generatePlayers();
		
		if (playerID >= players.length)
			playerID = 0;
		
		if (choice)
			this.selectAction(gc, players, enemies);
		
		if (attack)
			this.selectTarget(gc, delta, enemies, enemyList, players, actions, players[playerID].getAction());
		
		if (getNumEnemies() <= 0 || numPlayers() <= 0)  
			State.enterState(over);
		
	}
	
	// WARNING: BORING STUFF BELOW
	
	public int getNumEnemies()
	{
		int enemies = 0;
		for (BattleEntity e : entities)
			if (!e.playerControlled() && !e.isDead())
				enemies++;
		
		return enemies;
	}
	
	private List<BattleEntity> getEnemies()
	{
		List<BattleEntity> enemies = new ArrayList<BattleEntity>();
		
		for (BattleEntity e : entities)
		{
			if (!e.playerControlled() && !e.isDead())
				enemies.add(e);
		}
		
		return enemies;
	}
	
	private BattleEntity[] toArray(List<BattleEntity> entities)
	{
		int n = entities.size();
		
		BattleEntity[] arr = new BattleEntity[n];
		
		for (int i = 0; i < n; i++)
			arr[i] = entities.get(i);
		
		return arr;
	}
	
	private void selectAction(GameContainer gc, BattleEntity[] players, BattleEntity[] enemies)
	{
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN) && optionIndex < options.length - 1) optionIndex++;
		else if (gc.getInput().isKeyPressed(Input.KEY_UP) && optionIndex > 0) optionIndex--;
		
		if (gc.getInput().isKeyPressed(Input.KEY_K))
		{
			if (options[optionIndex] == 0)
			{
				choice = false;
				attack = true;
				
				players[playerID].setAction(new Attack(players[playerID], enemies[enemyIndex]));
				
				setArrowSelectPos();
			}
			
			else if (options[optionIndex] == 1)
			{
				choice = false;
				attack = true;
				
				//State.enterState(new BattleChooseSpell(this));
				
				players[playerID].setAction(new Fire(players[playerID], enemies[enemyIndex]));
				
				setArrowSelectPos();
			}
			
			else if (options[optionIndex] == 2)
			{
				State.enterState(over);
			}
			
		}
		
		arrowY = 480 - 128 + 16 + 16 * optionIndex;
		arrowX = 480 - 128;
	}
	
	private void selectTarget(GameContainer gc, int delta, BattleEntity[] enemies, List<BattleEntity> enemyList, BattleEntity[] players, List<Action> actions, Action actionChosen)
	{
		players[playerID].choosing = true;
		if (gc.getInput().isKeyPressed(Input.KEY_UP) && enemyIndex > 0) enemyIndex--;
		else if (gc.getInput().isKeyPressed(Input.KEY_DOWN) && enemyIndex < getNumEnemies() - 1) enemyIndex++;
		
		if (enemyIndex > enemies.length - 1) enemyIndex = enemies.length - 1;
		
		arrowX = enemies[enemyIndex].getX() - 16;
		arrowY = enemies[enemyIndex].getY();
		
		if (gc.getInput().isKeyPressed(Input.KEY_K))
		{
			//Action playerAction = actionChosen; //new Attack(players[playerID], enemies[enemyIndex]);
			//players[playerID].setAction(playerAction);
			players[playerID].setReady(true);
			actions.add(players[playerID].getAction());
			
			players[playerID].choosing = false;
			playerID++;
			choice = true;
			attack = false;
		}
		
		if (allPlayersReady(players))
		{
			for (int i = 0; i < players.length; i++)
				players[i].setReady(false);
			
			for (BattleEntity e : enemyList)
			{
				Action action = new Attack(e, players[random.nextInt(players.length)]);
				e.setAction(action);
				actions.add(action);
			}
			
			State.enterState(new BattleExecute(this, actions));
			
		}
		
	}
	
	private void setArrowSelectPos()
	{
		for (BattleEntity e : entities)
		{
			if (!e.playerControlled())
			{
				arrowX = e.getX() - 32;
				arrowY = e.getY();
				break;
			}
		}
	}
	
	private boolean allPlayersReady(BattleEntity[] players)
	{
		for (int i = 0; i < players.length; i++)
			if (!players[i].isReady() && !players[i].isDead()) return false;
		return true;
	}
	
	private int numPlayers()
	{
		int num = 0;
		for (BattleEntity e : entities)
		{
			if (e.playerControlled() && !e.isDead()) num++;
		}
		
		return num;
	}
	
	private BattleEntity[] generatePlayers()
	{
		List<BattleEntity> playerList = new ArrayList<BattleEntity>();
		
		for (BattleEntity e : entities)
		{
			if (e.playerControlled() && !e.isDead())
				playerList.add(e);
		}
		
		return toArray(playerList);
	}
	
	public List<BattleEntity> generateEntities()
	{
		List<BattleEntity> entities = new ArrayList<BattleEntity>();
		
		entities.add(new BattlePlayer(this, 480 - 128, 128, 50, 5, 5, "A"));
		entities.add(new BattlePlayer(this, 480 - 128, 128 + 64, 50, 5, 5, "B"));
		entities.add(new BattlePlayer(this, 480 - 128, 256, 50, 5, 5, "C"));
		
		for (int y = 0; y < 4; y++)
		{
			entities.add(new BattleEnemy(this, 64, 128 + y * 32, 10, 1, 6));
		}
		
		for (int y = 0; y < 4; y++)
		{
			entities.add(new BattleEnemy(this, 96, 128 + y * 32, 10, 1, 1));
		}
		
		return entities;
	}

	@Override
	public void onEnter()
	{
		if (getNumEnemies() <= 0)
			State.enterState(over);
		
		choice = true;
		attack = false;
		actions = new ArrayList<Action>();
	}

	@Override
	public void onExit()
	{
		
	}
	
}
