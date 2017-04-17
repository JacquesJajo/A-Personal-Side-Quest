package game.entities.overworld;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.gfx.Assets;
import game.states.State;
import game.states.battle.BattleState;
import game.states.overworld.LocalMap;
import game.tiles.TileMap;

public class Player extends MapEntity
{

	private TileMap map;
	private boolean isMoving, moveLeft, moveRight, moveUp, moveDown;
	private float prevY, prevX;
	private Random random;
	
	public Player(float x, float y, State over, TileMap map)
	{
		super(x, y, over);
		this.map = map;
		random = new Random();
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		Assets.playerOver.draw(9 * 32, 7 * 32);
	}

	@Override
	public void update(GameContainer gc, int delta)
	{
		
		int tileX = (int) (x / 32);
		int tileY = (int) (y / 32);
		
		if (!isMoving && gc.getInput().isKeyPressed(Input.KEY_UP) && map.getLayer(LocalMap.COLLISION_LAYER).getTileId(tileX, tileY - 1) == -1)
		{
			isMoving = true;
			moveUp = true;
			prevY = y;

		} else if (!isMoving && gc.getInput().isKeyPressed(Input.KEY_DOWN) && map.getLayer(LocalMap.COLLISION_LAYER).getTileId(tileX, tileY + 1) == -1)
		{
			isMoving = true;
			moveDown = true;
			prevY = y;

		} else if (!isMoving && gc.getInput().isKeyPressed(Input.KEY_LEFT) && map.getLayer(LocalMap.COLLISION_LAYER).getTileId(tileX - 1, tileY) == -1)
		{
			isMoving = true;
			moveLeft = true;
			prevX = x;

		} else if (!isMoving && gc.getInput().isKeyPressed(Input.KEY_RIGHT) && map.getLayer(LocalMap.COLLISION_LAYER).getTileId(tileX + 1, tileY) == -1)
		{
			isMoving = true;
			moveRight = true;
			prevX = x;
		}
		
		if (moveUp)
		{
			y--;
			map.move(0, 1);

			if (y <= prevY - 32)
			{
				moveUp = false;
				isMoving = false;
				checkCombat();
			}
		}
		
		else if (moveDown)
		{
			y++;
			map.move(0, -1);

			if (y >= prevY + 32)
			{
				moveDown = false;
				isMoving = false;
				checkCombat();
			}
		}
		
		else if (moveLeft)
		{
			x--;
			map.move(1, 0);

			if (x <= prevX - 32)
			{
				moveLeft = false;
				isMoving = false;
				checkCombat();
			}
		}
		
		else if (moveRight)
		{
			x++;
			map.move(-1, 0);

			if (x >= prevX + 32)
			{
				moveRight = false;
				isMoving = false;
				checkCombat();
			}
		}
	}
	
	public void checkCombat()
	{
		int combat = random.nextInt(10);
		
		if (combat == 0)
		{
			BattleState battle = new BattleState(over);
			battle.addEntities(battle.generateEntities());
			State.enterState(battle);
		}
	}

}
