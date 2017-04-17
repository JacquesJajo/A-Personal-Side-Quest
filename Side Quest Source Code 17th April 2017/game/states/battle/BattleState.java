package game.states.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.MainGame;
import game.entities.battle.BattleEntity;
import game.entities.battle.Placeholder;
import game.entities.battle.actions.Action;
import game.entities.battle.actions.Attack;
import game.entities.battle.characters.Grey;
import game.entities.battle.characters.Pink;
import game.entities.battle.characters.Yellow;
import game.entities.battle.enemies.Ogre;
import game.entities.battle.enemies.Skeleton;
import game.gfx.Assets;
import game.states.State;

public class BattleState extends State
{

	private float arrowX, arrowY;
	private int enemyIndex, optionIndex, playerID = 0;
	private int[] options;
	private boolean attack, choice;
	
	private List<BattleEntity> entities;
	private List<Action> actions;
	private BattleEntity[] players;
	public State over;
	
	public BattleState(State over)
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
			if (players[y].getHealth() < 0) players[y].setHealth(0);
			
			if (players[y].choosing)
				g.setColor(Color.green);
			else
				g.setColor(Color.white);
			g.drawString(players[y].getName() + " HP: " + players[y].getHealth() + " MP:" + players[y].getMp(), MainGame.SCREEN_WIDTH - 256 - 64,  MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * y);
			g.setColor(Color.white);
		}
		
		List<String> enemyNames = new ArrayList<String>();
		Map<String, Integer> enemyTotals = new HashMap<String, Integer>();
		
		for (BattleEntity e : entities)
		{
			if (!e.playerControlled() && !enemyNames.contains(e.getName()) && !e.isDead() && e.getName() != Placeholder.name)
			{
				enemyNames.add(e.getName());
				enemyTotals.put(e.getName(), 0);
			}
		}
		
		for (BattleEntity e : entities)
		{
			if (!e.playerControlled() && !e.isDead() && e.getName() != Placeholder.name)
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
			if (!e.playerControlled() && !e.isDead() && e.getName() != Placeholder.name)
				enemies++;
		
		return enemies;
	}
	
	public List<BattleEntity> getEnemies()
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
				
				Attack attack = new Attack(players[playerID]);
				attack.onChoose();
				players[playerID].setAction(attack);
				
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
		
		if (gc.getInput().isKeyPressed(Input.KEY_K) || !players[playerID].mustChoose)
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
				if (k == 0 || e.getName() == Placeholder.name || e.getTechList().size() == 0)
					action = new Attack(e, players[random.nextInt(players.length)]);
				else
				{	
					int index = e.getTechList().size();
					
					if (index <= 0) index = 1;
					
					action = e.getTechList().get(random.nextInt(index));
					action.setTarget(players[random.nextInt(players.length)]);
				}
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
	
	public int numPlayers()
	{
		int num = 0;
		for (BattleEntity e : entities)
		{
			if (e.playerControlled() && !e.isDead()) num++;
		}
		
		return num;
	}
	
	public BattleEntity[] generatePlayers()
	{
		List<BattleEntity> playerList = new ArrayList<BattleEntity>();
		
		for (BattleEntity e : entities)
		{
			if (e.playerControlled() && !e.isDead())
			{
				playerList.add(e);
			}
		}
		
		return toArray(playerList);
	}
	
	public List<BattleEntity> getPlayers()
	{
		List<BattleEntity> playerList = new ArrayList<BattleEntity>();
		
		for (BattleEntity e : entities)
		{
			if (e.playerControlled() && !e.isDead())
				playerList.add(e);
		}
		
		return playerList;
	}
	
	public BattleEntity getCurrentPlayer()
	{ 
		return players[playerID]; 
	}
	
	public List<BattleEntity> generateEntities()
	{
		List<BattleEntity> entities = new ArrayList<BattleEntity>();
		
		//BattleState battle, float x, float y, String name, int health, int attack, int speed, int mp, int intelligence
		
		entities.add(new Pink(this, MainGame.SCREEN_WIDTH - 128, 128, "Pink", 150, 20));
		entities.add(new Grey(this, MainGame.SCREEN_WIDTH - 128, 128 + 64, "Grey", 120, 60));
		entities.add(new Yellow(this, MainGame.SCREEN_WIDTH - 128, 128 + 128, "Yellow", 125, 30));
		
		for (int y = 0; y < 4; y++)
		{
			entities.add(new Skeleton(this, 64, 128 + y * 32));
		}
		
		for (int y = 0; y < 4; y++)
		{
			entities.add(new Ogre(this, 64 + 32, 128 + y * 32));
		}
		
		entities.add(new Placeholder(this));
		
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

	public List<BattleEntity> getEntities()
	{
		return entities;
	}

	public void setEntities(List<BattleEntity> entities)
	{
		this.entities = entities;
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
