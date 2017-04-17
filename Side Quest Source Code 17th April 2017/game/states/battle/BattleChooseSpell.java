package game.states.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.MainGame;
import game.entities.battle.BattleEntity;
import game.states.State;

public class BattleChooseSpell extends State
{

	private float arrowX, arrowY;
	private int spellIndex;
	
	private BattleState parent;
	private BattleEntity player;
	
	public BattleChooseSpell(BattleState parent, BattleEntity player)
	{
		this.parent = parent;
		this.player = player;
		spellIndex = 0;
		arrowX = 64 - 32;
		arrowY = MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * spellIndex;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		parent.render(gc, g);
		g.setColor(new Color(3, 6, 148));
		g.fillRect(0, MainGame.SCREEN_HEIGHT - 128, MainGame.SCREEN_WIDTH - 256 - 64, 128);
		
		g.setColor(Color.white);
		for (int i = 0; i < player.getTechList().size(); i++)
		{
			g.drawString(player.getTechList().get(i).name(), 64, MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * i);
		}
		
		g.drawString("->", arrowX, arrowY);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		arrowY = MainGame.SCREEN_HEIGHT - 128 + 16 + 16 * spellIndex;
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
		{
			gc.exit();
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_DOWN) && spellIndex < player.getTechList().size() - 1) spellIndex++;
		else if (gc.getInput().isKeyPressed(Input.KEY_UP) && spellIndex > 0) spellIndex--;
		
		if (gc.getInput().isKeyPressed(Input.KEY_K))
		{
			player.getTechList().get(spellIndex).onChoose();
			player.setAction(player.getTechList().get(spellIndex));
			State.enterState(parent);
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
