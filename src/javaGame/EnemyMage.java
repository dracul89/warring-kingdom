package javaGame;

import org.newdawn.slick.SlickException;

public class EnemyMage extends Enemy 
{

	public EnemyMage(String mapImageFilename) throws SlickException 
	{
		super();
		this.setMapImage(mapImageFilename);
		this.setX(0);
		this.setY(0);
		this.setPreviousX(0);
		this.setPreviousY(0);
		this.setStats(new CharacterStats());	
		this.setCharacterName("Mage");
		this.setProfession(E_Profession.Mage);
		this.setImage(mapImageFilename);
		this.boss = false;
		
		/*
		for(int i = 0; i<8; i++)//WILL: 18 MANA: 14
		{
			this.getStats().increaseWillpower();
			if(i%2 == 0)
				this.getStats().increaseMana();
		}*/
		
		//base mana and health = 10 * stats
		this.getStats().setMaxHealth(this.getStats().getConstitution()*10);
		this.getStats().setMaxMagic(this.getStats().getMana()*10);
		this.getStats().setHealth(this.getStats().getConstitution()*10);
		this.getStats().setMagic(this.getStats().getMana()*10);
		
	}

}
