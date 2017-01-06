import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;
/**
 * Panel to do all your algorithms/drawings/etc
 * @author Tolaz Hewa
 */

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	public Stack<Block> blocks = new Stack<Block>();
	public Rectangle stackBase;
	public ArrayList<Vehicle> myLink = new ArrayList<Vehicle>();
	private int count1 = 1;
	private int count2 = 0;
	private boolean ol = false;
	public Vehicle aV = null;
	
	/**
	 * Constructor for the panel
	 */
	public GamePanel()
	{
		myMouseActions();
	}
	
	/**
	 * does the mouse functions
	 */
	public void myMouseActions()
	{
		this.addMouseMotionListener(new MouseAdapter()
		{
			public void mouseDragged(MouseEvent e) 
			{
				if(count1 > 7)
				{
					for(Vehicle a: myLink)
					{
						if(a.isActive && !a.isTrailer)
						{
							a.setLocation(e.getX() - a.getBox().width/2 , e.getY() - a.getBox().height/2);
						}
					}
					repaint();
				}
			}
		});
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) 
			{	
					if(count1 <= 1)
					{
						TrainEngine train = new TrainEngine(e.getX() - (int)TrainEngine.LENGTH/2 , e.getY() - (int)TrainEngine.HEIGHT/2);
						myLink.add(train);
						count1++;
					}
					else if(count1 > 1 && count1 < 7)
					{
						RailCar railCar = new RailCar(e.getX() - RailCar.LENGTH/2, e.getY() - RailCar.HEIGHT/2, count1 - 1);
						for(Vehicle a: myLink)
						{
							if(a.overlaps(railCar))
							{
								ol = true;
								break;
							}
						}
						if(!ol)
						{
							myLink.add(railCar);
							count1++;
						}
						else
						{
							ol = false;
						}
					}
					else if(count1 == 7)
					{
						boolean cont = true;
						for(Vehicle a: myLink)
						{
							if(a.getBox().intersects(e.getX() - 25, e.getY()-185, 50, 195))
							{
								cont = false;
							}
						}
						if(cont)
						{
							stackBase = new Rectangle(e.getX() - 25, e.getY() - 10, 50, 20);
							char a = 'A';
							for(int i = 0; i < 5; i++)
							{
								Block newBlock = new Block(e.getX()- 15, e.getY() - ((i+1) * 35) - 15, (char)(a + i));
								blocks.push(newBlock);
							}
						count1++;
						}
						
					}
					else 
					{
						for(Vehicle a: myLink)
						{
							if(a.getBox().contains(getMousePosition()))
							{
								if(!a.isTrailer){
									a.isActive(true);
									aV = a;
								}
							}
							else 
							{
								if(!a.isTrailer)
								{
									count2++;
									a.isActive(false);
									if(count2 >= 6)
									{
										aV = null;
									}
								}
							}
						}
						count2 = 0;
					}
					repaint();
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(count1 > 7 && aV != null && !aV.isEngine)
				{
					for(Vehicle item: myLink)
					{
						if(item != aV && item.overlaps(aV) && item.trailer == null)
						{
							item.trailer = aV;
							item.hasTrailer = true;
							aV.isTrailer = true;
							aV.setLocation(item.getX()+item.getBox().width , item.getY());
							aV.isActive(false);
						}
						else if(item != aV && item.overlaps(aV))
						{
							for(int i = 0; i < myLink.size(); i++)
							{
								if(myLink.get(i).overlaps(aV) && myLink.get(i) != aV)
								{
									aV.setLocation(e.getX(), e.getY() + 50+(i*10));
									i = 0;
								}
							}
						}
					}
					repaint();
				}
			}
		});
	}
	/**
	 * resets the game
	 */
	public void restart()
	{
		blocks = new Stack<Block>();
		stackBase = null;
		myLink = new ArrayList<Vehicle>();
		count1 = 1;
		count2 = 0;
		ol = false;
		aV = null;
		repaint();
	}
	
	/**
	 * paints the imagines on to the panel
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for(Vehicle a: myLink)
		{
			a.draw(g2);
		}
		if(stackBase != null)
		{
			g2.setColor(Color.BLACK);
			g2.draw(stackBase);
			g2.fill(stackBase);
		}
		for(Block b: blocks)
		{
			b.draw(g2);
		}
	}
}
