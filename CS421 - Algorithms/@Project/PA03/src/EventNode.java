
public class EventNode 
{
	public int ec = 0, lc = 0, slack = 0;
	String name = "";
	boolean isDummy = false;
	
	public EventNode(String n)
	{
		this.name = n;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public boolean isDummy() 
	{
		return isDummy;
	}

	public void setDummy(boolean isDummy) 
	{
		this.isDummy = isDummy;
	}
	
	public String toString()
	{
		return "(" + name + ", " + isDummy + ", " + ec + ", " + lc + ", " + slack + ")";
	}
}
