
public class ActivityNode
{
	String name;
	int weight;
	
	public ActivityNode(String n, int w)
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
	
	public String toString()
	{
		return "(" + name + "," + weight + ")";
	}
}
