/**
 * 
 */
package game.character;

import java.util.Random;

import game.base.E_Profession;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author Joe
 *
 */
public abstract class GameCharacter 
{
	private int x;
	private int y;
	private int previousX;
	private int previousY;
	private Image mapImage;
	private CharacterStats stats;
	private E_Profession profession;
	private String characterName;
	private double percentageHealth;
	private double percentageMagic;
	private final int BASE = 4;
	private final int BASEATTACK = 15;
	
	protected Random rand;
	
	protected GameCharacter()
	{
		this.rand = new Random();
	}
	
	
	
	/**
	 * @return the mapImage
	 */
	public Image getMapImage() 
	{
		return this.mapImage;
	}

	/**
	 * @param mapImage the mapImage to set
	 */
	public void setMapImage(Image mapImage) 
	{
		this.mapImage = mapImage;
	}

	/**
	 * @param sets the map image
	 */
	public void setMapImage(String passedIn) throws SlickException
	{
		try 
		{
			this.mapImage = new Image(passedIn);
		} 
		catch (SlickException e) 
		{
			//System.out.println("There was a Problem with the filename of main character image: " + passedIn);
			throw e;
		}
	}

	
	
	/**
	 * @return the previousX
	 */
	public int getPreviousX() {
		return previousX;
	}

	/**
	 * @param previousX the previousX to set
	 */
	public void setPreviousX(int previousX) {
		this.previousX = previousX;
	}

	/**
	 * @return the previousY
	 */
	public int getPreviousY() {
		return previousY;
	}

	/**
	 * @param previousY the previousY to set
	 */
	public void setPreviousY(int previousY) {
		this.previousY = previousY;
	}

	/**
	 * @return the x
	 */
	public int getX() 
	{
		return this.x;
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
		return this.y;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	
	public CharacterStats getStats()
	{
		return stats;
	}
	
	public void setStats(CharacterStats s)
	{
		this.stats = s;
	}
	
	public void moveRight(int spaces)
	{
		this.previousX = this.x;
		this.previousY = this.y;
		this.x += spaces;
	}
	public void moveLeft(int spaces)
	{
		this.previousX = this.x;
		this.previousY = this.y;
		this.x -= spaces;
	}
	public void moveUp(int spaces)
	{
		this.previousX = this.x;
		this.previousY = this.y;
		this.y -= spaces;
	}
	public void moveDown(int spaces)
	{
		this.previousX = this.x;
		this.previousY = this.y;
		this.y += spaces;
	}

	public void setStart(int startingX, int startingY) 
	{
		this.x = startingX;
		this.y = startingY;
		this.previousX = startingX;
		this.previousY = startingY;
	}
	
	public String attack(GameCharacter character){
		int amount;
		if(getProfession() == E_Profession.Rogue){//because rogue uses ranged weapons
			amount = getStats().getDexterity()*rand.nextInt(BASE) + rand.nextInt(BASEATTACK);
		}else{
			amount = getStats().getStrength()*rand.nextInt(BASE) + rand.nextInt(BASEATTACK);
		}
		String results = getCharacterName()+" attacks "+character.getCharacterName()+" for "+amount+"\n";
		if(amount == 0){
			results = getCharacterName()+" attacks "+character.getCharacterName()+" but missed! \n";
		}else{ 
			
			character.getStats().setHealth(character.getStats().getHealth()-amount);
		}
		//System.out.println(results);
		return results;
	}
	public String specialAttack(GameCharacter character) {
		int amount;
		String results = "";
		String special = profession.getSpecial();
		if(this.getStats().getMagic() >= 10){
		this.getStats().setMagic(this.getStats().getMagic()-10);//deduct magic for special
		switch(getProfession()){
		case Rogue:
			
			for(int index=0;index < 2; index++){
			amount = (int) (this.getStats().getWillpower()*rand.nextInt(BASE) + rand.nextInt(BASEATTACK));
			//System.out.println(special+" used "+character.getCharacterName()+" was shot for "+amount);
			results += special+" used "+character.getCharacterName()+" was shot for "+amount+"\n";
			
			character.getStats().setHealth( (character.getStats().getHealth()-amount));
			}
			break;
		case Monk:
			
			amount = (int) (this.getStats().getWillpower()*rand.nextInt(2*BASE) + rand.nextInt(BASEATTACK));
			//System.out.println(special+" used "+this.characterName+" was healed for "+amount);
			results += special+" used "+this.characterName+" was healed for "+amount+"\n";
			this.getStats().setHealth( (this.getStats().getHealth()+amount));
			break;
		case Barbarian:
			
			amount = (int) (this.getStats().getWillpower()*rand.nextInt(2*BASE) + rand.nextInt(BASEATTACK));
			//System.out.println(special+" used on "+character.getCharacterName()+" for "+amount);
			results += special+" used on "+character.getCharacterName()+" for "+amount+"\n";
			character.getStats().setHealth(character.getStats().getHealth()-amount);
			break;
		case Mage:
			
			
			//this is a Damage over time attack it might look like an error but it's not.
			for(int index=0;index < 4;index++){
				amount = (int) (this.getStats().getWillpower()*rand.nextInt(BASE-1) + rand.nextInt(BASEATTACK)); //calculates a new amount each time
				character.getStats().setHealth((character.getStats().getHealth()-amount));
				//System.out.println(special+" used "+this.characterName+" ignites "+character.getCharacterName()+" for "+amount+" damage");
				results += special+" used "+this.characterName+" ignites "+character.getCharacterName()+" for "+amount+" damage \n";
			}
			break;
		case Monster:
			special = profession.getSpecial();
			amount = (int) (this.getStats().getWillpower()*rand.nextInt(2*BASE) + rand.nextInt(BASEATTACK));
			character.getStats().setHealth((character.getStats().getHealth()-amount));
			//System.out.println(special+" used "+this.characterName+" heckles "+character.getCharacterName()+" for "+amount+" nerf damage");
			results += special+" used "+this.characterName+" heckles "+character.getCharacterName()+" for "+amount+" nerf damage \n";
			break;
		}
		}
		return results;
	}

	
	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public void battleDisplay(GameContainer gc,Graphics g,float locationX,float locationY){
		percentageHealth = stats.getHealth()/(double)stats.getMaxHealth();
		percentageMagic = stats.getMagic()/(double)stats.getMaxMagic();
		g.drawString(this.characterName, gc.getWidth()*(locationX+.03f), gc.getHeight()*(locationY-.03f));
		if(percentageHealth < .33){
			g.setColor(Color.red);
		}else{
			g.setColor(Color.yellow);
		}
		if(percentageHealth > .9 )percentageHealth = 1.0;//trying to fix rounding issues
		g.fillRoundRect(gc.getWidth()*locationX, gc.getHeight()*locationY, (float) (80*percentageHealth), 10, 0);
		g.setColor(Color.white);
		g.drawRoundRect(gc.getWidth()*locationX, gc.getHeight()*locationY, 80, 10, 0);
		
		if(percentageMagic < .33){
			g.setColor(Color.green);
		}else{
			g.setColor(Color.blue);
		}
		g.fillRoundRect(gc.getWidth()*locationX, gc.getHeight()*(locationY+.025f), (float) (80*percentageMagic), 10, 0);
		g.setColor(Color.white);
		g.drawRoundRect(gc.getWidth()*locationX, gc.getHeight()*(locationY+.025f), 80, 10, 0);
	}

	public E_Profession getProfession() {
		return profession;
	}

	public void setProfession(E_Profession profession) {
		this.profession = profession;
	}
}
