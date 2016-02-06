package javaGame;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class BattleScreen extends BasicGameState 
{
	private int state;
	private GameCharacterMain hero;

	private final float lineWidth = 3f;
	private Image background;
	private Sound sound;
	private LinkedList<String> battle;
	private String battleInfo;
	private ShapeButton attackButton;
	private ShapeButton specialButton;
	private ShapeButton itemsButton;
	private ShapeButton continueButton;
	private String distributed;
	private StopWatch stopWatch;

	public BattleScreen(int state, GameCharacterMain hero)
	{
		this.state = state;
		this.hero = hero;
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.hero.setMyTurn(true);		
		battle = new LinkedList<String>();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sound = new Sound();
		background = new Image("res/scenery.jpg");

		this.hero.setMyTurn(true);		
		battle = new LinkedList<String>();

		attackButton = new ShapeButton(gc);
		specialButton = new ShapeButton(gc);
		itemsButton = new ShapeButton(gc);
		continueButton = new ShapeButton(gc);
		continueButton.setFillColor(Color.red);
		attackButton.setFillColor(Color.black);
		specialButton.setFillColor(Color.black);
		attackButton.setRoundedButton(60, 15, 0);
		specialButton.setRoundedButton(60, 15, 0);
		continueButton.setButton(100, 30);
		this.distributed = "";
		stopWatch = StopWatch.start();
		

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{

		layoutBattle(gc, g);

		//g.drawRoundRect(this.lineWidth, (gc.getHeight()*.76f)-lineWidth,gc.getWidth()*.31f-2*lineWidth, gc.getHeight()*.25f-lineWidth, 3);


		/*
		 * battle text
		 */
		String battleInfo ="";
		while(battle.size() > 6){
			battle.removeFirst();
			battleInfo = "";
		}
		if(!battle.isEmpty()){

			for(int index = 0;index <battle.size();index++){

				battleInfo += battle.get(index);
			}
			g.setColor(Color.white);
			g.drawString(battleInfo, 3.02f, (gc.getHeight()*.76f));
			
		}
		

		if(hero.getEnemyToFight().getStats().getHealth() <= 0){
			g.setColor(Color.black);
			g.fillRect(gc.getWidth()*.3f, gc.getHeight()*.15f, 300, 350);
			g.setColor(Color.white);
			g.drawRoundRect(gc.getWidth()*.3f, gc.getHeight()*.15f, 300, 350, 3);
			g.drawString(distributed, gc.getWidth()*.35f, gc.getHeight()*.2f);
			continueButton.setLocation(.65, .5);

			continueButton.drawButton("Continue", Color.white, g);
			continueButton.drawOutline(3f, Color.white, g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{


		
		/*
		 * Hero dies
		 */
		if(hero.getStats().getHealth() <= 0){
			sbg.enterState(5, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
		/*
		 * Monster dies
		 */
		if(hero.getEnemyToFight().getStats().getHealth() <= 0){

			hero.setMyTurn(true);

			//while(!battle.isEmpty()) battle.remove(); // empty the list
			if(distributed.equals("")){
				if(hero.getEnemyToFight().isBoss())
					distributed = hero.increaseStat(true);
				else
					distributed = hero.increaseStat(false);
				//System.out.println(hero.getCharacterName()+" defeated the "+hero.getEnemyToFight().getCharacterName()+"\n"+hero.getStats().toString());

				
			}
		}
		/*
		 * Buttons clicked
		 */
		if(continueButton.isClicked()){
			distributed = ""; // reset
			while(!battle.isEmpty()) battle.remove(); // empty the list
			sbg.enterState(3, new FadeOutTransition(Color.red), new FadeInTransition(Color.red));
		}
		/*
		 * turn management
		 */
		if(!hero.isMyTurn()){

			hero.setMyTurn(true);
			if(Math.random() > .1){
				battle.add(hero.getEnemyToFight().attack(hero));

			}else{
				battle.add(hero.getEnemyToFight().specialAttack(hero));
				while(battle.size() > 6){
					battle.removeFirst();
					battleInfo = "";
				}

			}

		}else{
				if(hero.getEnemyToFight().getStats().getHealth() > 0){
					if(attackButton.isClicked()){
					attackEnemy();
					}
					if(specialButton.isClicked()){
						specialEnemy();
	
					}
				}
			}
		 
		 
		}

	@Override
	public int getID() 
	{
		return this.state;
	}

	public void keyPressed(int key,char c){
		if(hero.getEnemyToFight().getStats().getHealth() > 0){
			switch(key){
			case Input.KEY_ESCAPE:
				//System.out.println("Going back to MapState");
				break;
			case Input.KEY_1:
				attackEnemy();
				break;
			case Input.KEY_2:
				specialEnemy();
				break;
			case Input.KEY_0://cheat to kill enemy
				hero.getEnemyToFight().getStats().setHealth(0);
				break;
			}
		}
	}

	private void attackEnemy(){ // helper method for attack 
		if(hero.isMyTurn()){ // keeps the player from cheating
			sound.playAttackSound(hero.getProfession());
			hero.setMyTurn(false);
			battle.add(hero.attack(hero.getEnemyToFight()));
		}

	}
	private void specialEnemy(){

		if(hero.isMyTurn()){ // what he said
			String special = hero.specialAttack(hero.getEnemyToFight());
			if(!special.equals("")){//special happened
				sound.playSpecialAttackSound(hero.getProfession());
				battle.add(special);

				hero.setMyTurn(false);
			}
			while(battle.size() > 6){
				battle.removeFirst();
				battleInfo = "";
			}
		}
	}
	public void layoutBattle(GameContainer gc,Graphics g){
		//background
		g.drawImage(background.getScaledCopy(gc.getWidth(), (int) (gc.getHeight()*.75f)), 0, 0);
		//hero stuff
		g.drawImage(hero.getProfession().getSelfie().getScaledCopy(5), gc.getWidth()-(32*5), gc.getHeight()*.45f);

		hero.battleDisplay(gc, g, .8f, .37f);
		if(!hero.getEnemyToFight().equals(null)){
			g.drawImage(hero.getEnemyToFight().getMapImage().getScaledCopy(5), gc.getWidth()*.05f, gc.getHeight()*.45f);
		}
		//enemy health bar / magic bar
		hero.getEnemyToFight().battleDisplay(gc, g, .05f, .4f);

		//layout
		g.setColor(Color.white);		
		g.setLineWidth(this.lineWidth);
		g.drawRoundRect(this.lineWidth, (gc.getHeight()*.76f)-lineWidth,gc.getWidth()-2*lineWidth, gc.getHeight()*.25f-lineWidth, 3);
		g.drawRoundRect(gc.getWidth()*.7f-this.lineWidth, (gc.getHeight()*.76f)-lineWidth,gc.getWidth()*.31f-2*lineWidth, gc.getHeight()*.25f-lineWidth, 3);
		//buttons
		attackButton.setLocation(.77, .75);
		specialButton.setLocation(.8, .75);

		attackButton.drawButton("1. "+hero.getProfession().getAttack(), Color.white, g);
		specialButton.drawButton("2. "+hero.getProfession().getSpecial(), Color.white, g);
	}
	public void clearList(){
		while(!battle.isEmpty()){
			battle.removeFirst();
		}
	}

}
