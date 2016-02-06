package javaGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class ImageButton extends Button
{

	private Image buttonImage;
	private float imageHeight;
	private float imageWidth;
	
	
	public ImageButton(GameContainer gamecontainer)
	{
		super(gamecontainer);
		this.vector = new Vector2f();
	}
	public void setButtonImage(Image image)
	{
		this.buttonImage = image;
		imageHeight = image.getHeight();
		imageWidth = image.getWidth();
	}
	public Image getButtonImage()
	{
		return buttonImage;
	}
	
	public void drawButton(String name,Color color,Graphics graphics){
		graphics.drawImage(this.buttonImage, (vector.getX()-(imageWidth/HALVIES)), (vector.getY() -(imageHeight/HALVIES)));
		graphics.setColor(color);
		graphics.drawString(name,(float) (vector.getX()-(imageWidth/buttoWidthOffset)), (float)(vector.getY()-(imageHeight/buttonHeightOffset)));
	}
	@Override 
	public boolean isClicked(){

		Input mouse = this.gamecontainer.getInput();
		float xPosition = mouse.getMouseX();
		float yPosition = mouse.getMouseY();
		if(xPosition > (this.vector.getX()-this.imageWidth) && (xPosition < this.vector.getX()+this.imageWidth) && 
				(yPosition > this.vector.getY()-this.imageHeight) && (yPosition < this.vector.getY()+this.imageHeight))
		{
			if(mouse.isMousePressed(0))
			{
				return true;
			}
			
		}
		return false;
	}
	
}
