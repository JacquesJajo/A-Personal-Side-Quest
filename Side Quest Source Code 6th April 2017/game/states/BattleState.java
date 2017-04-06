package game.states;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import game.gfx.Assets;

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
		actions = new ArrayList<Action>();
	}
	
	public void addEntities(List<BattleEntity> entities)
	{
		this.entities = entities;
		players = generatePlayers();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			gc.exit();
		}
		
		g.setColor(new Color(0, 128, 192));
		g.fillRect(0, 0, MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);
		
		g.drawImage(Assets.background, 0, 0);
		
		for (BattleEntity e : entities)
			e.render(gc, g);
		
		g.setColor(new Color(3, 6, 148));
		g.fillRect(0, MainGame.SCREEN_HEIGHT - 128, MainGame.SCREEN_WIDTH, 128);
		
		g.setColor(Color.white);
		if (State.getCurrentState() == this)
			g.drawString("->", arrowX, arrowY);
		g.drawString("Attack", MainGame.SCREEN_WIDTH - 96, MainGame.SCREEN_HEIGHT - 128 + 16);
		g.drawString("Tech", MainGame.SCREEN_WIDTH - 96, MainGame.SCREEN_HEIGHT - 128 + 32);
		g.drawString("Run", MainGame.SCREEN_WIDTH - 96, MainGame.SCREEN_HEIGHT - 128 + 48);
		
		for (int y = 0; y < players.length; y++)
		{
			g.drawString(players[y].getName() + " HP: " + players[y].getHealth() + " MP:" + players[y].getMp(), MainGame.SCREEN_WIDTH - 256 - 64,  MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * y);
		}
		
		List<String> enemyNames = new ArrayList<String>();
		Map<String, Integer> enemyTotals = new HashMap<String, Integer>();
		
		for (BattleEntity e : entities)
		{
			if (!e.playerControlled() && !enemyNames.contains(e.getName()) && !e.isDead())
			{
				enemyNames.add(e.getName());
				enemyTotals.put(e.getName(), 0);
			}
		}
		
		for (BattleEntity e : entities)
		{
			if (!e.playerControlled() && !e.isDead())
				enemyTotals.put(e.getName(), enemyTotals.get(e.getName()) + 1);
		}
		
		for (int i = 0; i < enemyNames.size(); i++)
		{
			g.drawString(enemyNames.get(i) + " x" + enemyTotals.get(enemyNames.get(i)), 32, MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * i);
		}
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		List<BattleEntity> enemyList = getEnemies();
		BattleEntity[] enemies = toArray(enemyList);
		
		players = generatePlayers();
		
		if (playerID >= players.length)
			playerID = 0;
		
		if (enemyIndex >= enemies.length)
			enemyIndex = 0;
		
		if (choice)
			this.selectAction(gc, players);
		
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
	
	private void selectAction(GameContainer gc, BattleEntity[] players)
	{
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN) && optionIndex < options.length - 1) optionIndex++;
		else if (gc.getInput().isKeyPressed(Input.KEY_UP) && optionIndex > 0) optionIndex--;
		
		if (gc.getInput().isKeyPressed(Input.KEY_K))
		{
			if (options[optionIndex] == 0)
			{
				choice = false;
				attack = true;
				
				players[playerID].setAction(new Attack(players[playerID]));
				
			}
			
			else if (options[optionIndex] == 1)
			{

				choice = false;
				attack = true;

				State.enterState(new BattleChooseSpell(this, players[playerID]));
			}
			
			else if (options[optionIndex] == 2)
			{
				State.enterState(over);
			}
			
		}
		
		arrowY = MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * optionIndex;
		arrowX = MainGame.SCREEN_WIDTH - 128;
	}
	
	private void selectTarget(GameContainer gc, int delta, BattleEntity[] enemies, List<BattleEntity> enemyList, BattleEntity[] players, List<Action> actions, Action actionChosen)
	{
		players[playerID].choosing = true;
		if (gc.getInput().isKeyPressed(Input.KEY_UP) && enemyIndex > 0) enemyIndex--;
		else if (gc.getInput().isKeyPressed(Input.KEY_DOWN) && enemyIndex < getNumEnemies() - 1) enemyIndex++;
		
		arrowX = enemies[enemyIndex].getX() - 16;
		arrowY = enemies[enemyIndex].getY();
		
		if (gc.getInput().isKeyPressed(Input.KEY_K))
		{
			players[playerID].setReady(true);
			
			players[playerID].getAction().setTarget(enemies[enemyIndex]);
			
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
				int k = random.nextInt(2);
				Action action;
				if (k == 0)
					action = new Attack(e, players[random.nextInt(players.length)]);
				else
					action = new Fire(e, players[random.nextInt(players.length)]);
				System.out.println(action.name());
				e.setAction(action);
				actions.add(action);
			}
			
			State.enterState(new BattleExecute(this, actions));
			
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
	
	public BattleEntity getCurrentPlayer()
	{ 
		return players[playerID]; 
	}
	
	public List<BattleEntity> generateEntities()
	{
		List<BattleEntity> entities = new ArrayList<BattleEntity>();
		
		BattlePlayer A = new BattlePlayer(this,  MainGame.SCREEN_WIDTH - 128, 128, 50, 5, 5, "Jacques");
		A.getTechList().add(new Fire(A));
		A.getTechList().add(new Fire(A));
		A.getTechList().add(new Fire(A));
		entities.add(A);
		BattlePlayer B = new BattlePlayer(this, MainGame.SCREEN_WIDTH - 128, 128 + 64, 50, 5, 5, "Pierre");
		B.getTechList().add(new Fire(B));
		entities.add(B);
		BattlePlayer C = new BattlePlayer(this, MainGame.SCREEN_WIDTH - 128, 256, 50, 5, 5, "Clarence");
		C.getTechList().add(new Fire(C));
		entities.add(C);
		
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

	public List<Action> getActions()
	{
		return actions;
	}

	public void setActions(List<Action> actions)
	{
		this.actions = actions;
	}

	@Override
	public void onEnter()
	{
		if (getNumEnemies() <= 0)
			State.enterState(over);
	}

	@Override
	public void onExit()
	{
		
	}
	
}
