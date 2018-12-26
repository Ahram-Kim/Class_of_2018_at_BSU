import java.io.*;
import java.util.*;

public class CriticalPath 
{
	String testFile = null;
	String[] tokens; 

	@SuppressWarnings("rawtypes")
	ArrayList<LinkedList> linkedLists;
	ArrayList<Node> nodeNames;
	ArrayList<Node> mainNodes;
	ArrayList<Node> dummyNodes;
	LinkedList<String> incoming;	

	public static void main(String[] args) 
	{
		try
		{
			new CriticalPath(args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriticalPath(String[] args) throws Exception
	{
		if(args.length < 1 || args.length > 1)
		{
			System.err.println("Usage : java CriticalPath <file name>");
		}
		else
		{
			linkedLists = new ArrayList<LinkedList>();
			nodeNames = new ArrayList<Node>();
			mainNodes = new ArrayList<Node>();
			dummyNodes = new ArrayList<Node>();
			incoming = new LinkedList<String>();

			testFile = args[0];

			File file = new File(testFile);
			Scanner input = new Scanner(file);

			LinkedList<Node> xLinkedList = null;

			int p = 0, q = 0;
			while(input.hasNextLine())
			{
				tokens = input.nextLine().split("\\s+");
				xLinkedList = new LinkedList<Node>();

				if(p == 0)
				{
					Node aNode = new Node(" ");
					nodeNames.add(aNode);

					for(int i = 1; i < tokens.length; i++)
					{
						aNode = new Node(tokens[i].trim());
						nodeNames.add(aNode);
					}
				}
				else
				{
					for(int i = 0; i < tokens.length; i++)
					{
						if(i == 0)
						{
							for(int j = 0; j < mainNodes.size(); j++)
							{
								String name = (mainNodes.get(j).getName());

								if(name.equals(tokens[i]))
								{
									Node aNode = new Node(mainNodes.get(j).getName(), mainNodes.get(j).getWeight());
									xLinkedList.add(aNode);
								}
							}
						}

						if(q == 0)
						{
							Node aNode = new Node(tokens[i].trim(), 0);
							xLinkedList.add(aNode);
							mainNodes.add(aNode);
						}
						q++;

						boolean bool = false;

						if(i > 0)
						{
							if(Integer.parseInt(tokens[i]) >= 0)
							{
								Node aNode = new Node(nodeNames.get(i).getName(), Integer.parseInt(tokens[i]));
								xLinkedList.add(aNode);

								for(int j = 0; j < mainNodes.size(); j++)
								{
									String name = (mainNodes.get(j).getName());

									if(name.equals(nodeNames.get(j).getName()))
									{
										bool = true;
									}
								}

								if(!bool && aNode.getName() != null)
								{
									mainNodes.add(aNode);
								}
							}
						}
					}
				}
				linkedLists.add(xLinkedList);
				q++;
			}
			input.close();
		}
		convertActiveToEventGraph();
	}
	
	@SuppressWarnings("unchecked")
	public void convertActiveToEventGraph()
	{
		int preArraySize = linkedLists.size();
		
		for(int j = 1; j < linkedLists.size()-1; j++)
		{
			for(int i = 1; i < linkedLists.get(j).size(); i++)
			{
				Node aNode = (Node) linkedLists.get(j).get(i);
				String name = aNode.getName();
				
				for(int k = 0; k < nodeNames.size(); k++)
				{
					if(name.equals(nodeNames.get(k).getName()))
					{
						Node bNode = (Node)linkedLists.get(j).get(0);
						String name2 = bNode.getName();
						nodeNames.get(k).increaseIncoming();
						nodeNames.get(k).addIncoming(name2);
					}
				}
			}
		}
		
		for(int i = 0; i < nodeNames.size()-1; i++)
		{
			LinkedList<Node> xLinkedList = null;
			int incomingCount = nodeNames.get(i).getIncoming();
			
			if(incomingCount > 1)
			{
				nodeNames.get(i).setIncoming(1);
				xLinkedList = new LinkedList<Node>();
				
				//TODO check
				String d = "d";
				String name = nodeNames.get(i).getName();
				name = name + d;
				
				Node dummyNode = new Node(name, 0);
				dummyNode.setDummy(true);
				incoming = nodeNames.get(i).getIncomingNodeName();
				
				for(String s : incoming)
				{
					dummyNode.addIncoming(s);
				}
				
				nodeNames.get(i).clearIncoming();
				nodeNames.get(i).addIncoming(dummyNode.getName());
				dummyNode.setIncoming(incomingCount);
				xLinkedList.add(dummyNode);
				
				for(int j = 0; j < mainNodes.size(); j++)
				{
					if((mainNodes.get(j).getName()).equals(nodeNames.get(i).getName()))
					{
						Node aNode = new Node(mainNodes.get(j).getName(), mainNodes.get(j).getWeight());
						dummyNodes.add(aNode);
						xLinkedList.add(aNode);
						break;
					}
				}
				linkedLists.add(xLinkedList);
			}
		}
		
		for(int i = 1; i < linkedLists.size(); i++)
		{
			for(int j = 0; j < nodeNames.size(); j++)
			{
				Node aNode = (Node) linkedLists.get(i).get(0);
				
				if(aNode.getName().equals(nodeNames.get(j).getName()))
				{
					LinkedList<String> newIncoming = nodeNames.get(i).getIncomingNodeName();
					
					for(String s : newIncoming)
					{
						aNode.addIncoming(s);
					}
					aNode.setIncoming(nodeNames.get(j).getIncoming());
				}
			}
		}
		
		for(int i = 0; i < preArraySize; i++)
		{
			for(int j = 1; j < linkedLists.get(i).size(); j++)
			{
				Node aNode = (Node) linkedLists.get(i).get(j);
				
				for(int k = 0; k < dummyNodes.size(); k++)
				{
					if(dummyNodes.get(k).getName().equals(aNode.getName()))
					{
						String name = (dummyNodes.get(k).getName()) + "d";
						Node bNode = new Node(name, 0);
						linkedLists.get(i).set(j, bNode);
					}
				}
			}
		}
		
		ArrayList<Node> preTop = new ArrayList<Node>();
		
		for(int i = 1; i < linkedLists.size(); i++)
		{
			Node firstNode = (Node) linkedLists.get(i).get(0);
			preTop.add(firstNode);
		}
		
		EventNodeGraph graph = new EventNodeGraph(linkedLists.size() - 1);
		
		for(int i = 1; i < linkedLists.size(); i++)
		{
			for(int j = 1; j < linkedLists.get(i).size(); j++)
			{
				Node aNode = (Node) (linkedLists.get(i).get(j));
				
				for(int k = 0; k < preTop.size(); k++)
				{
					if(preTop.get(k).getName().equals(aNode.getName()))
					{
						graph.addEdge(i-1, k);
						break;
					}
				}
			}
		}
		
		LinkedList<Node> sorted = new LinkedList<Node>();
		Vector<Integer> topSort = graph.solveTopologicalSorting();
		
		for(int i = 0; i < topSort.size(); i++)
		{
			int val = topSort.get(i);
			Node thhisNode = preTop.get(val);
			sorted.add(thhisNode);
		}
		
		for(int i = 1; i < linkedLists.size(); i++)
		{
			Node aNode = (Node) linkedLists.get(i).get(0);
			
			for(int j = 1; j < linkedLists.get(i).size(); j++)
			{
				for(int k = 0; k < sorted.size(); k++)
				{
					if(aNode.getName().equals(sorted.get(k).getName()))
					{
						Node bNode = (Node) linkedLists.get(i).get(j);
						sorted.get(k).addOutgoing(bNode.getName());
					}
				}
			}
		}
		getEarlistCompletionTime(sorted);
		getLatestCompletionTime(sorted);
		getSlackCompletionTime(sorted);
		System.out.println(toString(sorted));
	}
	
	private void getEarlistCompletionTime(LinkedList<Node> sorted)
	{
		for(int i = 0; i < sorted.size(); i++)
		{
			int incomingNode = sorted.get(i).getIncomingNodeName().size();
			
			if(i == 0)
			{
				sorted.get(i).setEc(0);
			}
			else
			{
				int masterEC = 0;
				
				for(int j = 0; j < incomingNode; j++)
				{
					String node = (String) sorted.get(i).getIncomingNodeName().get(j);
					
					for(int a = 0; a < sorted.size(); a++)
					{
						if(node.equals(sorted.get(a).getName()))
						{
							int ec = sorted.get(a).getEc();
							
							if(ec > masterEC)
							{
								masterEC = ec;
							}
						}
					}
				}
				int EC = masterEC + sorted.get(i).getWeight();
				sorted.get(i).setEc(EC);
			}
		}
	}
	
	private void getLatestCompletionTime(LinkedList<Node> sorted)
	{
		for(int i = sorted.size() - 1; i >= 0; i--)
		{
			int outgoingNode = sorted.get(i).getOutgoingNodeName().size();
			
			if(i == sorted.size() - 1)
			{
				sorted.get(i).setLc(sorted.get(i).getEc());
			}
			else
			{
				int masterLC = sorted.get(sorted.size() - 1).getEc();
				
				for(int j = 0; j < outgoingNode; j++)
				{
					String node = (String) sorted.get(i).getOutgoingNodeName().get(j);
					
					for(int a = 0; a < sorted.size(); a++)
					{
						if(node.equals(sorted.get(a).getName()))
						{
							int lc = sorted.get(a).getLc() - sorted.get(a).getWeight();
							
							if(lc <= masterLC)
							{
								masterLC = lc;
							}
						}
					}
				}
				sorted.get(i).setLc(masterLC);
			}
		}
	}
	
	private void getSlackCompletionTime(LinkedList<Node> sorted)
	{
		for(int i = 0; i < sorted.size(); i++)
		{
			int slack = sorted.get(i).getLc() - sorted.get(i).getEc();
			sorted.get(i).setSlack(slack);
		}
	}
	
	public String toString(LinkedList<Node> sorted)
	{
		String s = "";
		s += " \n";
		s += "Actvity Node" + "      " + "EC" + "      " + "LC" + "      " + "SlackTime" + "\n";
		s += "---------------------------------------------- \n";
		
		for(int i = 0; i < sorted.size(); i++)
		{
			if(!sorted.get(i).isDummy)
			{
				s += sorted.get(i).getName()
						+ "                 " + sorted.get(i).getEc()
						+ "       " + sorted.get(i).getLc()
						+ "       " + sorted.get(i).getSlack() + "\n";
			}
		}
		s += "( Node is printed in topological order ) \n";
		return s;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
