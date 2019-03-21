package game.buttons;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public abstract class Button {
	
	protected final float buttoWidthOffset = 2.7f;
	protected final float buttonHeightOffset = 3.7f;
	protected final float HALVIES = 2.0f;
	private int offset;
	protected Vector2f vector;
	protected GameContainer gamecontainer;
	protected Button(GameContainer gamecontainer)
	{
		this.gamecontainer = gamecontainer;
		this.vector = new Vector2f();
	}
	public void setOffset(int number)
	{
		this.offset = number;
	}
	public int getOffset()
	{
		return this.offset;
	}

	public void setLocation(double percentY,double percentX){
		vector.set((float)(gamecontainer.getWidth()*percentX),(float) (gamecontainer.getHeight()*percentY));
	}
	public void setLocation(float YLocation,float XLocation){
		vector.set(XLocation, YLocation);
	}
	
	public abstract void drawButton(String name,Color color,Graphics graphics);
	public abstract boolean isClicked();
}
