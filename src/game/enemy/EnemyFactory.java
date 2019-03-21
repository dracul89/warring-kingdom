package game.enemy;

import org.newdawn.slick.SlickException;

public class EnemyFactory 
{
	
	public EnemyFactory(){}
	
	public Enemy generateEnemies(int room, int[] roomList) throws SlickException
	{
		if(roomList[room] == 1)
			return null;
		else if(room == 2)
			return new EnemyRogueBoss("/res/archerboss.png");
		else if(room == 6)
			return new EnemyMageBoss("/res/mageboss.png");
		else if(room == 44)
			return new EnemyWarriorBoss("/res/barbarianboss.png");
		else if(room == 48)
			return new EnemyMonkBoss("/res/monkboss.png");
		else if(room >= 28 && room < 31 || room >= 35 && room < 38 || room >= 42 && room < 44)
			return new EnemyWarrior("/res/barbarian.png");
		else if(room >= 0 && room < 2 || room >= 7 && room < 10 || room >= 14 && room < 17)
			return new EnemyRogue("/res/archer.png");
		else if(room >= 4 && room < 6 || room >= 11 && room < 14 || room >= 18 && room < 21)
			return new EnemyMage("/res/redmage.png");
		else if(room >= 32 && room < 35 || room >= 39 && room < 42 || room >= 46 && room < 48)
			return new EnemyMonk("/res/monk.png");
		
		return null;
	}
}
