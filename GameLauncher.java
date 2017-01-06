import javax.swing.JFrame;

/**
 * main function class to run the program
 * @author Tolaz Hewa
 *
 */
public class GameLauncher
{
	public static void main(String[] args)
	{
		JFrame myFrame = new GameFrame();
  	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("Assignment 2");
	  myFrame.setVisible(true);
	}

}
