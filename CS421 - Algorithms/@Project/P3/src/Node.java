import java.util.*;
public class Node 
{
	String name;
	boolean isDummy;
	int weight, incoming, lc, ec, slack;
	
	LinkedList<String> incomingNodeName;
	LinkedList<String> outgoingNodeName;
	
	public Node(String name, int weight)
	{
		this.name = name;
		this.weight = weight;
		this.isDummy = false;
		incomingNodeName = new LinkedList<String>();
		outgoingNodeName = new LinkedList<String>();
	}
	
	public Node(String name)
	{
		this.name = name;
		this.isDummy = false;
		incomingNodeName = new LinkedList<String>();
		outgoingNodeName = new LinkedList<String>();
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

	public int getWeight() 
	{
		return weight;
	}

	public void setWeight(int weight) 
	{
		this.weight = weight;
	}

	public int getIncoming()
	{
		return incoming;
	}

	public void setIncoming(int incoming) 
	{
		this.incoming = incoming;
	}

	public int getLc() 
	{
		return lc;
	}

	public void setLc(int lc) 
	{
		this.lc = lc;
	}

	public int getEc() 
	{
		return ec;
	}

	public void setEc(int ec) 
	{
		this.ec = ec;
	}

	public int getSlack() 
	{
		return slack;
	}

	public void setSlack(int slack) 
	{
		this.slack = slack;
	}

	public LinkedList<String> getIncomingNodeName() 
	{
		return incomingNodeName;
	}

	public void setIncomingNodeName(LinkedList<String> incomingNodeName) 
	{
		this.incomingNodeName = incomingNodeName;
	}
	
	public void addIncoming(String name)
	{
		incomingNodeName.add(name);
	}
	
	public void clearIncoming()
	{
		incomingNodeName.clear();
	}
	
	public void increaseIncoming()
	{
		incoming++;
	}

	public LinkedList<String> getOutgoingNodeName() 
	{
		return outgoingNodeName;
	}

	public void setOutgoingNodeName(LinkedList<String> outgoingNodeName) 
	{
		this.outgoingNodeName = outgoingNodeName;
	}
	
	public void addOutgoing(String name)
	{
		outgoingNodeName.add(name);
	}
	
	public void clearOutgoing()
	{
		outgoingNodeName.clear();
	}
	
	public String toString()
	{
		return "( " + name + ", " + weight + ", " + incoming + ", " + lc + ", " 
				+ ec + ", " + slack + "," + isDummy + ", " 
				+ incomingNodeName + ", " + outgoingNodeName + " )";
	}
}
