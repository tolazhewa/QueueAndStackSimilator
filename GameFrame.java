import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * Frame class to outline the menu and the interface
 * @author Tolaz Hewa
 */
public class GameFrame extends JFrame 
{
	private static final int FRAME_WIDTH = 750;
	private static final int FRAME_HEIGHT = 750;
	private GamePanel myPanel;
	private JMenuBar menuBar;
	
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the frame which adds menu bar and panel
	 */
	public GameFrame()
	{
		myPanel = new GamePanel();
		this.add(myPanel);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		menuBar = new JMenuBar();   
		
		setJMenuBar(menuBar);
		
		menuBar.setForeground(Color.DARK_GRAY);
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.add(createFileMenu());
		menuBar.add(createStackMenu());
		menuBar.add(createListMenu());
	}
	
	/**
	 * creates file menu with its options 
	 * @return file menu
	 */
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		menu.setBackground(Color.DARK_GRAY);
		menu.add(createNew("New"));
		menu.add(createExit("Exit"));
		return menu;
	}  
	
	/**
	 * creates stack menu with its options 
	 * @return stack menu
	 */
	public JMenu createStackMenu()
	{
		JMenu menu = new JMenu("Stack");
		menu.setBackground(Color.DARK_GRAY);
		menu.add(createPop("Pop"));
		menu.add(createPush("Push"));
		return menu;
	}  
	
	/**
	 * creates list menu with its options 
	 * @return list menu
	 */
	public JMenu createListMenu()
	{
		JMenu menu = new JMenu("List");
		menu.setBackground(Color.DARK_GRAY);
		menu.add(createAddFirst("Add First"));
		menu.add(createAddLast("Add Last"));
		menu.add(createRemoveFirst("Remove First"));
		menu.add(createRemoveLast("Remove Last"));
		return menu;
	}  
	
	/**
	 * creates exit menu item
	 * @param name name of menu item
	 * @return exit menu item
	 */
	public JMenuItem createExit(String name)
	{
	   class exitListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   System.exit(0);
	       }
	   }      
	   
	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new exitListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates new menu item
	 * @param name name of menu item
	 * @return new menu item
	 */
	public JMenuItem createNew(String name)
	{
	   class newListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   myPanel.restart();
	       }
	   }      
	   
	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new newListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates AddLast menu item
	 * @param name name of menu item
	 * @return AddLast menu item
	 */
	public JMenuItem createAddLast(String name)
	{
	   class AddLastListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   if(myPanel.aV != null && myPanel.aV.isActive && !myPanel.aV.isEngine)
	    	   {
	    		   myPanel.myLink.get(0).addLast(myPanel.aV);
	    		   myPanel.aV.isActive(false);
	    		   myPanel.aV = null;
	    		   myPanel.repaint();
	    	   }
	       }
	   }      

	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new AddLastListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates AddFirst menu item
	 * @param name name of menu item
	 * @return AddFirst menu item
	 */
	public JMenuItem createAddFirst(String name)
	{
	   class AddFirstListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   if(myPanel.aV != null && myPanel.aV.isActive && !myPanel.aV.isEngine)
	    	   {
	    		   myPanel.aV.isActive(false);
	    		   myPanel.myLink.get(0).addFirst(myPanel.aV);
	    		   myPanel.aV = null;
	    		   repaint();
	    	   }
	       }
	   }      
	   
	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new AddFirstListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates RemoveFirst menu item
	 * @param name name of menu item
	 * @return new RemoveFirst item
	 */
	public JMenuItem createRemoveFirst(String name)
	{
	   class RemoveFirstListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   if(myPanel.aV != null && myPanel.aV.isActive)
	    	   {
	    		   if(myPanel.aV.isEngine && !myPanel.aV.hasTrailer)
	    		   {
	    			   return;
	    		   }
	    		   myPanel.aV.isActive(false);
	    		   myPanel.aV.removeFirst();
	    		   myPanel.aV = null;
	    		   myPanel.repaint();
	    	   }
	       }
	   }   
	   
	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new RemoveFirstListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates RemoveLast menu item
	 * @param name name of menu item
	 * @return RemoveLast menu item
	 */
	public JMenuItem createRemoveLast(String name)
	{
	   class RemoveLastListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   if(myPanel.aV != null && myPanel.aV.isActive && myPanel.aV.hasTrailer)
	    	   {
	    		   myPanel.aV.isActive(false);
	    		   myPanel.aV.removeLast();
	    		   myPanel.aV = null;
	    		   myPanel.repaint();
	    	   }
	       }
	   }      

	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new RemoveLastListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates Push menu item
	 * @param name name of menu item
	 * @return Push menu item
	 */
	public JMenuItem createPush(String name)
	{
	   class pushListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   if(myPanel.aV != null && myPanel.aV.isActive)
	    	   {
	    		   Vehicle result;
	    		   if(myPanel.aV.isEngine && myPanel.aV.hasTrailer)
	    		   {
	    			   result = myPanel.aV.trailer.findNextBlock();
	    		   }
	    		   else
	    		   {
	    			   result = myPanel.aV.findNextBlock();
	    		   }
	    		   if(result == null)
	    		   {
	    			   return;
	    		   }
	    		   if(myPanel.blocks.size() > 0)
	    		   {
	    		   result.block.setLocation(myPanel.blocks.get(myPanel.blocks.size() - 1).getX(), 
	    				   			  myPanel.blocks.get(myPanel.blocks.size() - 1).getY() - 35);
	    		   }
	    		   else
	    		   {
	    			   result.block.setLocation(myPanel.stackBase.x + 10, myPanel.stackBase.y - 35);
	    		   }
	    		   
	    		   myPanel.blocks.push(result.block);
	    		   
	    		   result.hasBlock = false;
	    		   result.block = null;
	    		   myPanel.aV.isActive(false);
	    		   myPanel.aV = null;
	    		   myPanel.repaint();
	    	   }
	       }
	   }   
	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new pushListener();
	   item.addActionListener(listener);
	   return item;
	}
	
	/**
	 * creates Pop menu item
	 * @param name name of menu item
	 * @return Pop menu item
	 */
	public JMenuItem createPop(String name)
	{
	   class popListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	    	   if(myPanel.aV != null && myPanel.aV.isActive)
	    	   {
	    		   Vehicle result;
	    		   if(myPanel.aV.isEngine)
	    		   {
	    			   result = myPanel.aV.trailer.findLastBlock();
	    		   }
	    		   else
	    		   {
	    			   result = myPanel.aV.findLastBlock();
	    		   }
	    		   if(result == null)
	    		   {
	    			   return;
	    		   }
		    	   result.block = myPanel.blocks.pop();
		    	   result.hasBlock = true;
	    		   myPanel.aV.isActive(false);
	    		   myPanel.aV = null;
	    		   myPanel.repaint();
	    	   }
	       }
	   }   
	   JMenuItem item = new JMenuItem(name);
	   ActionListener listener = new popListener();
	   item.addActionListener(listener);
	   return item;
	}
}
