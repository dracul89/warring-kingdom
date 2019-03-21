/**
 * 
 */
package game.audio;

import game.audio.SoundClip;

/**
 * @author Joe
 *
 */
public class MockClip extends SoundClip
{
	public MockClip()
	{
		this.filename = "NOFILESELECTED";		
	}
	
	public MockClip(String filename)
	{
		this.filename = filename;
		System.out.println("A MockClip object was created with filename: " + filename);
	}
	
	@Override
	public void play() 
	{
		System.out.println("A sound plays.");
	}

	@Override
	public void stopPlay() 
	{
		System.out.println("A sound stops playing.");
	}

	@Override
	public void loop() 
	{
		System.out.println("A sound plays in a loop.");
	}

	@Override
	public void setDelay(double passedIn) 
	{
		System.out.println("A delay is set.");
	}

	@Override
	public void setFadeIn(double passedIn) 
	{
		System.out.println("A fade in time is set.");
		
	}

	@Override
	public void setFadeOut() 
	{
		System.out.println("A fade out time is set.");
		
	}

	@Override
	public void setPitch() 
	{
		System.out.println("A pitch is set.");
	}

	@Override
	public void setVolume(double newVolume) 
	{
		System.out.println("The volume was changed to: " + newVolume + "%");
		
	}

	@Override
	public void close() 
	{
		System.out.println("A sound File was closed.");
		
	}

}
