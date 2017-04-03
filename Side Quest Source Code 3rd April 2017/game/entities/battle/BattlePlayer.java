package game.entities.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import game.states.Battle;

public class BattlePlayer extends BattleEntity
{

	public BattlePlayer(Battle battle, float x, float y, int health, int attack)
	{
		super(battle, x, y, health, attack);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
	{
		g.setColor(Color.white);
		g.drawString("Good Guy", x, y);
		g.drawString("Health: " + health, x, y + 16);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
	{
		
	}

	@Override
	public void battleAction()
	{
		battle.getEnemies()[battle.getEnemyIndex()].damage(this);
	}

}
