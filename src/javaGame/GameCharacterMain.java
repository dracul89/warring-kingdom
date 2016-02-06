/**
 * 
 */
package javaGame;

import org.newdawn.slick.SlickException;

/**
 * @author Joe
 *
 */
public class GameCharacterMain extends GameCharacter 
{

	private Enemy enemyToFight;
	private boolean myTurn;


	public GameCharacterMain(String mapImageFilename) throws SlickException
	{
		super();
		this.setMapImage(mapImageFilename);
		this.setX(0);
		this.setY(0);
		this.setPreviousX(0);
		this.setPreviousY(0);
		this.setStats(new CharacterStats());
		setMyTurn(false);
	}

	public GameCharacterMain() 
	{
		super();
		this.setX(0);
		this.setY(0);
		this.setPreviousX(0);
		this.setPreviousY(0);
		this.setStats(new CharacterStats());
	}
	public void setName(String name){ setCharacterName(name);}
	public String getName(){return getCharacterName(); }

	public boolean checkEnemyCollision(Room mapView)
	{
		for(Enemy c : mapView.getEnemies())
		{
			if(this.getX() == c.getX() && this.getY() == c.getY())
			{
				this.setEnemyToFight(c);
				mapView.removeEnemy(c);
				return true;
			}
		}
		return false;
	}

	public String increaseStat(boolean boss)
	{	
		CharacterStats stats = this.getStats();
		String statChanges = "";
		int num;
		if(boss)
			num = 5;
		else num = 1;
		
		for(int index=0; index < num; index++)
		{
			switch(rand.nextInt(5))
			{
			case 0:
				stats.increaseConstitution();
				statChanges =" Increased Constitution \n";
				break;
			case 1:
				if(this.getProfession().equals(E_Profession.Rogue)){
					stats.increaseDexterity();
					statChanges =" Increased Dextrity \n";
				}else{
					stats.increaseStrength();
					statChanges =" Increased Strength \n";
				}
				break;
			case 2:
				stats.increaseMana();
				statChanges =" Increased Mana \n";
				break;
			case 3:
				if(this.getProfession().equals(E_Profession.Rogue)){
					stats.increaseDexterity();
					statChanges =" Increased Dextrity \n";
				}else{
					stats.increaseStrength();
					statChanges =" Increased Strength \n";
				}
				break;
			case 4:
				stats.increaseWillpower();
				statChanges =" Increased Willpower \n";
				break;
			}
		}


		stats.setMaxHealth(stats.getConstitution()*10);
		stats.setMaxMagic(stats.getMana()*10);

		return statChanges+stats.toString()+"\n";
	}

	public Enemy getEnemyToFight()
	{
		return this.enemyToFight;
	}

	public void setEnemyToFight(Enemy gc)
	{
		this.enemyToFight = gc;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

}
