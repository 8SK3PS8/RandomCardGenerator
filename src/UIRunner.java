//CO : A.J Bamgbelu

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIRunner 
{
	
	public static void main(String[] args)
	{
			JPanel game = new UserInterface();
			JFrame frame = new JFrame("TEAM Sid JAR");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setPreferredSize(new Dimension(frame.getToolkit().getScreenSize()));;
			frame.setUndecorated(false);
			frame.setVisible(true);
			frame.add(game);
			frame.revalidate();
	}
	
}
