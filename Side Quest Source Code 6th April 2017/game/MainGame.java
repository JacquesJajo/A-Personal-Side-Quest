package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import game.gfx.Assets;
import game.states.BasicOverworld;
import game.states.State;

public class MainGame extends BasicGame
{

	public static final int SCREEN_WIDTH = 640, SCREEN_HEIGHT = 480;
	public static final int BATTLE = 0, OVER = 1;
	
	public BasicOverworld over;
	
	public MainGame(String title)
	{
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		State.getCurrentState().render(gc, g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		Assets.init();
		over = new BasicOverworld();
		State.enterState(over);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		State.getCurrentState().update(gc, delta);
	}
	
	public static void main(String[] args)
	{
		AppGameContainer appgc;
		try
		{
			appgc = new AppGameContainer(new MainGame("Game"));
			appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			appgc.setMouseGrabbed(true);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
