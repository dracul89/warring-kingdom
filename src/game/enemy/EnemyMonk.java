package game.enemy;

import game.character.CharacterStats;
import game.base.E_Profession;
import org.newdawn.slick.SlickException;

public class EnemyMonk extends Enemy 
{

	public EnemyMonk(String mapImageFilename) throws SlickException 
	{
		super();
		this.setMapImage(mapImageFilename);
		this.setX(0);
		this.setY(0);
		this.setPreviousX(0);
		this.setPreviousY(0);
		this.setStats(new CharacterStats());
		this.setCharacterName("Monk");
		this.setProfession(E_Profession.Monk);
		this.setImage(mapImageFilename);
		this.boss = false;
		
		/*
		for(int i = 0; i<8; i++)//CONST: 18 STRN: 14
		{
			this.getStats().increaseConstitution();
			if(i%2 == 0)
				this.getStats().increaseStrength();
		}*/
		
		//base mana and health = 10 * stats
		this.getStats().setMaxHealth(this.getStats().getConstitution()*10);
		this.getStats().setMaxMagic(this.getStats().getMana()*10);
		this.getStats().setHealth(this.getStats().getConstitution()*10);
		this.getStats().setMagic(this.getStats().getMana()*10);
		
	}

}
