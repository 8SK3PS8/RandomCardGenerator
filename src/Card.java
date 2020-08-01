// CO : A.J Bamgbelu

public class Card 
{
	private String type;
	private String number;
	private String path;
	
	public Card(String name , String pathO)
	{
		String[] sut = name.split("_");
		number = sut[0];
		type = sut[1];
		path = pathO;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getNumber()
	{
		return number;
	}
	
	public String getName()
	{
		return getType() + "_" + getNumber();
	}
	
	public String getPath()
	{
		return path;
	}

}
