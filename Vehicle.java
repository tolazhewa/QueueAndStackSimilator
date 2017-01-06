import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * abstract Vehicle class to do what is required for all vehicles
 * @author Tolaz Hewa
 */

public abstract class Vehicle 
{	
	public boolean isActive;
	public boolean isTrailer;
	public Rectangle box;
	public boolean hasTrailer;
	public Vehicle trailer;
	public Color color = Color.BLACK;
	public int number;
	public boolean isEngine = false;
	public Block block;
	public boolean hasBlock;
	
	/**
	 * 
	 * @param g2 graphics 
	 */
	public abstract void draw(Graphics2D g2);
	
	/**
	 * return x
	 * @return x
	 */
	public int getX()
	{
		return box.x;
	}
	
	/**
	 * returns y
	 * @return y
	 */
	public int getY()
	{
		return box.y;
	}
	
	/**
	 * returns number
	 * @return number
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * return box
	 * @return box
	 */
	public Rectangle getBox()
	{
		return box;
	}
	/**
	 * sets the color
	 * @param c desired color
	 */
	public void setColor(Color c)
	{
		color = c;
	}
	/**
	 * sets active recursively
	 * @param b is active or not
	 */
    public void isActive(boolean b)
    {
    	this.isActive = b;
    	if(this.trailer == null)
    	{
    		return;
    	}
    	this.trailer.isActive(b);
    }
    /**
     * sets location
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setLocation(int x, int y)
    {
    	this.box.setLocation(x, y);
    	if(this.trailer != null)
    	{
    		this.trailer.setLocation(box.x + this.box.width, box.y);
    	}
    }
    /**
     * adds first
     * @param add the item to add as first
     */
    public void addFirst(Vehicle add)
    {
    	Vehicle last = add.findLast();
    	if(this.hasTrailer)
    	{
    		add.isTrailer = true;
    		last.hasTrailer = true;
    		last.trailer = this.trailer;
    		this.trailer = add;
    		this.setLocation(this.getX(), this.getY());
    	}
    	else
    	{
    		this.trailer = add;
    		this.hasTrailer = true;
    		add.isTrailer = true;
    		this.setLocation(this.getX(), this.getY());
    	}
    }
    /**
     * removes the first item in linked list
     */
    public void removeFirst()
    {
    	Vehicle remove = this;
    	if(remove.isEngine && remove.hasTrailer)
    	{
    		Vehicle removeE = remove.trailer;
    		if(!removeE.hasTrailer)
    		{
    			remove.hasTrailer = false;
    		}
    		removeE.hasTrailer = false;
    		removeE.isTrailer = false;
    		remove.trailer = removeE.trailer;
    		removeE.trailer = null;
    		removeE.setLocation(removeE.getX() + 25, removeE.getY()+50);
    		remove.setLocation(remove.getX(), remove.getY());
    	}
    	else if(remove.hasTrailer)
    	{
    		remove.hasTrailer = false;
    		remove.trailer.isTrailer = false;
    		remove.trailer = null;
    		remove.setLocation(remove.getX() + 25, remove.getY()+50);
    	}   
    }
    /**
     * adds last
     * @param add item to end of it
     */
    public void addLast(Vehicle add)
    {
    	Vehicle last = this.findLast();
    	last.trailer = add;
    	last.hasTrailer = true;
    	add.isTrailer = true;
    	add.setLocation(last.getX()+last.getBox().width , last.getY());
    }
    /**
     * removes last item in list
     */
    public void removeLast()
    {
    	Vehicle remove = this.findLast2();
    	remove.hasTrailer = false;
    	remove.trailer.isTrailer = false;
    	remove.trailer.setLocation(remove.getX() + 25, remove.getY()+50);
    	remove.trailer = null;
    }
    /** 
     * finds the last item that has trailer
     * @return
     */
    public Vehicle findLast()
    {
    	Vehicle given = this;
    	while(given.hasTrailer)
    	{
    		given = given.trailer;
    	}
    	return given;
    }
    /**
     * finds the last item that has block
     * @return
     */
    public Vehicle findLastBlock()
    {
    	Vehicle result = this;
    	while(result.hasBlock)
    	{
    		if(result.hasTrailer)
    		{
    			result = result.trailer;
    		}
    		else
    		{
    			return null;
    		}
    	}
    	return result;
    }
    
    /**
     * finds first block
     * @return
     */
    public Vehicle findNextBlock()
    {
    	Vehicle result = this;
    	while(!result.hasBlock)
    	{
    		if(result.hasTrailer)
    		{
    			result = result.trailer;
    		}
    		else
    		{
    			return null;
    		}
    	}
    	return result;
    }
    /**
     * finds the second last item that has trailer
     * @return
     */
    public Vehicle findLast2()
    {
    	Vehicle given = this;
    	while(given.hasTrailer)
    	{
    		if(!given.trailer.hasTrailer)
    		{
    			break;
    		}
    		given = given.trailer;
    	}
    	return given;
    }
    
    /**
     * checks to see if it overlaps with other
     * @param other other vehicle
     * @return intersects (true) or not (false)
     */
	public boolean overlaps(Vehicle other) 
	{
		if(other.getBox().intersects(this.box))
		{
			return true;
		}
		return false;
	}
}
