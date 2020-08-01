// CO: A.J Bamgbelu

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.net.URL;
import javax.swing.JOptionPane;

public class UserInterface extends JPanel implements MouseListener
{
	private HashMap<Rectangle,String[]> HitBoxes;
	private HashMap<String, Image> images;
	private DeckOCards deck;
	private String topLeftCard;
	private String topRightCard;
	private String bottomLeftCard;
	private String bottomRightCard;
	private String CardLastClicked;
	private String borderClicked;
	private Boolean b = false;
	public final int SCREEN_WIDTH = getToolkit().getScreenSize().width;
	public final int SCREEN_HEIGHT = getToolkit().getScreenSize().height;
	
	


	public UserInterface()
	{
		HitBoxes = new HashMap<Rectangle, String[]>();
		images = new HashMap<String, Image>();
		deck = new DeckOCards();
		addMouseListener(this);
		CardLastClicked = "";
		borderClicked = "";
		bottomRightCard = "5_C";
		bottomLeftCard = "7_H";
		topRightCard = "8_D";
		topLeftCard = "9_C";

		images.put("Wooden_Table", getImage("/Pictures/Final_Wood.jpg"));
		images.put("Random_Logo",getImage("/Pictures/Random_Logo.png"));
		images.put("Sqaure_Border",getImage("/Pictures/Square.png"));
		images.put("Exit_Button",getImage("/Pictures/Exit_Button.png"));
		images.put("New_Exit_Button",getImage("/Pictures/New_Exit_Button.PNG"));
		for(int i = 0; i < 36; i++)
		{
			String name = deck.getCard(i).getNumber() + "_" + deck.getCard(i).getType();
			images.put(name, getImage(deck.getCard(i).getPath()));
		}

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawScreen(g);
	}
	
	public Image getImage(String path)
	{
		Image tempImage = null;
		try
		{
			URL imageURL = UserInterface.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		}
		catch(Exception e)
		{
			System.out.println("An error occured - " + e.getMessage());
		}
		
		return tempImage;
	}
	
	public void drawScreen(Graphics g)
	{
		String[] type = {"C","D","H","S"};
		int x,y,width,height;
		g.drawImage(images.get("Wooden_Table"),0,0,SCREEN_WIDTH,SCREEN_HEIGHT,this);
		
		x = ((int)(SCREEN_WIDTH*.739));
		y = ((int)(SCREEN_HEIGHT*.976));
		drawText(g,x,y,"Created by A.J Bamgbelu","Courier||Bold||30||00000000000");

		width = ((int)(SCREEN_WIDTH*.943))/2;
		height = ((int)(SCREEN_HEIGHT*.167))/2;
		x = ((int)(SCREEN_WIDTH*.263));
		y = ((int)(SCREEN_HEIGHT*.444));
		g.drawImage(images.get("Random_Logo"),x,y,width,height,this);
		
		width = ((int)((SCREEN_HEIGHT*.35)*1.08));
		height = ((int)((SCREEN_WIDTH *.4)*.58));
		
		int num = getRandomInteger(10,2);
		String number = ""+num;
		String typ = type[getRandomInteger(4,0)];
		String card = number + "_" + typ;
		

		/*This is the top left card*/
		x = ((int)(SCREEN_WIDTH*.0938));
		y = ((int)(SCREEN_HEIGHT*.035));
		if(CardLastClicked.equals("Top_Left_Card") && borderClicked.equals(""))
		{
			card =  generateCard(card);
			topLeftCard = card;
			g.drawImage(images.get(card),x,y,width,height, this);
			System.out.println(" The card width " + width + " The card height is " + height);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Top_Left_Card"});
		}
		else
		{
			g.drawImage(images.get(topLeftCard),x,y,width,height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Top_Left_Card"});
		}
		
		
		
		
		/*This is the bottom left card*/
		x = ((int)(SCREEN_WIDTH*.0938));
		y = ((int)(SCREEN_HEIGHT*.530));
		if(CardLastClicked.equals("Bottom_Left_Card") && borderClicked.equals(""))
		{
			card =  generateCard(card);
			bottomLeftCard = card;
			g.drawImage(images.get(card),x,y,width,height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Bottom_Left_Card"});
		}
		else
		{
			g.drawImage(images.get(bottomLeftCard),x,y, width,height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Bottom_Left_Card"});
		}
			
		

		
	
		
		/*This is the top right card*/
		x = ((int)(SCREEN_WIDTH*.728));
		y = ((int)(SCREEN_HEIGHT*.035));
		if(CardLastClicked.equals("Top_Right_Card") && borderClicked.equals(""))
		{
			card =  generateCard(card);
			topRightCard = card;
			g.drawImage(images.get(card),x,y,width, height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Top_Right_Card"});
		}
		else
		{
			g.drawImage(images.get(topRightCard),x,y,width,height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Top_Right_Card"});
		}
		
		

		

		
		/*This is the bottom right card*/
		x = ((int)(SCREEN_WIDTH*.728));
		y = ((int)(SCREEN_HEIGHT*.530));
		if(CardLastClicked.equals("Bottom_Right_Card") && borderClicked.equals(""))
		{
			card =  generateCard(card);
			bottomRightCard = card;
			g.drawImage(images.get(card),x,y, width, height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Bottom_Right_Card"});
		}
		else
		{
			g.drawImage(images.get(bottomRightCard),x,y,width,height, this);
			HitBoxes.put(new Rectangle((x),y,width,height), new String[] {"Bottom_Right_Card"});
		}
		
		CardLastClicked = "";
		
	}
	
	public void drawText(Graphics g, int x, int y, String text, String parameters)
	{
		String[] params = parameters.split("\\|\\|");
		String fontName = params[0];
		int bold = params[1].equals("bold") ? Font.BOLD : Font.PLAIN;
		int size = Integer.parseInt(params[2]);

		int red = Integer.valueOf(params[3].substring(0, 3));
		int green = Integer.valueOf(params[3].substring(3, 6));
		int blue = Integer.valueOf(params[3].substring(6, 9));
		Color color = new Color(red, green, blue);
		Font font = new Font(fontName, bold, size);

		TextLayout tl = new TextLayout(text, font, ((Graphics2D)g).getFontRenderContext());
		g.setColor(color);
		tl.draw((Graphics2D)g, x, y);
	}
	
	 public static int getRandomInteger(int maximum, int minimum)
	 {
	      return ((int) (Math.random()*(maximum - minimum))) + minimum;
	 }
	 
	 public String generateCard(String card)
	 {
		 String[] type = {"C","D","H","S"};
		 
			while(images.get(card).getWidth(null) <= 0  || images.get(card).getHeight(null) <= 0)
			{
				 int num = getRandomInteger(10,2);
				 String number = ""+num;
				 String typ = type[getRandomInteger(4,0)];
				  card = number + "_" + typ;
			}
			
			return card;
	 }
	
	public static void main(String[] args)
	{
		JPanel game = new UserInterface();
		JFrame frame = new JFrame("A.J JAR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.add(game);
		
		frame.revalidate();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
		for(Rectangle i : HitBoxes.keySet())
		{
			if(i.contains(e.getPoint()))
			{
				String[] purposeOfClick = HitBoxes.get(i);
				String purpose = purposeOfClick[0];
				CardLastClicked = purpose;
				if(purpose.equals("EXIT"))
				{
					System.exit(0);
				}
				borderClicked = "";
				b = true;
			}
		}
		
		if(b = false)
		{
				CardLastClicked = "";
				borderClicked = "borderClicked";
		}
		HitBoxes.clear();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
