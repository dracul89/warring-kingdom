package javaGame;


import org.newdawn.slick.SlickException;

public class EnemyMageBoss extends Enemy 
{

	public EnemyMageBoss(String mapImageFilename) throws SlickException 
	{
		super();
		this.setMapImage(mapImageFilename);
		this.setX(0);
		this.setY(0);
		this.setPreviousX(0);
		this.setPreviousY(0);
		this.setStats(new CharacterStats());
		this.setCharacterName("Magnus");
		this.setProfession(E_Profession.Mage);
		this.setImage(mapImageFilename);
		this.boss = true;
		
		
		for(int i = 0; i<50; i++)//CONS: 50 STRN: 5 WILL: 5
		{
			this.getStats().increaseConstitution();
			if(i%10 == 0)
			{
				this.getStats().decreaseStrength();
				this.getStats().decreaseWillpower();
			}
		}
		
		//base mana and health = 10 * stats
		this.getStats().setMaxHealth(this.getStats().getConstitution()*10);
		this.getStats().setMaxMagic(this.getStats().getMana()*10);
		this.getStats().setHealth(this.getStats().getConstitution()*10);
		this.getStats().setMagic(this.getStats().getMana()*10);
		
	}

}