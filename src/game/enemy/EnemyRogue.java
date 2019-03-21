package game.enemy;

import game.character.CharacterStats;
import game.base.E_Profession;
import org.newdawn.slick.SlickException;

public class EnemyRogue extends Enemy 
{
	
	public EnemyRogue(String mapImageFilename) throws SlickException 
	{
		super();
		this.setMapImage(mapImageFilename);
		this.setX(0);
		this.setY(0);
		this.setPreviousX(0);
		this.setPreviousY(0);
		this.setStats(new CharacterStats());
		this.setCharacterName("Rogue");
		this.setProfession(E_Profession.Rogue);
		this.setImage(mapImageFilename);
		this.boss = false;
		
		/*
		for(int i = 0; i<6; i++)//DEX: 16 CONST: 13
		{
			this.getStats().increaseDexterity();
			if(i%2 == 0)
				this.getStats().increaseConstitution();
		}*/
		
		//base mana and health = 10 * stats
		this.getStats().setMaxHealth(this.getStats().getConstitution()*10);
		this.getStats().setMaxMagic(this.getStats().getMana()*10);
		this.getStats().setHealth(this.getStats().getConstitution()*10);
		this.getStats().setMagic(this.getStats().getMana()*10);
		
	}

}
