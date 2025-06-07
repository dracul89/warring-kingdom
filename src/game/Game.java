package game;
/*
 * CHEATS: use name 'qwerty' to have max stats
 * use name "Tom" for easter egg
 * press '0' during combat to insta-kill enemy
 * 
 */
import game.character.GameCharacterMain;
import game.screen.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Game extends StateBasedGame
{
	
	public static final int menuScreen = 0;
	public static final int helpScreen = 1;
	public static final int characterScreen = 2;
	public static final int mapScreen = 3;
	public static final int battleScreen = 4;
	public static final int winLoseScreen = 5;
	private GameCharacterMain hero;


	public Game()
	{
		
		super("Warring Kingdoms");
		hero = new GameCharacterMain();
		this.addState(new MenuScreen(menuScreen));
		this.addState(new HelpScreen(helpScreen));
		this.addState(new CharacterScreen(characterScreen,hero));
		this.addState(new MapScreen(mapScreen,hero));
		this.addState(new BattleScreen(battleScreen,hero));
		this.addState(new WinLoseScreen(winLoseScreen,hero));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		this.getState(menuScreen).init(gc, this);
		this.getState(helpScreen).init(gc, this);
		this.getState(characterScreen).init(gc, this);
		this.getState(mapScreen).init(gc, this);
		this.getState(battleScreen).init(gc, this);
		this.getState(winLoseScreen).init(gc, this);

		this.enterState(menuScreen);
	}


	public static void main(String[] args) 
	{
		AppGameContainer appGC;
		try
		{
//			System.setProperty("net.java.games.input.useDefaultPlugin", "false");
			appGC = new AppGameContainer(new Game());
			appGC.setDisplayMode(800, 600, false);
			appGC.start();
		}
		catch(SlickException e)
		{
			System.err.println(e.getMessage());
		}
		
	}

}
