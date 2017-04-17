package game.tiles;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TileLayer
{
	private float x, y;
	private int width, height;
	private Tile[] tiles;
	private List<Image> sprites;
	
	public boolean shouldRender = true;
	
	public TileLayer(float x, float y, int width, int height, int[] map, List<Image> sprites)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprites = sprites;
		
		tiles = new Tile[width * height];
		generateMap(map);
	}
	
	private void generateMap(int[] map)
	{
		for (int i = 0; i < map.length; i++)
		{
			tiles[i] = new Tile(map[i]);
		}
	}
	
	public void render(GameContainer gc, Graphics g)
	{
		
		int tileColumn = 1, tileRow = 1;
		
		for (int i = 0; i < tiles.length; i++)
		{
			if (tiles[i].getType() != -1)
					tiles[i].render(x + (tileColumn - 1) * 32,  y + (tileRow - 1) * 32, gc, g, sprites.get(tiles[i].getType()));
			
			tileColumn += 1;
			
			if (tileColumn > width)
			{
				tileColumn = 1;
				tileRow++;
			}
		}
	}
	
	public void testRender(int[] map, Graphics g)
	{
		int tileColumn = 1, tileRow = 1;
		
		for (int i = 0; i < map.length; i++)
		{
			if (map[i] == 0)
			{
				g.setColor(Color.green);
				g.fillRect(x + (tileColumn - 1) * 32,  y + (tileRow - 1) * 32, 32, 32);
			}
			
			else if (map[i] == 1)
			{
				g.setColor(Color.gray);
				g.fillRect(x + (tileColumn - 1) * 32,  y + (tileRow - 1) * 32, 32, 32);
			}
			
			tileColumn += 1;
			
			if (tileColumn > width)
			{
				tileColumn = 1;
				tileRow++;
			}
		}
	}
	
	public void move (float dx, float dy)
	{
		x += dx;
		y += dy;
	}
	
	public int getTileId(int x, int y)
	{
		return tiles[y * width + x].getType();
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public Tile[] getTiles()
	{
		return tiles;
	}

	public void setTiles(Tile[] tiles)
	{
		this.tiles = tiles;
	}
	
}
