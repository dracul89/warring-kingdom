package game.base;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public enum E_Profession{
	Rogue("Rogue","res/archer.png","Double Shot","Shot"),
	Monk("Monk","res/monk.png","Healing Hymm","Punch"),
	Barbarian("Barbarian","res/barbarian.png","Skull Crush","Bash"),
	Mage("Mage","res/redmage.png","Ignite","Stab"),
	Monster("Monster","res/skeleton.png","Lecture","Glare");
	private String special;
	private String profession;
	private Image selfie;
	private String attack;
	private E_Profession(String profession,String selfie,String special,String attack){
		this.profession = profession;
		this.special = special;
		this.attack = attack;
		try {
			this.selfie = new Image(selfie);
		} catch (SlickException e) {
			Log.error(e);
		}
	}
	public String getProfession(){
		return profession;
	}
	public Image getSelfie(){
		return selfie;
	}
	public String getAttack(){
		return attack;
	}
	public String getSpecial(){
		return special;
	}
	
	public void setSelfie(String fileName)
	{
		try {
			this.selfie = new Image(fileName);
		} catch (SlickException e) 
		{
			Log.error(e);
		}
	}
}
