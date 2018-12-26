
public class Edge 
{
	String name;
	int weight;
	Node from, to;
	
	public Edge(String name, int weight, Node from, Node to)
	{
		this.name = name;
		this.weight = weight;
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

	public Node getFrom() 
	{
		return from;
	}

	public void setFrom(Node from) 
	{
		this.from = from;
	}

	public Node getTo() 
	{
		return to;
	}

	public void setTo(Node to) 
	{
		this.to = to;
	}
	
	public String toString()
	{
		return "( " + name + ", " + weight + ", " + from + ", " + to + " )";
	}
}
