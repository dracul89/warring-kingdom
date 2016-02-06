package javaGame;

public class ClearedRoomsHelper 
{
	int [] rooms;
	
	public ClearedRoomsHelper(int [] rooms)
	{
		this.rooms = rooms;
	}
	
	public void initClearedRooms(GameCharacterMain player)
	{
		//no mans land
		for(int i = 3; i < 46; i+=7)
			this.rooms[i] = 1;
		for(int i = 21; i < 28; i++)
			this.rooms[i] = 1;
		
		switch(player.getProfession())
		{
		case Rogue:
			for(int i = 0; i < 17; i++)
			{
				rooms[i] = 1;
				if(i == 2 || i == 9)
					i += 4;
			}
			break;
		case Mage:
			for(int i = 4; i < 21; i++)
			{
				rooms[i] = 1;
				if(i == 6 || i == 13)
					i += 4;
			}
			break;
		case Barbarian:
			for(int i = 28; i < 45; i++)
			{
				rooms[i] = 1;
				if(i == 30 || i == 37)
					i += 4;
			}
			break;
		case Monk:
			for(int i = 32; i < 49; i++)
			{
				rooms[i] = 1;
				if(i == 34 || i == 41)
					i += 4;
			}
			break;
			
		}
	}
}
