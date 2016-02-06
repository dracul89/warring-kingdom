package javaGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class WinLoseScreen extends BasicGameState 
{

	private int state;
	private GameCharacterMain hero;
	private ShapeButton button;
	private ShapeButton exitButton;
	public WinLoseScreen(int state, GameCharacterMain hero)
	{
		this.state = state;
		this.hero = hero;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// TODO Auto-generated method stub
		button = new ShapeButton(gc);
		button.setFillColor(Color.red);
		button.setRoundedButton(120, 40, 0);
		exitButton = new ShapeButton(gc);
		exitButton.setFillColor(Color.red);
		exitButton.setRoundedButton(120, 40, 0);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// TODO Auto-generated method stub
		if(hero.getStats().getHealth() <= 0)
			g.drawString("You Suck!!",gc.getWidth()*.45f, gc.getHeight()*.5f);
		else
			g.drawString("You are Victorious!!",gc.getWidth()*.45f, gc.getHeight()*.5f);
		button.setLocation(.7, .5);
		button.drawButton("New Game", Color.white,g);
		button.drawOutline(5f, Color.decode("#c0c0ca"), g);
		exitButton.setLocation(.8, .5);
		exitButton.drawButton("Exit", Color.white,g);
		exitButton.drawOutline(5f, Color.decode("#c0c0ca"), g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		if(button.isClicked()){
			sbg.getState(3).init(gc, sbg);
			sbg.enterState(0, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
		if(exitButton.isClicked()){
			System.exit(0);
		}

	}

	@Override
	public int getID() 
	{
		return this.state;
	}


}
