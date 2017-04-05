package game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Entity
{
	
	protected float x, y;
	
	public Entity(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void render(GameContainer gc, Graphics g);
	public abstract void update(GameContainer gc, int delta);

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
}
