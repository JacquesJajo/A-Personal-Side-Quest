package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import game.states.Battle;

public class Main extends StateBasedGame
{

	public static final int SCREEN_WIDTH = 480, SCREEN_HEIGHT = 480;
	
	public Main(String name)
	{
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException
	{
		this.addState(new Battle());
	}
	
	public static void main(String[] args)
	{
		AppGameContainer appgc;
		try
		{
			appgc = new AppGameContainer(new Main("Game"));
			appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			appgc.start();
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
