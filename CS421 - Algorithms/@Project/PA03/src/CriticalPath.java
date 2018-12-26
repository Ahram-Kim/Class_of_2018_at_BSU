import java.io.*;
import java.util.*;

public class CriticalPath 
{
	public static int count = 0;

	String testFile = null;
	String[] tokens;
	String[] nodeNames = null;

	@SuppressWarnings("rawtypes")
	ArrayList<LinkedList> listOfLinkedList;
	ArrayList<Edge> listOfEdges;
	ArrayList<ActivityNode> activityNodeList;
	ArrayList<EventNode> eventNodeList;

	int size;
	List<Integer> edge[];

	HashMap<String, Integer> activeToEventMapping;		// activity symbol -> event index

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

	@SuppressWarnings("rawtypes")
	private CriticalPath(String[] args) throws Exception
	{
		if(args.length < 1 || args.length > 1)
		{
			System.err.println("Usage : java CriticalPath <file name>");
		}
		else
		{
			listOfLinkedList = new ArrayList<LinkedList>();
			activeToEventMapping = new HashMap<String, Integer>();
			activityNodeList = new ArrayList<ActivityNode>();
			eventNodeList = new ArrayList<EventNode>();


			testFile = args[0];

			File file = new File(testFile);
			Scanner fileScan = new Scanner(file);

			LinkedList<ActivityNode> aLinkList = null;

			int i = 0;
			while(fileScan.hasNextLine()) 
			{	
				tokens = fileScan.nextLine().split("\\s+");		//remove all whitespaces

				if(i == 0)
				{
					nodeNames = new String[tokens.length];

					for(i = 0; i < tokens.length; i++)
					{
						nodeNames[i] = tokens[i];
					}	
				}
				else
				{ 
					aLinkList = new LinkedList<ActivityNode>();

					for(i = 1; i < tokens.length; i++)
					{
						aLinkList.add(new ActivityNode(nodeNames[i], Integer.parseInt(tokens[i].trim()))) ;
					}
					listOfLinkedList.add(aLinkList);
				}
			}
			fileScan.close();
		}
		convertActiveToEventGraph();
		calculateECTime();
		calculateLCTime();
		calculateSlackTime();
		//		System.out.println(toString());
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public void convertActiveToEventGraph()
	{
		listOfEdges = new ArrayList<Edge>();
		LinkedList<Edge> edgeList = null;
		LinkedList aLinkList;

		int count = 0;

		for(int i = 0; i < listOfLinkedList.get(0).size(); i++)		// i is column
		{
			count = 0;
			ActivityNode temp = null;

			for(int j = 0; j < listOfLinkedList.size(); j++)		// j is row edge from j to i
			{
				aLinkList = listOfLinkedList.get(j);
				ActivityNode acNode = ((ActivityNode)aLinkList.get(i));

				if(activeToEventMapping.get(acNode.getName()) == null)
				{
					EventNode evNode = new EventNode(acNode.getName());
					eventNodeList.add(evNode);
					activeToEventMapping.put(acNode.name, eventNodeList.size() - 1);
				}

				if(acNode.weight >= 0)
				{
					count++;
					EventNode evNode = null;
					temp = acNode;

					if(activeToEventMapping.get(acNode.getName()) == null)
					{
						evNode = new EventNode(acNode.getName());
						eventNodeList.add(evNode);
						activeToEventMapping.put(acNode.name, eventNodeList.size() - 1);
					}
					else
					{
						evNode = eventNodeList.get(activeToEventMapping.get(acNode.getName()));
					}
					Edge inEdge = new Edge(acNode.name, acNode.weight);
					ActivityNode node = (ActivityNode)listOfLinkedList.get(0).get(j);
					inEdge.setFrom(activeToEventMapping.get(node.getName()));
					inEdge.setTo(activeToEventMapping.get(acNode.name));
					listOfEdges.add(inEdge);
				}
			}

			if(count > 1)
			{
				EventNode dummyNode = new EventNode(getDummyNode());
				dummyNode.setDummy(true);

				for(int a = 1; a <= count; a++)
				{
					Edge edge = listOfEdges.get(listOfEdges.size() - a);
					edge.setName(dummyNode.getName());
					edge.setWeight(0);
					listOfEdges.set(listOfEdges.size() - a, edge);
				}

				EventNode lastNode = eventNodeList.get(eventNodeList.size() - 1);
				lastNode.setName(dummyNode.getName());
				lastNode.setDummy(true);
				eventNodeList.set(eventNodeList.size() - 1, lastNode);

				EventNode realNode = new EventNode(temp.name);
				eventNodeList.add(realNode);
				Edge dummyEdge = new Edge(temp.name, temp.weight);
				dummyEdge.setFrom(eventNodeList.size() - 2);
				dummyEdge.setTo(eventNodeList.size() - 1);
				listOfEdges.add(dummyEdge);
				activeToEventMapping.put(temp.name, eventNodeList.size() - 1);
			}
		}
	}

	public static String getDummyNode()
	{
		count++;
		return "d" + count;
	}

	public Vector<Integer> solveTopologicalSorting()
	{
		int topoSortList[] = new int[size];

		for(int i = 0; i < size; i++)
		{
			edge[i] = new ArrayList<Integer>();
			ArrayList<Integer> temp = (ArrayList<Integer>) edge[i];

			for(int n : temp)
			{
				topoSortList[n]++;
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();

		for(int i = 0; i < size; i++)
		{
			if(topoSortList[i] == 0)
			{
				q.add(i);
			}
		}

		Vector<Integer> topoSort = new Vector<Integer>();

		while(!q.isEmpty())
		{
			int temp = q.poll();
			topoSort.add(temp);

			for(int n : edge[temp])
			{
				if(topoSortList[n]-- == 0)
				{
					q.add(n);
				}
			}
		}
		return topoSort;
	}

	public void calculateECTime()
	{

		int destinationIndex = 0;
		int destinationValue = 0;

		System.out.println("1. GET THE EARLIEST COMPLETION TIME (w/ DUMMY NODE)");
		System.out.println("-------------------------------------------------");
		System.out.println("Node Name : Node weight : EC");
		for(int i = 0; i < listOfEdges.size(); i++)
		{
			Edge edge = listOfEdges.get(i);

			if(destinationIndex != edge.to)
			{
				destinationValue = 0;
			}
			else {

			}

			destinationValue = eventNodeList.get(edge.from).ec + edge.weight;
			EventNode eNode = eventNodeList.get(edge.to);

			if(destinationValue > eNode.ec)
			{
				eNode.ec = destinationValue;
				eventNodeList.set(edge.to, eNode);
			}
			destinationIndex = edge.to;

			System.out.println(listOfEdges.get(i).name + "  : " + listOfEdges.get(i).weight + " : " + eNode.ec);
		}
		System.out.println("-----------------------------------------");
		System.out.println("Edges : " + listOfEdges);
		System.out.println("Event Nodes : " + eventNodeList);
		System.out.println();
		System.out.println();

	}

	@SuppressWarnings("unused")
	public void calculateLCTime()
	{
		int sourceIndex = listOfEdges.get(listOfEdges.size() - 1).from;
		int destinationIndex = listOfEdges.get(listOfEdges.size() - 1).to;
		int sourceValue = eventNodeList.get(sourceIndex).ec;

		EventNode eNode = eventNodeList.get(destinationIndex);
		eNode.lc = eNode.ec;
		eventNodeList.set(destinationIndex, eNode);		

		System.out.println("2. GET THE LATEST COMPLETION TIME (w/ DUMMY NODE)");
		System.out.println("-------------------------------------------------");
		System.out.println("Node Name : Node weight : LC");
		for(int i = listOfEdges.size() - 1; i >= 0; i--)
		{
			Edge edge = listOfEdges.get(i);
			EventNode sourceNode = eventNodeList.get(edge.from);
			EventNode destinationNode = eventNodeList.get(edge.to);

			if(sourceNode.lc == 0)
			{
				sourceNode.lc = destinationNode.lc - edge.weight;
			}
			else
			{
				if((destinationNode.lc - edge.weight) < sourceNode.lc)
				{
					sourceNode.lc = destinationNode.lc - edge.weight;
				}
			}
			eventNodeList.set(edge.from, sourceNode);

			System.out.println(listOfEdges.get(i).name + "  : " + listOfEdges.get(i).weight + " : " + sourceNode.lc);

		}
		System.out.println("-------------------------------------------");
		System.out.println("Edges : " + listOfEdges);
		System.out.println("Event Nodes : " + eventNodeList);
		System.out.println();
		System.out.println();
	}

	public void calculateSlackTime()
	{
		EventNode node = null;

		System.out.println("3. GET THE SLACK TIME (w/ DUMMY NODE)");
		System.out.println("-------------------------------------------------");
		System.out.println("Node Name : Node weight : Slack Time");
		for(int i = 0; i < eventNodeList.size(); i++)
		{
			node = eventNodeList.get(i);
			node.slack = node.lc - node.ec;
			eventNodeList.set(i, node);

			System.out.println(listOfEdges.get(i).name + "  : " + listOfEdges.get(i).weight + " : " + node.slack);

		}
		System.out.println("-------------------------------------------");
		System.out.println("Edges:"+listOfEdges);
		System.out.println("Event Nodes:"+eventNodeList);
		System.out.println();
		System.out.println();
	}

	//	public String toString()
	//	{
	//		String s = "";
	//		s += " \n";
	//		s += "Actvity Node" + "      " + "EC" + "      " + "LC" + "      " + "SlackTime" + "\n";
	//		s += "---------------------------------------------- \n";
	//		
	//		for(int i = 1; i < tokens.length; i ++)
	//		{
	//			EventNode node = eventNodeList.get(i);
	//
	//		}
	//		
	//		return s;
	//	}
} 
