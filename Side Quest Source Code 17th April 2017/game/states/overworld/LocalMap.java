package game.states.overworld;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import game.entities.overworld.Player;
import game.states.State;
import game.tiles.CSVReader;
import game.tiles.TileLayer;
import game.tiles.TileMap;

public class LocalMap extends State
{

	public static final String TILE_LAYER = "tiles", COLLISION_LAYER = "collision", UPPER_LAYER = "upper";
	
	private Player player;
	private TileMap map;
	
	public LocalMap(int width, int height, String tileLayer, String collisionLayer, String upperLayer, List<Image> sprites)
	{
		map = new TileMap(0, 0);
		
		map.addLayer(TILE_LAYER, new TileLayer(map.getX(), map.getY(), width, height, CSVReader.readCSV(tileLayer), sprites));
		map.addLayer(COLLISION_LAYER, new TileLayer(map.getX(), map.getY(), width, height, CSVReader.readCSV(collisionLayer), sprites));
		map.addLayer(UPPER_LAYER, new TileLayer(map.getX(), map.getY(), width, height, CSVReader.readCSV(upperLayer), sprites));
		
		player = new Player(9 * 32, 7 * 32, this, map);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		map.renderLayer(TILE_LAYER, gc, g);
		map.renderLayer(COLLISION_LAYER, gc, g);
		player.render(gc, g);
		map.renderLayer(UPPER_LAYER, gc, g);
	}
	
	@Override
	public void update(GameContainer gc, int delta)
	{
		player.update(gc, delta);
		map.update(gc, delta);
		
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			gc.exit();
		}
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
