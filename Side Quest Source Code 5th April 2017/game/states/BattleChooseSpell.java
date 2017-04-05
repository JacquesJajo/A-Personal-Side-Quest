package game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class BattleChooseSpell extends State
{

	private BattleState parent;
	
	public BattleChooseSpell(BattleState parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		parent.render(gc, g);
		g.setColor(new Color(3, 6, 148));
		g.fillRect(0, 480 - 128, 480, 128);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		
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
