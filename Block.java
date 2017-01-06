import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Blocks for stack
 * @author Tolaz Hewa
 */
public class Block 
{
	private Rectangle box;
	public static final int SIDE_LENGTH = 30;
	private char blockLetter;
	/**
	 * constructor of the block
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param letter letter to be written
	 */
	public Block(int x, int y, char letter)
	{
		box = new Rectangle(x, y, SIDE_LENGTH, SIDE_LENGTH);
		blockLetter = letter;
	}
	/**
	 * sets location
	 * @param x wanted x-coordinate
	 * @param y wanted y-coordinate
	 */
	public void setLocation(int x, int y)
	{
		box.setLocation(x, y);
	}
	
	/**
	 * gets x-coordinate
	 * @return x-coordinate
	 */
	public int getX()
	{
		return this.box.x;
	}
	
	/**
	 * gets y-coordinate
	 * @return y-coordinate
	 */
	public int getY()
	{
		return this.box.y;
	}
	
	/**
	 * draws the block
	 * @param g2 Graphics Variable
	 */
	public void draw(Graphics2D g2)
	{
		g2.setColor(new Color(50,205,50));
		g2.setStroke(new BasicStroke(3));
		g2.draw(box);
		g2.drawString("" + blockLetter, (int)box.getCenterX() - 4, (int)box.getCenterY()+4); 
		g2.setStroke(new BasicStroke(1));
	}
	
}
