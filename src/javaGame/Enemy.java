package javaGame;

public abstract class Enemy extends GameCharacter implements Cloneable
{
	protected final int RANGE = 5;
	protected final int FAILEDUP = 0;
	protected final int FAILEDDOWN = 1;
	protected final int FAILEDLEFT = 2;
	protected final int FAILEDRIGHT = 3;
	protected int failedMove = -1;
	protected boolean boss;
	
	protected Enemy()
	{
		super();
	}
	
	public void move(GameCharacter player)
	{
		if(this.getXDiff(player) < RANGE && this.getYDiff(player) < RANGE)
			this.smartMove(player);
		else
			this.randomMove();
		
		
	}
	
	private void smartMove(GameCharacter player)
	{
		if(Math.abs(this.getX() + 1 - player.getX()) < getXDiff(player))
		{
			this.setPrevs();
			this.setX(this.getX() + 1);
			this.failedMove = this.FAILEDRIGHT;
		}
		else if(Math.abs(this.getX() - 1 - player.getX()) < getXDiff(player))
		{
			this.setPrevs();
			this.setX(this.getX() - 1);
			this.failedMove = this.FAILEDLEFT;
		}
		else if(Math.abs(this.getY() - 1 - player.getY()) < getYDiff(player))
		{
			this.setPrevs();
			this.setY(this.getY() - 1);
			this.failedMove = this.FAILEDUP;
		}
		else if(Math.abs(this.getY() + 1 - player.getY()) < getYDiff(player))
		{
			this.setPrevs();
			this.setY(this.getY() + 1);
			this.failedMove = this.FAILEDDOWN;
		}
		
	}
	
	public void smartMoveIfCollision(GameCharacter player)
	{
		switch(this.failedMove)
		{
		case FAILEDDOWN:
			this.setX(this.getX() - 1);
			this.failedMove = FAILEDLEFT;
			break;
		case FAILEDUP:	
			this.setX(this.getX() + 1);
			this.failedMove = FAILEDRIGHT;
			break;
		case FAILEDLEFT:
			this.setY(this.getY() - 1);
			this.failedMove = FAILEDUP;
			break;
		case FAILEDRIGHT:
			this.setY(this.getY() + 1);
			this.failedMove = FAILEDDOWN;
			break;
		}
	}
	
	private void randomMove()
	{
		double chance = rand.nextDouble();
		if(chance < .25 && this.getX() > 0)
		{
			this.setPrevs();
			this.setX(this.getX() - 1);
		}
		else if(chance >= .25 && chance < .5 && this.getY() > 0)
		{
			this.setPrevs();
			this.setY(this.getY() - 1);
		}
		else if(chance >= .5 && chance < .75 && this.getX() < 14)
		{
			this.setPrevs();
			this.setX(this.getX() + 1);
		}
		else if(chance >= .75 && this.getY() < 14)
		{
			this.setPrevs();
			this.setY(this.getY() + 1);
		}
	}
	
	private int getXDiff(GameCharacter player)
	{
		return Math.abs(this.getX() - player.getX());
	}
	
	private int getYDiff(GameCharacter player)
	{
		return Math.abs(this.getY() - player.getY());
	}
	
	private void setPrevs()
	{
		this.setPreviousX(this.getX());
		this.setPreviousY(this.getY());
	}

	public String getName() 
	{
		return getCharacterName();
	}

	public void setName(String name) 
	{
		setCharacterName(name);
	}
	public void setImage(String fileName)
	{
		this.getProfession().setSelfie(fileName);
	}
	
	public boolean isBoss()
	{
		return this.boss;
	}

}
