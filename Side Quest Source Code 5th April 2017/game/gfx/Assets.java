package game.gfx;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Assets
{
	
	public static SpriteSheet enemies, characters;
	public static Image enemySheet, skeleton, deadSkeleton,
						characterSheet, player, playerOver;
	
	public static void init()
	{
		try
		{
			enemySheet = new Image("res/EnemySheet.png");
			characterSheet = new Image("res/CharacterSheet.png");
			enemies = new SpriteSheet(enemySheet, 32, 32);
			characters = new SpriteSheet(characterSheet, 32, 32);
			skeleton = enemies.getSprite(4, 2);
			deadSkeleton = enemies.getSprite(8, 2);
			player = characters.getSprite(6, 1);
			playerOver = characters.getSprite(6, 0);
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}
