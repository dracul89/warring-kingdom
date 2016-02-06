package javaGame;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Stack;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class CharacterScreen extends BasicGameState 
{
	private final int MAX = 15;
	private final int MIN = 10;
	private final int ZERO = 0;
	private final int BASE = 10;
	
	private int state;	
	private int availablePts;	
	private float lineWidth;	
	private int cornerRadius; // for making rounded corners
	private String name;	
	private Stack<String> names; // for undoing a typo
	
	/*
	 * Buttons
	 */
	private ImageButton buttonNext;
	private ImageButton leftButton;
	private ImageButton rightButton;
	private ShapeButton minusStrength;
	private ShapeButton plusStrength;
	private ShapeButton minusDexterity;
	private ShapeButton plusDexterity;
	private ShapeButton minusWillpower;
	private ShapeButton plusWillpower;
	private ShapeButton minusConstitution;
	private ShapeButton plusConstitution;
	private ShapeButton minusMana;
	private ShapeButton plusMana;
	private ShapeButton confirm;
	private ShapeButton deny;
	private ArrayList<E_Profession> professions;
	private int index;
	private GameCharacterMain gameCharacter;
	private CharacterStats stats;
	private boolean blank;
	
	
	public CharacterScreen(int state, GameCharacterMain gameCharacterMain)
	{
		this.state = state;
		this.gameCharacter = gameCharacterMain;
	}
	
	@Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
        this.name = "";
        this.names = new Stack<String>();
		this.names.add(this.name);
        this.availablePts = 15;
        this.index = 0;
        this.stats = new CharacterStats();
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		index = 0;
		buttonNext = new ImageButton(gc);
		buttonNext.setButtonImage(new Image("res/button.png"));
		leftButton = new ImageButton(gc);
		leftButton.setButtonImage(new Image("res/leftArrow.png"));
		rightButton = new ImageButton(gc);
		rightButton.setButtonImage(new Image("res/rightArrow.png"));
		minusConstitution = new ShapeButton(gc);
		minusDexterity = new ShapeButton(gc);
		minusMana = new ShapeButton(gc);
		minusStrength = new ShapeButton(gc);
		minusWillpower = new ShapeButton(gc);
		plusConstitution = new ShapeButton(gc);
		plusDexterity = new ShapeButton(gc);
		plusMana = new ShapeButton(gc);
		plusStrength = new ShapeButton(gc);
		plusWillpower = new ShapeButton(gc);
		confirm = new ShapeButton(gc);
		deny = new ShapeButton(gc);
		
		/*
		 * 
		 */
		confirm.setButton(80f, 30f);
		deny.setButton(80f, 30f);
		/*
		 * 
		 */
		minusConstitution.setFillColor(Color.black);
		minusConstitution.setButton(20, 20);
		
		plusConstitution.setFillColor(Color.black);
		plusConstitution.setButton(20, 20);
		/*
		 * 
		 */
		minusStrength.setFillColor(Color.black);
		minusStrength.setButton(20, 20);
		
		plusStrength.setFillColor(Color.black);
		plusStrength.setButton(20, 20);
		/*
		 * 
		 */
		minusDexterity.setFillColor(Color.black);
		minusDexterity.setButton(20, 20);
		
		plusDexterity.setFillColor(Color.black);
		plusDexterity.setButton(20, 20);
		/*
		 * 
		 */
		minusWillpower.setFillColor(Color.black);
		minusWillpower.setButton(20, 20);
		
		plusWillpower.setFillColor(Color.black);
		plusWillpower.setButton(20, 20);
		/*
		 * 
		 */
		minusMana.setFillColor(Color.black);
		minusMana.setButton(20, 20);
		
		plusMana.setFillColor(Color.black);
		plusMana.setButton(20, 20);
		
		
		/*
		 * font stuff
		 */
		Font f = new Font("Dialog", Font.PLAIN, 20);
		new TrueTypeFont(f, false);
		
		stats = new CharacterStats();
		
		
		professions = new ArrayList<E_Profession>();
		professions.add(E_Profession.Rogue);
		professions.add(E_Profession.Barbarian);
		professions.add(E_Profession.Monk);
		professions.add(E_Profession.Mage);
		
		name = "";
		names = new Stack<String>();
		names.add(name); // empty name added to the queue
		//will get values from player character
		availablePts = 15;
		
		lineWidth = 5f;
		cornerRadius = 7;
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		
		g.getFont();
		
		g.drawString("Name: "+names.peek(), 100, 40); // change to player name
		g.drawImage(professions.get(index).getSelfie().getScaledCopy(5), 90, 70); // change to player image
		
		
		g.drawString("Class: "+professions.get(index).getProfession(), 100,(float) (gc.getHeight()*.38));//change to player class

		drawCharacterMenu(g, gc);
		leftButton.setLocation(.4,	.1);
		leftButton.drawButton("", Color.white, g);
		rightButton.setLocation(.4,.35);
		rightButton.drawButton("", Color.white, g);
		buttonNext.setLocation(.8, .5);
		buttonNext.drawButton("Continue", Color.white, g);
		// .2
		minusStrength.setLocation(.2, .825);
		minusStrength.drawButton("-", Color.red, g);
		g.setColor(Color.white);
		g.drawString(""+stats.getStrength() , (float) (gc.getWidth()*.85),(float) (gc.getHeight()*.2));
		plusStrength.setLocation(.2, .9);
		plusStrength.drawButton("+", Color.red, g);
		// .27
		minusDexterity.setLocation(.27, .825);
		minusDexterity.drawButton("-", Color.red, g);
		g.setColor(Color.white);
		g.drawString(""+stats.getDexterity() , (float) (gc.getWidth()*.85),(float) (gc.getHeight()*.27));
		plusDexterity.setLocation(.27, .9);
		plusDexterity.drawButton("+", Color.red, g);
		// .32
		minusWillpower.setLocation(.33, .825);
		minusWillpower.drawButton("-", Color.red, g);
		g.setColor(Color.white);
		g.drawString(""+stats.getWillpower() , (float) (gc.getWidth()*.85),(float) (gc.getHeight()*.33));
		plusWillpower.setLocation(.33, .9);
		plusWillpower.drawButton("+", Color.red, g);
		// .39
		minusConstitution.setLocation(.4, .825);
		minusConstitution.drawButton("-", Color.red, g);
		g.setColor(Color.white);
		g.drawString(""+stats.getConstitution() , (float) (gc.getWidth()*.85),(float) (gc.getHeight()*.4));
		plusConstitution.setLocation(.4, .9);
		plusConstitution.drawButton("+", Color.red, g);
		// .47
		minusMana.setLocation(.47, .825);
		minusMana.drawButton("-", Color.red, g);
		g.setColor(Color.white);
		g.drawString(""+stats.getMana() , (float) (gc.getWidth()*.85),(float) (gc.getHeight()*.47));
		plusMana.setLocation(.47, .9);
		plusMana.drawButton("+", Color.red, g);
		g.setColor(Color.white);
		
		
		if(blank){ //you left the name blank
			
			g.setColor(Color.red);
			g.fillRect(gc.getWidth()*.35f, gc.getHeight()*.43f,	240f , 180f);
			g.setColor(Color.white);
			g.drawRoundRect(gc.getWidth()*.35f, gc.getHeight()*.43f,	240f , 180f, 3);
			g.drawString(" Your name has been left \n"
					+ " blank if you proceed the \n name Stu"
					+ " will be assigned \n continue?",gc.getWidth()*.35f, gc.getHeight()*.43f);
			
			
			confirm.setLocation(.68, .43);
			
			deny.setLocation(.68, .58);
			g.setColor(Color.black);
			confirm.drawButton("Confirm", Color.white, g);
			confirm.drawOutline(3f, Color.white, g);
			g.setColor(Color.black);
			deny.drawButton("Nope", Color.white, g);
			deny.drawOutline(3f, Color.white, g);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		if(buttonNext.isClicked())
		{
			if(names.size() > 1){
				if(name.equals("Qwerty"))
				{
					stats.cheat();
					
				}
				
				stats.setMaxHealth(BASE*stats.getConstitution());
				stats.setMaxMagic(BASE*stats.getMana());
				stats.setHealth(BASE*stats.getConstitution());			
				stats.setMagic(BASE*stats.getMana());
				
				gameCharacter.setStats(stats);
				gameCharacter.setName(name);
				gameCharacter.setProfession(this.professions.get(index));
				if(name.equals("Tom")){
					gameCharacter.setProfession(E_Profession.Monster);
				}
				sbg.enterState(3, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
			}else{
				blank = true;
			}

		}
		if(deny.isClicked()) blank = false;
		if(confirm.isClicked()){
			name =  "Stu";
			stats.setMaxHealth(BASE*stats.getConstitution());
			stats.setMaxMagic(BASE*stats.getMana());
			stats.setHealth(BASE*stats.getConstitution());			
			stats.setMagic(BASE*stats.getMana());
			
			gameCharacter.setStats(stats);
			blank=false;
			gameCharacter.setName(name);
			gameCharacter.setProfession(this.professions.get(index));
			sbg.enterState(3, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
			
		}
		if(names.size() == 2)name = names.peek().toUpperCase();
		if(leftButton.isClicked() && index > 0){
			index--;
		}
		if(rightButton.isClicked() && index < professions.size()-1){
			index++;
		}
		if(minusConstitution.isClicked() && this.availablePts < this.MAX && stats.getConstitution() > this.MIN){
			this.availablePts++;
			stats.decreaseConstitution();
		}
		if(plusConstitution.isClicked() && this.availablePts > this.ZERO){
			this.availablePts--;
			stats.increaseConstitution();
		}
		if(minusStrength.isClicked() && this.availablePts < this.MAX && stats.getStrength() > this.MIN){
			this.availablePts++;
			stats.decreaseStrength();
		}
		if(plusStrength.isClicked() && this.availablePts > this.ZERO){
			this.availablePts--;
			stats.increaseStrength();
		}
		if(minusDexterity.isClicked() && this.availablePts < this.MAX && stats.getDexterity() > this.MIN){
			this.availablePts++;
			stats.decreaseDexterity();
		}
		if(plusDexterity.isClicked() && this.availablePts > this.ZERO){
			this.availablePts--;
			stats.increaseDexterity();
		}
		if(minusWillpower.isClicked() && this.availablePts < this.MAX && stats.getWillpower() > this.MIN){
			this.availablePts++;
			stats.decreaseWillpower();
		}
		if(plusWillpower.isClicked() && this.availablePts > this.ZERO){
			this.availablePts--;
			stats.increaseWillpower();
		}
		if(minusMana.isClicked() && this.availablePts < this.MAX && stats.getMana() > this.MIN){
			this.availablePts++;
			stats.decreaseMana();;
		}
		if(plusMana.isClicked() && this.availablePts > this.ZERO ){
			this.availablePts--;
			stats.increaseMana();
		}
	}

	@Override
	public int getID() 
	{
		return this.state;
	}
	
	
	private void drawCharacterMenu(Graphics graphics,GameContainer gameContainer){
		graphics.setLineWidth(this.lineWidth);
		graphics.setColor(Color.white);
		graphics.drawRoundRect((float)(gameContainer.getWidth()*.4)-this.lineWidth,this.lineWidth,(float) (gameContainer.getWidth()*.6),(float) (gameContainer.getHeight()*.7), this.cornerRadius);
		graphics.drawRoundRect((float)(gameContainer.getWidth()*.4)-this.lineWidth,this.lineWidth,(float) (gameContainer.getWidth()*.6),(float) (gameContainer.getHeight()*.1), this.cornerRadius);
		graphics.drawString(" Available Points: "+this.availablePts, (float)(gameContainer.getWidth()*.7),this.lineWidth*2 );
		graphics.drawString("Attributes \n \n Strength:\n Determines power of hand weapons \n Dextrity:\n Determines power of ranged weapons \n Willpower: \n Determines power of special attacks \n Constitution: \n Determines available health \n Mana: \n Determines available mana \n", (float)(gameContainer.getWidth()*.4), (float) (gameContainer.getHeight()*.125));
		
	}

	public void keyPressed(int key,char c){
		 
		switch (key) {
		case Input.KEY_A:
			this.name += "a";
			names.add(name);
			break;
		case Input.KEY_B:
			this.name += "b";
			names.add(name);
			break;
		case Input.KEY_C:
			this.name += "c";
			names.add(name);
			break;
		case Input.KEY_D:
			this.name += "d";
			names.add(name);
			break;
		case Input.KEY_E:
			this.name += "e";
			names.add(name);
			break;
		case Input.KEY_F:
			this.name += "f";
			names.add(name);
			break;
		case Input.KEY_G:
			this.name += "g";
			names.add(name);
			break;
		case Input.KEY_H:
			this.name += "h";
			names.add(name);		
			break;
		case Input.KEY_I:
			this.name += "i";
			names.add(name);			
			break;
		case Input.KEY_J:
			this.name += "j";
			names.add(name);				
			break;
		case Input.KEY_K:
			this.name += "k";
			names.add(name);					
			break;
		case Input.KEY_L:
			this.name += "l";
			names.add(name);						
			break;
		case Input.KEY_M:
			this.name += "m";
			names.add(name);
			break;
		case Input.KEY_N:
			this.name += "n";
			names.add(name);								
			break;
		case Input.KEY_O:
			this.name += "o";
			names.add(name);									
			break;
		case Input.KEY_P:
			this.name += "p";
			names.add(name);										
			break;
		case Input.KEY_Q:
			this.name += "q";
			names.add(name);
			break;
		case Input.KEY_R:
			this.name += "r";
			names.add(name);												
			break;
		case Input.KEY_S:
			this.name += "s";
			names.add(name);													
			break;
		case Input.KEY_T:
			this.name += "t";
			names.add(name);														
			break;
		case Input.KEY_U:
			this.name += "u";
			names.add(name);															
			break;
		case Input.KEY_V:
			this.name += "v";
			names.add(name);																
			break;
		case Input.KEY_W:
			this.name += "w";
			names.add(name);									
			break;
		case Input.KEY_X:
			this.name += "x";
			names.add(name);																		
			break;
		case Input.KEY_Y:
			this.name += "y";
			names.add(name);																			
			break;
		case Input.KEY_Z:
			this.name += "z";
			names.add(name);																				
			break;
		case Input.KEY_BACK:
			if(names.size() > 1){
				
				names.pop();
				this.name = names.peek();
			}
			break;
		default:
			break;
		}
	}
	public String getSelfie(){
		return professions.get(index).getSelfie().getResourceReference();
	}
}
