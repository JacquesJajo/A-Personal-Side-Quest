package game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class BasicMenu extends State
{

	private StateStack states;
	
	public BasicMenu(StateStack states)
	{
		this.states = states;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		g.drawString("Press Space", 15 * 32, 64);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		if (gc.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			states.setStateToTop();
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
