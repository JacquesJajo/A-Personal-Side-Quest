package game.gfx;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Assets
{
	
	public static SpriteSheet enemies, characters, forest, market;
	public static Image enemySheet, skeleton, deadSkeleton, ogre, deadOgre,
						characterSheet, pink, grey, yellow, playerOver,
						background,
						forestSheet, grass, path,
						marketImage;
	public static Image[] pinkWalkLeft, greyWalkLeft, yellowWalkLeft, skeletonWalkRight, ogreWalkRight;
	public static List<Image> forestSprites, marketSprites;
	
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
			ogre = enemies.getSprite(4, 0);
			deadOgre = enemies.getSprite(8, 0);
			pink = characters.getSprite(6, 1);
			grey = characters.getSprite(3, 1);
			yellow = characters.getSprite(0, 5);
			playerOver = characters.getSprite(6, 0);
			background = new Image("res/Background.png");
			
			forestSheet = new Image("res/worldset.png");
			forest = new SpriteSheet(forestSheet, 32, 32);
			grass = forest.getSprite(6, 0);
			path = forest.getSprite(10, 0);
			forestSprites = sliceSheet(forest, 32, 32);
			
			marketImage = new Image("res/PathAndObjects_0.png");
			market = new SpriteSheet(marketImage, 32, 32);
			marketSprites = sliceSheet(market, 32, 32);
			
			pinkWalkLeft = new Image[3];
			pinkWalkLeft[0] = characters.getSprite(6, 1);
			pinkWalkLeft[1] = characters.getSprite(7, 1);
			pinkWalkLeft[2] = characters.getSprite(8, 1);
			
			greyWalkLeft = new Image[3];
			greyWalkLeft[0] = characters.getSprite(3, 1);
			greyWalkLeft[1] = characters.getSprite(4, 1);
			greyWalkLeft[2] = characters.getSprite(5, 1);
			
			yellowWalkLeft = new Image[3];
			yellowWalkLeft[0] = characters.getSprite(0, 5);
			yellowWalkLeft[1] = characters.getSprite(1, 5);
			yellowWalkLeft[2] = characters.getSprite(2, 5);
			
			skeletonWalkRight = new Image[2];
			skeletonWalkRight[0] = enemies.getSprite(4, 2);
			skeletonWalkRight[1] = enemies.getSprite(5, 2);
			
			ogreWalkRight = new Image[2];
			ogreWalkRight[0] = enemies.getSprite(4, 0);
			ogreWalkRight[1] = enemies.getSprite(5, 0);
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public static List<Image> sliceSheet(SpriteSheet sheet, int spriteWidth, int spriteHeight)
	{
		List<Image> sprites = new ArrayList<Image>();
		
		for (int y = 0; y < sheet.getHeight() / spriteHeight; y++)
			for (int x = 0; x < sheet.getWidth() / spriteWidth; x++)
				sprites.add(sheet.getSprite(x, y));
			
		return sprites;
	}
}
