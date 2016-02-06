package javaGame;


import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MapScreen extends BasicGameState 
{

	private int state;
	private Room mapView;
	private double psuedoTimer;
	private boolean roomsInited;
	private final int PASSIVEHEAL = 2;

	private GameCharacterMain hero;
	

	
	public MapScreen(int state,GameCharacterMain gameCharacterMain)
	{
		this.state = state;
		this.hero = gameCharacterMain; 

	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		if(roomsInited)
			mapView.reset("res/worldMap.tmx", sbg);
		this.roomsInited = false;
		mapView = Room.getInstance("res/worldMap.tmx", sbg);
		mapView.setX(45);
		mapView.setY(45);
		mapView.setHeight(15);
		mapView.setWidth(15);
		mapView.setTileSize(32);
		mapView.setLeftBorder(32);
		mapView.setTopBorder(32);
		
		hero.setStart(7, 7);//x y coordinates
		this.psuedoTimer = 0;
		
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{		
		mapView.drawRoom();//important to do this before all characters && objects
		mapView.drawCharacter(hero, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{	
		if(!this.roomsInited)
		{
			mapView.initRooms(hero);
			this.roomsInited = true;
		}
		
		if(this.psuedoTimer > 9999999999999d)
			this.psuedoTimer = 1;
		
		double temp = Math.ceil(this.psuedoTimer);
		this.psuedoTimer += delta/500f; //half second interval
		
		if(this.psuedoTimer > temp)
		{
			mapView.moveEnemies(hero);
			checkHeroEnemyCollision(sbg, mapView);
			passiveHealing(hero);
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_D) || gc.getInput().isKeyPressed(Input.KEY_RIGHT))
		{
				hero.moveRight(1);
				this.collisionChecks(sbg);
		}
		
		else if(gc.getInput().isKeyPressed(Input.KEY_S) || gc.getInput().isKeyPressed(Input.KEY_DOWN))
		{
				hero.moveDown(1);
				this.collisionChecks(sbg);
		}
		else if(gc.getInput().isKeyPressed(Input.KEY_W) || gc.getInput().isKeyPressed(Input.KEY_UP))
		{
				hero.moveUp(1);
				this.collisionChecks(sbg);
		}
		else if(gc.getInput().isKeyPressed(Input.KEY_A) || gc.getInput().isKeyPressed(Input.KEY_LEFT))
		{
				hero.moveLeft(1);
				this.collisionChecks(sbg);
		}
		
		mapView.checkForWin();
	}
	
	private void passiveHealing(GameCharacterMain player)
	{
		player.getStats().setHealth(player.getStats().getHealth() + PASSIVEHEAL);
		player.getStats().setMagic(player.getStats().getMagic() + PASSIVEHEAL);
	}
	
	private void collisionChecks(StateBasedGame sbg)
	{
		checkHeroEnemyCollision(sbg, mapView);
		mapView.checkCollision(hero);
	}
	
	private void checkHeroEnemyCollision(StateBasedGame sbg, Room mapView)
	{
		if(hero.checkEnemyCollision(mapView))
		{
			sbg.enterState(4, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
	}

	@Override
	public int getID() 
	{
		return this.state;
	}
	
}
