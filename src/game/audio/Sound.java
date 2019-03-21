/**
 * 
 */
package game.audio;

import game.base.E_Profession;

/**
 * @author Joe
 *
 */
public class Sound 
{
	private SoundClip music;
	private SoundClip weapon;
	private double musicVolume; //music's musicVolume at this point
	
	public Sound()
	{
		musicVolume = 90; // meaning 100% is the default musicVolume
		try 
		{
			this.weapon = new WavClip("res/music/fist1.wav");
			this.music = new WavClip("res/music/fist1.wav");
						
		} 
		catch (Exception e) 
		{
			this.music = new MockClip();
			this.weapon = new MockClip();
			e.printStackTrace();
		}
		

		
	}
	
	public void playAppropriateMusic(int roomNumber)
	{
		try {
			if( ( (roomNumber > 20 && roomNumber < 28) || (roomNumber % 7 == 3) ) && !music.getFilename().equals("res/music/songFactionless.wav"))
			{
				music.stopPlay();
				music.close();
				this.music = new WavClip("res/music/songFactionless.wav");
				this.music.loop();
			}
			else if( ( roomNumber == 16 || roomNumber == 1 )&& !music.getFilename().equals("res/music/songRogue.wav") )
			{
				this.music.close();
				this.music = new WavClip("res/music/songRogue.wav");//MUSIC
				this.music.loop();
			}
			else if( ( roomNumber == 18 || roomNumber == 5 )&& !music.getFilename().equals("res/music/songMage.wav") )
			{
				this.music.close();
				this.music = new WavClip("res/music/songMage.wav");//MUSIC
				this.music.loop();
			}
			else if( (roomNumber == 30 || roomNumber == 37 || roomNumber == 43) && !music.getFilename().equals("res/music/songBarbarian.wav") )
			{
				this.music.close();
				this.music = new WavClip("res/music/songBarbarian.wav");//MUSIC
				this.music.loop();
			}
			else if( (roomNumber == 32 || roomNumber == 47) && !music.getFilename().equals("res/music/songMonk.wav") )
			{
				this.music.close();
				this.music = new WavClip("res/music/songMonk.wav");//MUSIC
				this.music.loop();
			}
			else if( roomNumber == 2 || roomNumber == 6 || roomNumber == 44 || roomNumber == 48)
			{
				this.music.close();
				this.music = new WavClip("res/music/boss.wav");//MUSIC
				this.music.loop();
			}
		} 
		catch (Exception e) 
		{
			this.music = new MockClip("music.MockClip");
			System.err.println("Sound File Error.");
			e.printStackTrace();
		}
		finally
		{
			this.music.setVolume(musicVolume);
		}
		
	}//end method
	
	public void playIntroMusic()
	{
		try
		{
			music.stopPlay();
			music.close();
			this.music = new WavClip("res/music/songFactionless.wav");
			this.music.setVolume(musicVolume);
			this.music.loop();
		}
		catch (Exception e) 
		{
			this.music = new MockClip("music.MockClip");
			System.err.println("Sound File Error.");
			e.printStackTrace();
		}
	}
	
	/*
	 * The following four methods just play different sounds, and are fixed files for now.
	 * I.E. if the file doesn't exist, the sound doesn't play
	 */
	public void playFistAttack()
	{
		try
		{
			music.stopPlay();
			music.close();
			this.music = new WavClip("res/music/songFactionless.wav");
			this.music.setVolume(musicVolume);
			this.music.loop();
		}
		catch (Exception e) 
		{
			this.music = new MockClip("music.MockClip");
			System.err.println("Sound File Error.");
			e.printStackTrace();

		} 
		finally
		{
			music.setVolume(musicVolume);

		}
	}
	
	public void playAttackSound(String filename)
	{
		try 
		{
			if(this.weapon.getFilename().equals(filename))
				this.weapon.play();
			else
			{
				this.weapon.stopPlay();
				this.weapon.close();
				this.weapon = new WavClip(filename);
				this.weapon.play();
			}
		} 
		catch (Exception e) 
		{
			this.weapon = new MockClip(filename);
			//System.out.println("Weapon Sound Error.");
			e.printStackTrace();
		} 
	}

	public void playAttackSound(E_Profession profession)
	{
		music.setVolume(musicVolume);
		switch(profession){
		case Rogue:
			playAttackSound("res/music/bow1.wav");
			break;
		case Monk:
			playAttackSound("res/music/fist1.wav");
			break;
		case Barbarian:
			playAttackSound("res/music/sword1.wav");
			break;
		case Mage:
			playAttackSound("res/music/spell1.wav");
			break;
		default:
			break;
		}
	}//end method
	
	public void playSpecialAttackSound(E_Profession profession)
	{
		switch(profession)
		{
		case Rogue:
			playAttackSound("res/music/bow2.wav");
			break;
		case Monk:
			playAttackSound("res/music/heal1.wav");
			break;
		case Barbarian:
			playAttackSound("res/music/sword2.wav");
			break;
		case Mage:
			playAttackSound("res/music/spell2.wav");
			break;
		default:
			break;
		}
	}//end method
	
}//end class
