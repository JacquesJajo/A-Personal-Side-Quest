package game.entities.overworld;

import game.entities.Entity;
import game.states.State;

public abstract class MapEntity extends Entity
{

	protected State over;
	
	public MapEntity(float x, float y, State over)
	{
		super(x, y);
		this.over = over;
	}

}
