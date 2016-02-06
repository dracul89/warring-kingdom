package javaGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class ShapeButton extends Button {
	
	
	private float width;
	private float height;
	private int cornerRadius;
	private Color color;

	public ShapeButton(GameContainer gamecontainer) {
		super(gamecontainer);
		this.vector = new Vector2f();
	}
	public void drawOutline(float width,Color color,Graphics graphics){
		graphics.setColor(color);
		graphics.setLineWidth(width);
		graphics.drawRoundRect((float) (this.vector.getX()-(this.width/HALVIES)), (float) (this.vector.getY()-(this.height/HALVIES)), this.width, this.height, this.cornerRadius);
	}
	@Override
	public void drawButton(String name, Color color,Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillRoundRect((float) (this.vector.getX()-(this.width/HALVIES)), (float) (this.vector.getY()-(this.height/HALVIES)), this.width, this.height, this.cornerRadius);
		graphics.setColor(color);
		
		graphics.drawString(name,(float) (vector.getX()-(this.width/buttoWidthOffset)), (float)(vector.getY()-(this.height/buttonHeightOffset)));
	}
	public void setRoundedButton(float width,float height,int cornerRadius){
		this.width = width;
		this.height = height;
		this.cornerRadius = cornerRadius;
	}
	public void setButton(float width,float height){
		this.height = height;
		this.width = width;
		this.cornerRadius = 0;// see what I did here? don't need another method in drawbutton :) 
	}
	@Override
	public boolean isClicked() {
		Input mouse = this.gamecontainer.getInput();
		float xPosition = mouse.getMouseX();
		float yPosition = mouse.getMouseY();
		if(xPosition > (this.vector.getX()-this.width) && (xPosition < this.vector.getX()+this.width) && 
				(yPosition > this.vector.getY()-this.height) && (yPosition < this.vector.getY()+this.height))
		{
			if(mouse.isMousePressed(0))
			{
				return true;
			}
		}
		return false;
	}
	public void setFillColor(Color color){
		this.color = color;
	}
	
}
