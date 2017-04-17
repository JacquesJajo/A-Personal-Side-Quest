package game.states.overworld;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.Main;
import game.entities.overworld.Player;
import game.gfx.Assets;
import game.states.State;
import game.states.battle.BattleState;
import game.tiles.CSVReader;
import game.tiles.TileLayer;
import game.tiles.TileMap;

public class BasicOverworld extends State
{

	public static final String TEST = "test", ROCKS = "rocks";
	
	private float x, y;
	public int health = 10;
	private Random random;
	private TileLayer test, rocks;
	private TileMap map;
	private Player player;
	
	public BasicOverworld()
	{
		x = 9 * 32;
		y = 10 * 32;
		random = new Random();
		test = new TileLayer(0, -3 * 32, 40, 30, CSVReader.readCSV("res/basicWorld_Tile Layer 1.csv"), Assets.forestSprites);
		rocks = new TileLayer(0, -3 * 32, 40, 30, CSVReader.readCSV("res/basicWorld_Tile Layer 2.csv"), Assets.forestSprites);
		map = new TileMap(0, -3 * 32);
		map.addLayer(TEST, test);
		map.addLayer(ROCKS, rocks);
		player = new Player(x, y, this, map);
	}
	
	public void init(GameContainer gc)
	{
		health = 10;
		x = 9 * 32;
		y = 7 * 32;
		random = new Random();
	}

	public void render(GameContainer gc, Graphics g)
	{
		map.render(gc, g);
		//g.drawImage(Assets.playerOver, 9 * 32, 7 * 32);
		player.render(gc, g);
	}

	public void update(GameContainer gc, int delta)
	{
		
		player.update(gc, delta);
		map.update(gc, delta);
		
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			gc.exit();
		}
	}
	
	public void checkCombat()
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
