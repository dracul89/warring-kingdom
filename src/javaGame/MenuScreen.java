package javaGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class MenuScreen extends BasicGameState 
{
	private int state;
	private ShapeButton button;
	private ShapeButton helpButton;
	private ShapeButton exitButton;
	private Image title;
	public MenuScreen(int state)
	{
		this.state = state;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		title = new Image("/res/Title.png");
		button = new ShapeButton(gc);
		helpButton = new ShapeButton(gc);
		exitButton = new ShapeButton(gc);
		
		button.setFillColor(Color.red);
		button.setRoundedButton(120, 40, 0);
		
		helpButton.setFillColor(Color.red);
		helpButton.setRoundedButton(120, 40, 0);
		
		exitButton.setFillColor(Color.red);
		exitButton.setRoundedButton(120, 40, 0);
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		
		g.drawImage(title, gc.getWidth()*.5f-(title.getWidth()/2), gc.getHeight()*.2f-(title.getHeight()/2));
		
		button.setLocation(.3, .5);
		button.drawButton("New Game", Color.white,g);
		button.drawOutline(5f, Color.decode("#c0c0ca"), g);
		
		helpButton.setLocation(.4, .5);
		helpButton.drawButton("How to Play", Color.white, g);
		helpButton.drawOutline(5f, Color.decode("#c0c0ca"), g);
		
		exitButton.setLocation(.5, .5);
		exitButton.drawButton("Exit", Color.white, g);
		exitButton.drawOutline(5f, Color.decode("#c0c0ca"), g);
		
		
		g.setColor(Color.red);
		g.drawString("Copyright 2014 Team unhandledException \n JD Hall, Joe Harrison, Danny Beaumont",gc.getWidth()*.45f-(title.getWidth()/2) , gc.getHeight()*.9f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		if(button.isClicked()){
			sbg.enterState(2, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
		if(helpButton.isClicked())
		{
			sbg.enterState(1, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
		if(exitButton.isClicked())
		{
			System.exit(0);
		}

	}

	@Override
	public int getID() 
	{
		return this.state;
	}

}
