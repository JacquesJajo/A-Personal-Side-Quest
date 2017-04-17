package game.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import game.entities.Entity;

public class TileMap extends Entity
{
	
	private Map<String, TileLayer> layers;
	private List<String> names;
	
	public TileMap(float x, float y)
	{
		super(x, y);
		layers = new HashMap<String, TileLayer>();
		names = new ArrayList<String>();
	}
	
	public void renderLayer(String layerName, GameContainer gc, Graphics g)
	{
		if (layers.get(layerName).shouldRender)
			layers.get(layerName).render(gc, g);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		for (String name : names)
		{
			if (layers.get(name).shouldRender)
				layers.get(name).render(gc, g);
		}
	}
	
	public void update(GameContainer gc, int delta)
	{
		for (String name : names)
		{
			layers.get(name).setX(x);
			layers.get(name).setY(y);
		}
	}

	public void addLayer(String layerName, TileLayer layer)
	{
		layers.put(layerName, layer);
		names.add(layerName);
	}
	
	public void move(float dx, float dy)
	{
		x += dx;
		y += dy;
	}
	
	public TileLayer getLayer(String name)
	{
		return layers.get(name);
	}

}
