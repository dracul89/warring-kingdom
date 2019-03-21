package game.screen;
//team unhandled exception
import game.buttons.ShapeButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class HelpScreen extends BasicGameState 
{
	private int state;
	private ShapeButton doneButton;
	private String text;
	
	public HelpScreen(int state)
	{
		this.state = state;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		doneButton = new ShapeButton(gc);
		
		doneButton.setFillColor(Color.red);
		doneButton.setRoundedButton(120, 40, 0);
		
		text = "You are a brave adventurer who must choose a side in an epic war over four kingdoms.\n\n" + 
		"To the northwest lay the lush forests of the Rogues.\n" +
		"To the northeast lay the frigid plains of the Mages.\n" +
		"To the southwest lay the crude wastelands of the Barbarians.\n" +
		"To the southeast lay the quiet valley of the Monks.\n\n" +
		"You must choose your kingdom and slay all others to control all four kingdoms.\n\n" +
		"Use w, a, s, d, or arrow keys to move.";

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		doneButton.setLocation(.7, .5);
		doneButton.drawButton("Done", Color.white,g);
		doneButton.drawOutline(5f, Color.decode("#c0c0ca"), g);
		
		g.drawString(text, 10, (gc.getHeight()*.1f));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		if(doneButton.isClicked())
		{
			sbg.enterState(0, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
	}

	@Override
	public int getID() 
	{
		return this.state;
	}

}
