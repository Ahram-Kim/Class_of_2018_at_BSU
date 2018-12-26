
public class Edge 
{
	String name = "";	
	int weight;
	int from;
	int to;
	
	public Edge(String n, int w)
	{
		this.name = n;
		this.weight = w;
	}
	
	public String getName() 
	{
		return name;
	}
	
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	
	public int getWeight()
	{
		return weight;
	}
	
	
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	
	public int getFrom()
	{
		return from;
	}
	
	
	public void setFrom(int from) 
	{
		this.from = from;
	}
	
	
	public int getTo() 
	{
		return to;
	}
	
	
	public void setTo(int to)
	{
		this.to = to;
	}

	public String toString()
	{
		return "(" + name + "," + weight + ", " + from + ", " + to + ")";
	}
}
