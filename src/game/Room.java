/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Random;

import game.audio.Sound;
import game.character.GameCharacter;
import game.character.GameCharacterMain;
import game.enemy.Enemy;
import game.enemy.EnemyFactory;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author Joe Harrison
 *
 */
public class Room 
{
	private static Room self;
	private int roomNumber;
	private int x;
	private int y;
	private int width;
	private int height;
	private int tileSize;
	private int leftBorder;
	private int topBorder;
	private TiledMap map;
	private ArrayList<Enemy> enemyList;
	private Random rand;
	private StateBasedGame sbg;
	private Sound sound;
	private EnemyFactory factory;
	private int[] clearedRooms;
	private ClearedRoomsHelper roomHelper;




	/**
	 * Constructs the room
	 */
	private Room(String passedIn, StateBasedGame sbg) throws SlickException 
	{
		try {
			this.map = new TiledMap(passedIn);
		} 
		catch (SlickException e) 
		{
			//System.out.println("There was a problem with the filename of the tiled map: " + passedIn);
			throw e;
		}

		//initialize room variables
		this.x = 0;
		this.y = 0;
		this.width = 15;
		this.height = 15;
		this.enemyList = new ArrayList<Enemy>();
		this.rand = new Random();
		this.sbg = sbg;
		this.roomNumber = 24;		
		this.factory = new EnemyFactory();
		this.clearedRooms = new int [49];
		this.roomHelper = new ClearedRoomsHelper(clearedRooms);

		//start music
		this.sound = new Sound();//MUSIC
		this.sound.playIntroMusic();
	} 

	private Room(){}//no default constructor allowed


	/*
	 * Singleton Pattern. Enforces that only one instance is ever created.
	 */
	public static Room getInstance(String tilemap, StateBasedGame sbg) throws SlickException
	{
		if(self == null)
		{
			self = new Room(tilemap, sbg);
			return self;
		}
		else
			return self;
	}

	public void reset(String tilemap, StateBasedGame sbg) throws SlickException
	{
		//initialize room variables
		this.x = 0;
		this.y = 0;
		this.width = 15;
		this.height = 15;
		this.enemyList = new ArrayList<Enemy>();
		this.rand = new Random();
		this.sbg = sbg;
		this.roomNumber = 24;		
		this.factory = new EnemyFactory();
		this.clearedRooms = new int [49];
		this.roomHelper = new ClearedRoomsHelper(clearedRooms);
		sound.playIntroMusic();
	}

	public void initRooms(GameCharacterMain player)
	{
		this.roomHelper.initClearedRooms(player);
	}

	public boolean checkCollision(GameCharacter player)
	{
		//outside of bounds of map
		if(player.getX() > 14 || player.getY() > 14 || player.getX() < 0 || player.getX() < 0)
			return true;

		int foregroundLayer = map.getLayerIndex("Foreground");
		if(map.getTileId(player.getX() + this.x ,player.getY() + this.y, foregroundLayer) == 0)
			return false;


		//the player collided with the foreground
		player.setX(player.getPreviousX());
		player.setY(player.getPreviousY());
		return true;
	}

	public void drawCharacter(GameCharacter person, Graphics g)
	{
		if(person.getX() > (width - 1))
		{
			this.moveRight();
			person.setX(0);
		}
		else if( person.getX() < 0)
		{
			this.moveLeft();
			person.setX(width - 1);
		}
		else if( person.getY() < 0)
		{
			this.moveUp();
			person.setY(height - 1);
		}
		else if(person.getY() > (height - 1))
		{
			this.moveDown();
			person.setY(0);
		}


		g.drawImage(person.getProfession().getSelfie(), person.getX() * this.tileSize + this.leftBorder, person.getY() * this.tileSize + this.topBorder);
		for(Enemy c : enemyList)
			g.drawImage(c.getProfession().getSelfie(), c.getX() * this.tileSize + this.leftBorder, c.getY() * this.tileSize + this.topBorder);
	}

	public void drawRoom()
	{
		this.map.render(tileSize, tileSize, x, y, width, height);
		if(this.enemyList.size() == 0)
			this.clearedRooms[this.roomNumber] = 1;	
	}

