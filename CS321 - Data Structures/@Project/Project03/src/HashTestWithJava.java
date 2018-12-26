import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Hash Test
 * @class CS321-004
 * @author Ahram Kim
 * @date October 20, 2017
 */
public class HashTestWithJava
{
	@SuppressWarnings({ "rawtypes", "unchecked", "resource"})
	public static void main(String[] args) throws IOException
	{
		String dataType = null;
		
		int inputType = 0;
		double loadFactor = 0;
		int tableSize = 0;
		int debugLevel = 0;

		if(args.length == 1)
		{
			inputType = Integer.parseInt(args[0]);
		}
		else if(args.length == 2)
		{
			inputType = Integer.parseInt(args[0]);
			loadFactor = Integer.parseInt(args[1]);
		}
		else if(args.length == 3)
		{
			inputType = Integer.parseInt(args[0]);
			loadFactor = Double.parseDouble(args[1]);
			tableSize = Integer.parseInt(args[2]);
		}
		else if(args.length == 4)
		{
			inputType = Integer.parseInt(args[0]);
			loadFactor = Double.parseDouble(args[1]);
			tableSize = Integer.parseInt(args[2]);
			debugLevel = Integer.parseInt(args[3]);
		}
		else
		{
			System.err.println("Usage: java HashTest <input type> <load factor> [<debuf level>]");
		}

		Hashtable hashtable = null; 
		
		if(inputType == 1)	
		{
			hashtable = new Hashtable<Integer, Integer>();
		}
		else if(inputType == 2)
		{
			hashtable = new Hashtable<Long, Integer>();
		}
		else if(inputType == 3)
		{	
			hashtable = new Hashtable<String , Integer>();
		}
		
		int numInsert = (int) (tableSize * loadFactor);
		int i = 0;
		int duplicate =0;
		
		if(inputType == 1)
		{
			Random rand = new Random(1234);
			
			while(i < numInsert)
			{
				int key = rand.nextInt();
				
				if(hashtable.containsKey(key))
				{
					duplicate = (int) hashtable.get(key);
					duplicate++;
					hashtable.put(key, duplicate);
				}
				else
				{
					hashtable.put(key, 0);
					i++;
				}
			}	
		}
		else if(inputType == 2)
		{
			while(i < numInsert)
			{
				long key = System.currentTimeMillis();
				
				if(hashtable.containsKey(key))
				{
					duplicate = (int) hashtable.get(key);
					duplicate++;
					hashtable.put(key, duplicate);
				}
				else
				{
					hashtable.put(key, 0);
					i++;
				}
			}
		}
		else if(inputType == 3)
		{
			File file = new File("word-list");	
			Scanner scan = new Scanner(file);
			
			while(i < numInsert)
			{
				String input = scan.next();
				
				if(scan.hasNextLine())
				{
					if(hashtable.containsKey(input))
					{
						duplicate = (int) hashtable.get(input);
						duplicate++;
						hashtable.put(input, duplicate);
					}
					else
					{
						hashtable.put(input, 0);
						i++;
					}
				}
			}
			scan.close();
		}

		if(debugLevel == 0)
		{
			System.out.println("Table size: " + tableSize);
			
			if(inputType == 1)
			{
				dataType = "random number generator";
				System.out.println("Data source type: " + dataType);
			}
			else if(inputType == 2)
			{
				dataType = "current time generator";
				System.out.println("Data source type: " + dataType);
			}
			else if(inputType == 3)
			{
				dataType = "word-list";
				System.out.println("Data source type: " + dataType);
			}
			
			System.out.println("Using JAVA Hashtable.....");
			System.out.println("Inserted " + numInsert + " elements, of which " + getDup(hashtable) + " duplicates");
			System.out.println("load factor = " + loadFactor);
		}
		else if(debugLevel == 1)
		{
			System.out.println("Table size: " + tableSize);
			
			if(inputType == 1)
			{
				System.out.println("Data source type: random number generator");
			}
			else if(inputType == 2)
			{
				System.out.println("Data source type: current time generator");
			}
			else if(inputType == 3)
			{
				System.out.println("Data source type: word-list");
			}

			System.out.println("Using JAVA Hashtable.....");
			System.out.println("Inserted " + numInsert + " elements, of which " + getDup(hashtable) + " duplicates");
			System.out.println("load factor = " + loadFactor);
			
			FileWriter hsWriter;
			
			try
			{
				hsWriter = new FileWriter("hashtable-dump-0.5");
				hsWriter.write(hashtable.toString());
			}
			catch(IOException exception)
			{
				exception.printStackTrace();
			}
		}	
	}
	
	private static String getDup(Hashtable hashtable)
	{
		int sum = 0;
		
		Set keys = hashtable.keySet();
		
		for(Object i: keys)
		{
			sum = sum + (int) hashtable.get(i);
		}
		return Integer.toString(sum);
	}
}
