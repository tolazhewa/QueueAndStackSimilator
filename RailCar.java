import java.awt.* ;
import java.awt.geom.* ;
 /**
  * RailCar class represent a railcar
  * @author Tolaz Hewa
  *
  */

public class RailCar extends Vehicle
{
    public static final int UNIT = 10 ;
    public static final int U6 = 6 * UNIT ;
    public static final int U5 = 5 * UNIT ;
    public static final int U4 = 4 * UNIT ;
    public static final int U3 = 3 * UNIT ;
    public static final int U2 = 2 * UNIT ;
    public static final int U15 = UNIT + UNIT / 2 ;
    public static final int U05 =  UNIT / 2 ;
    public static final int BODY_WIDTH = U3 ;
    public static final int BODY_HEIGHT = U2 ;
    public static final int LENGTH  = U6 + U05;
    public static final int HEIGHT = U3;
    private Color color = Color.BLACK;
    
    /**
     * constructor that sets the x and y coordinates and sets number
     * @param x x-coordinate
     * @param y y-coordinate
     * @param number the number 
     */
    public RailCar(int x, int y, int number)
    {
    	box = new Rectangle(x, y, LENGTH, HEIGHT);
    	this.number = number;
    }
    
    /**
       Draw the rail car
       @param g2 the graphics context
     */
    public void draw(Graphics2D g2)
    {
    if(isActive)
    {
    	this.color = Color.RED;
    }
    else
    {
    	this.color = Color.BLACK;
    }
	// think about whether getX() and getY() should be inherited
     // or defined in this class
	int xLeft = getX() ;
	int yTop = getY() ;
	
	Rectangle2D.Double body 
	    = new Rectangle2D.Double(xLeft, yTop + UNIT, U6, UNIT);      
	Ellipse2D.Double frontTire 
	    = new Ellipse2D.Double(xLeft + UNIT, yTop + U2, UNIT, UNIT);
	Ellipse2D.Double rearTire
	    = new Ellipse2D.Double(xLeft + U4, yTop + U2, UNIT, UNIT);
	
	// the bottom of the front windshield
	//Point2D.Double r1  = new Point2D.Double(xLeft + UNIT, yTop + UNIT);
	// the front of the roof
	//Point2D.Double r2 = new Point2D.Double(xLeft + U2, yTop);
	// the rear of the roof
	//Point2D.Double r3 = new Point2D.Double(xLeft + U4, yTop);
	// the bottom of the rear windshield
	//Point2D.Double r4 = new Point2D.Double(xLeft + U5, yTop + UNIT);
	
	// the right end of the hitch
	Point2D.Double r5 = new Point2D.Double(xLeft + U6, yTop + U15);
	// the left end of the hitch
	Point2D.Double r6 = new Point2D.Double(xLeft + U6 + U05, yTop + U15);
	
	//Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
	//Line2D.Double roofTop = new Line2D.Double(r2, r3);
	//Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
	Line2D.Double hitch = new Line2D.Double(r5, r6);
	
	g2.setColor(color);
	g2.draw(body);
	//g2.draw(frontWindshield);
	//g2.draw(roofTop);
	//g2.draw(rearWindshield);
	g2.draw(hitch);
	g2.draw(frontTire);
	g2.draw(rearTire);
	g2.draw(body) ;
	// think about whether getNumber() should be inherited or
	// defined in this class
	g2.drawString("" + getNumber(), getX() + U2, yTop + U2) ;
	if(hasBlock)
	{
		block.setLocation(this.getX() + 15, this.getY() - 23);
		block.draw(g2);
	}
    }
}
    
    
    
    