/**
 * 
 */
package game.audio;
import game.audio.SoundClip;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * @author Joseph Harrison
 * 
 *
 */
public class WavClip extends SoundClip
{
	private Clip clip;
	FloatControl fc;
	
	@SuppressWarnings("unused")
	private WavClip(){}//do not allow default instance
	
	/*
	 * Initializes the WavFile with the string passed in, and with default values
	 */
	public WavClip(String passedIn)
	{
		this.start();
		this.filename = passedIn;
		this.volume = 0;
		this.delay = 0;
		this.fadeIn = 0;
		this.fadeOut = 0;
		this.pitch = 0;
		
		//load the clip from an audio stream
		getClip();

	}//end EVC

	@Override
	public void play() 
	{
		clip.stop();
		clip.setFramePosition(0);  // make sure clip is at the beginning (so that it resets every time it is played)
		clip.start();
	}//end play
    
	@Override
	public void loop()
	{
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
	
	@Override
	public void stopPlay() //stops and closes the clip object.
	{
		clip.stop();
	}
	

	@Override
	public void setDelay(double passedIn) //in seconds. I.E. 1.2 == 1200 Milliseconds
	{
		passedIn = passedIn*1000; //convert Milliseconds to seconds
		
		if(passedIn < 0)
			this.delay = 0;
		else if (passedIn > 60000) //60 seconds max
			this.delay = 60000;
		else
			this.delay = (long)passedIn;
		
	}//end method

	@Override
	public void setPitch()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setVolume(double newVolume) 
	{
		//make sure newVolume is within bounds
		if(newVolume > 100)
			newVolume = 100;
		if(newVolume < 0)
			newVolume = 0;
		newVolume = newVolume/100;
		
		float max = fc.getMaximum();
		float min = fc.getMinimum();
		
		//get the total distance between max and min volume
		float total = 0;
		if(max >= 0 && min <= 0)
			total = Math.abs(min) + max;
		else if(max < 0 && min < 0)
			total = Math.abs(min - max);
		else if(min >= 0 && max >= 0)
			total = max - min;
		
		//multiply the total distance by the percent passed in.
		float temp;
		temp = min + (float)( (double)total*newVolume );
		this.volume = (double)temp;
		fc.setValue(temp); //volumes tend to be inaudible below 50% (so far in testing)
		
		//System.out.println(fc.getValue());
	}//end setVolume

	@Override
	public void setFadeIn(double passedIn) 
	{
		setVolume(0); //make sure the volume starts at 0
		
		long newValue = (long)(passedIn*1000); //set to miliseconds
		long max = clip.getMicrosecondLength();
		
		if(newValue <= 0)
			this.fadeIn = 0;
		else if(newValue > max)
			this.fadeIn = max;
		else
			this.fadeIn = newValue;	
	}//end setFadeIn

	@Override
	public void setFadeOut() {
		// TODO Auto-generated method stub
		
	}
	
	public void close()
	{
		clip.close();
	}

	
	/*
	 * Get a clip object from an audiostream based on the filename
	 */
	private void getClip()
	{
		try 
		{
	        File fin = new File(this.filename);
	        if (fin.exists())
	        {
	            AudioInputStream stream = AudioSystem.getAudioInputStream(fin);
	            clip = AudioSystem.getClip();
	            clip.open(stream);
	            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	            this.volume = fc.getMaximum();
	        }
			else
			{
			    throw new RuntimeException("Sound: file not found: " + fin);
			}
	     }
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		}
		catch (UnsupportedAudioFileException e)
		{
		    e.printStackTrace();
		    throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		    throw new RuntimeException("Sound: Input/Output Error: " + e);
		}
		catch (LineUnavailableException e)
		{
		    e.printStackTrace();
		    throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
		}
		
	}//end method
	
	@Override
	public void finalize()
	{
		clip.close();
	}

}//end class