	public void checkForWin()
	{
		for (int i = 0; i < clearedRooms.length; i++) 
		{
			if (clearedRooms[i] == 0)
				return;
		}
		sbg.enterState(5, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
	}

	/**
	 * Moves the room's coordinates to where the room should be drawn
	 */
	public void moveRight()
	{

		this.roomNumber++;
		x += width;
		this.generateEnemies();
		sound.playAppropriateMusic(roomNumber);
		//System.out.println("ROOM#: " + roomNumber);
	}

	/**
	 * Moves the room's coordinates to where the room should be drawn
	 */
	public void moveLeft()
	{
		this.roomNumber--;

		x -= width;
		this.generateEnemies();
		sound.playAppropriateMusic(roomNumber);
		//System.out.println("ROOM#: " + roomNumber);
	}

	/**
	 * Moves the room's coordinates to where the room should be drawn
	 */
	public void moveUp()
	{

		this.roomNumber -= 7;
		y -= height;
		this.generateEnemies();
		//System.out.println("ROOM#: " + roomNumber);
		sound.playAppropriateMusic(roomNumber);
	}

	/**
	 * Moves the room's coordinates to where the room should be drawn
	 */
	public void moveDown()
	{

		this.roomNumber += 7;
		y += height;
		this.generateEnemies();
		//System.out.println("ROOM#: " + roomNumber);
		sound.playAppropriateMusic(roomNumber);
	}



	/**
	 * @return the leftBorder
	 */
	public int getLeftBorder() {
		return leftBorder;
	}
	/**
	 * @param leftBorder the leftBorder to set
	 */
	public void setLeftBorder(int leftBorder) {
		this.leftBorder = leftBorder;
	}
	/**
	 * @return the rightBorder
	 */
	public int getTopBorder() {
		return topBorder;
	}
	/**
	 * @param rightBorder the rightBorder to set
	 */
	public void setTopBorder(int rightBorder) {
		this.topBorder = rightBorder;
	}
	/**
	 * @return the map
	 */
	public TiledMap getMap() 
	{
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(TiledMap map)
	{
		this.map = map;
	}


	/**
	 * @return the tileSize
	 */
	public int getTileSize() 
	{
		return this.tileSize;
	}

	/**
	 * @param tileSize the tileSize to set
	 */
	public void setTileSize(int tileSize) 
	{
		this.tileSize = tileSize;
	}

	/**
	 * @return the x
	 */
	public int getX() 
	{
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) 
	{
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() 
	{
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) 
	{
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() 
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) 
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() 
	{
		return height;
	}

	/**
	 * 
	 * @param height the height to set
	 */
	public void setHeight(int height) 
	{
		this.height = height;
	}

	private void generateEnemies()
	{
		enemyList.clear();		
		Enemy enemy;

		for(int numberOfEnemies = rand.nextInt(5) + 1; numberOfEnemies > 0; numberOfEnemies--)
		{
			if(roomNumber == 2 || roomNumber == 6 || roomNumber == 44 || roomNumber == 48)
				numberOfEnemies = 1;
			
			try
			{
				enemy = factory.generateEnemies(roomNumber, this.clearedRooms);
				if(enemy == null)//room with no enemies
					return;
			} catch (SlickException e) 
			{
				e.printStackTrace();
				return;
			}

			enemy.setStart(rand.nextInt(13) + 1, rand.nextInt(13) + 1);
			while(this.checkCollision(enemy))//makes sure the enemy doesn't spawn on top of the player entering the screen
			{
				enemy.setStart(rand.nextInt(13) + 1, rand.nextInt(13) + 1);
			}

			enemyList.add(enemy);
		}
	}

	public void moveEnemies(GameCharacter player)
	{
		for(Enemy e : enemyList)
		{
			e.move(player);
			int stop = 0;
			while(this.checkCollision(e) && stop < 4)
			{
				e.smartMoveIfCollision(player);
				stop++;
			}
		}
	}

	public ArrayList<Enemy> getEnemies()
	{
		return this.enemyList;
	}

	public void removeEnemy(GameCharacter gc)
	{
		this.enemyList.remove(gc);
	}


}
