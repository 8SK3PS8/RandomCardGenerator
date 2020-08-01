// CO: A.J Bamgbelu

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DeckOCards 
{
	private ArrayList<Card> deck;
	public DeckOCards()
	{
		deck = new ArrayList<Card>();
		
			try 
			{
				deckInit();
			} catch (Exception e) 
			{
				System.out.print("We have entered the catch statement in the DeckOCards ::      ");
				e.printStackTrace();
			}
	
	}
	
	public void deckInit() throws IOException
	{
		InputStream is = getClass().getResourceAsStream("/Resource/Card.txt");
		Scanner kb = new Scanner(is);
		
		while(kb.hasNextLine())
		{
			String input = kb.nextLine();
			String[] file = input.split(" ");
			deck.add(new Card(file[0], file[1]));
		}
		
	}
	
	public Card getCard(int i)
	{
		try
		{
			return deck.get(i);
		}
		catch(Exception e)
		{
			
		}
		return new Card("","");
	}
	
	public static void main(String[] args)
	{
		DeckOCards  deck = new DeckOCards();
	}
}
