/**
 * 
 */
package javaGame;

/**
 * @author Joseph Harrison
 *
 */
public abstract class SoundClip extends Thread
{
	protected long delay; 	//the amount of delay before an object is played (in seconds)
	protected long fadeIn;  	//the amount in seconds before clip reaches full volume
	protected long fadeOut; 	//the amount in seconds at end of clip before it reaches 0 volume
	protected double pitch; 	//the pitch in percent
	protected double volume;		//the volume level associated with this sound (not the global volume) 0% to 100%
	protected String filename;
	
	
	/**
	 * @return the filename
	 */
	public String getFilename() 
	{
		return filename;
	}

	public double getVolume() {return this.volume;}
	
	/*
	 * plays the sound clip
	 */
	public abstract void play();
	
	/*
	 * stops the clip from playing
	 */
	public abstract void stopPlay();
	
	/*
	 * plays the clip in a loop
	 */
	public abstract void loop();
	
	/*
	 * sets the delay
	 */
	public abstract void setDelay(double passedIn);
	
	/*
	 * sets the fadeIn
	 */
	public abstract void setFadeIn(double passedIn);
	
	/*
	 * sets the fadeOut
	 */
	public abstract void setFadeOut();
	
	/*
	 * sets the pitch
	 */
	public abstract void setPitch();
	
	/*
	 * sets the volume specific to this sound file
	 */
	public abstract void setVolume(double newVolume);
	
	public abstract void close();
	
}//end class
