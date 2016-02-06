package javaGame;

public class CharacterStats 
{
	private int str, dex, will, cons, mana, health, magic,maxHealth,maxMagic;

	public CharacterStats()
	{
		this.str = 10;
		this.dex = 10;
		this.will = 10;
		this.cons = 10;
		this.mana = 10;
		this.health = 100;
		this.magic = 20;
	}
	
	public void cheat()
	{
		this.str = 9999;
		this.dex = 9999;
		this.will = 9999;
		this.cons = 9999;
		this.mana = 9999;
		this.health = 9999;
		this.magic = 9999;
	}

	public int getStrength()
	{
		return this.str;
	}

	public int getDexterity()
	{
		return this.dex;
	}

	public int getWillpower()
	{
		return this.will;
	}

	public int getConstitution()
	{
		return this.cons;
	}

	public int getMana()
	{
		return this.mana;
	}
	public int getHealth(){
		return this.health;
	}
	public int getMagic(){
		return this.magic;
	}
	public void setHealth(int number){
		if(number > 0){
			if(number >= maxHealth){
				this.health = maxHealth;
			}else{	
				this.health = number;
			}
		}else{
			this.health = 0;
		}
	}
	public void setMagic(int number){
		if(number > 0){
			if(number >= maxMagic){
				this.magic = maxMagic;
			}else{
				this.magic = number;
			}
		}else{
			this.magic = 0;
		}
	}
	public void increaseHealth(int amount){
		this.health += amount;
	}
	public void decreaseHealth(int amount){
		this.health -= amount;
	}
	public void increaseStrength()
	{
		this.str++;
	}

	public void increaseDexterity()
	{
		this.dex++;
	}

	public void increaseWillpower()
	{
		this.will++;
	}

	public void increaseConstitution()
	{
		this.cons++;
	}

	public void increaseMana()
	{
		this.mana++;
	}
	public void decreaseStrength(){ this.str--;	}
	public void decreaseDexterity(){ this.dex--; }
	public void decreaseWillpower(){ this.will--; }
	public void decreaseConstitution(){this.cons--;}
	public void decreaseMana(){ this.mana--; }

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxMagic() {
		return maxMagic;
	}

	public void setMaxMagic(int maxMagic) {
		this.maxMagic = maxMagic;
	}
	public void increaseMagic(){
		if(this.magic >= maxMagic){
			this.magic = maxMagic;
		}else{	
			this.magic++;
		}
	}
	public void increaseHealth(){
		if(this.health >= maxHealth){
			this.health = maxHealth;
		}else{	
			this.health++;
		}
	}
	public String toString(){
		String toReturn = "\n Max Health:"+this.maxHealth
				+"\n Max Magic: "+this.maxMagic
				+"\n\n Str: "+this.str
				+"\n Dex: "+this.dex
				+"\n Willpower: "+this.will
				+"\n Constitution: "+this.cons
				+"\n Mana: "+this.mana
				+"\n Health: "+this.health
				+"\n Magic "+this.magic;
		return toReturn;
	}
}
