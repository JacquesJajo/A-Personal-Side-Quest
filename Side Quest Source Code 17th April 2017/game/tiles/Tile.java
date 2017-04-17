package game.tiles;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile
{
	private int type;
	
	public Tile(int type)
	{
		this.type = type;
	}

	public void render(float x, float y, GameContainer gc, Graphics g, Image sprite)
	{
		g.drawImage(sprite, x, y);
	}
	
	public void render(float x, float y, GameContainer gc, Animation animation)
	{
		animation.draw(x, y);
	}
	
	public void testRender(float x, float y, GameContainer gc, Graphics g, Color colour)
	{
		g.setColor(colour);
		g.fillRect(x, y, 32, 32);
	}
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
