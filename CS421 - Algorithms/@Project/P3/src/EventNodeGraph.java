import java.util.*;

public class EventNodeGraph 
{
	int size;
	List<Integer> edge[];
	
	@SuppressWarnings("unchecked")
	public EventNodeGraph(int size)
	{
		this.size = size;
		this.edge = new ArrayList[size];
		
		for(int i = 0; i < size; i++)
		{
			edge[i] = new ArrayList<Integer>();
		}
	}
	
	public Vector<Integer> solveTopologicalSorting()
	{
		int topoSortList[] = new int[size];
		
		for(int i = 0; i < size; i++)
		{
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
	
	public void addEdge(int from, int to)
	{
		edge[from].add(to);
	}
}
