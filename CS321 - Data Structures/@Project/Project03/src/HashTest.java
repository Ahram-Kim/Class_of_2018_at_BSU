import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Hash Test
 * @class CS321-004
 * @author Ahram Kim
 * @date October 20, 2017
 */
public class HashTest
{
	@SuppressWarnings({ "rawtypes", "unchecked", "resource"})
	public static void main(String[] args) throws IOException
	{
		String dataType = null;
		
		int inputType = 0;
		double loadFactor = 0;
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
			debugLevel = Integer.parseInt(args[2]);
		}
		else
		{
			System.err.println("Usage: java HashTest <input type> <load factor> [<debuf level>]");
		}

		HashTable linearTable = null;
		HashTable doubleTable = null;
		
		if(inputType == 1)	
		{
			linearTable = new HashTable<Integer>(0);
			doubleTable = new HashTable<Integer>(1);
		}
		else if(inputType == 2)
		{
			linearTable = new HashTable<Long>(0);
			doubleTable = new HashTable<Long>(1);
		}
		else if(inputType == 3)
		{	
			linearTable = new HashTable<String>(0);
			doubleTable = new HashTable<String>(1);
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
		int numInsert = (int) (linearTable.hashTableLength() * loadFactor);
		int i = 0;
		
//		while(i < numInsert)
//		{
//			if(inputType == 1)
//			{
//				Random rand = new Random(1234);
//				int key = rand.nextInt();
//				
//				linearTable.insert(key);
//				doubleTable.insert(key);
//				i++;
//			}
//			else if(inputType == 2)
//			{
//				long key = System.currentTimeMillis();
//				
//				if(linearTable.insert(key) == false)
//				{
//					i++;
//				}
//				if(doubleTable.insert(key) == false)
//				{
//					
//				}
//			}
//			else if(inputType == 3)
//			{
//				File file = new File("word-list");
//				Scanner scan = new Scanner(file);
//				
//				if(scan.hasNextLine())
//				{
//					String input = scan.nextLine();
//					
//					if(linearTable.insert(input) == false)
//					{
//						i++;
//					}
//					if(doubleTable.insert(input) == false)
//					{
//						
//					}
//				}
//			}
//		}
		if(inputType == 1)
		{
			Random rand = new Random(1234);
			
			while(i < numInsert)
			{
				int key = rand.nextInt();
				linearTable.insert(key);
				doubleTable.insert(key);
				i++;
			}	
		}
		else if(inputType == 2)
		{
			while(i < numInsert)
			{
				long key = System.currentTimeMillis();

				if(linearTable.insert(key) == false)
				{
					i++;
				}
				if(doubleTable.insert(key) == false)
				{
					
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
					if(linearTable.insert(input) == false)
					{
						i++;
					}
					if(doubleTable.insert(input) == false)
					{
						
					}
				}
			}
			scan.close();
		}
		
	
		DecimalFormat df = new DecimalFormat("#.000");
		
		if(debugLevel == 0)
		{
			System.out.println("A good table size is found: " + linearTable.hashTableLength());
			
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
			
			System.out.println();
			System.out.println("Using Linear Hashing.....");
			System.out.println("Inserted " + numInsert + " elements, of which " + linearTable.duplicateCount() + " duplicates");
			System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + df.format(linearTable.probeCount()));
			System.out.println("Using Double Hashing.....");
			System.out.println("Inserted " + numInsert + " elements, of which " + doubleTable.duplicateCount() + " duplicates");
			System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + df.format(doubleTable.probeCount()));
		}
		else if(debugLevel == 1)
		{
			System.out.println("A good table size is found: " + linearTable.tableSize());
			
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
			
			System.out.println();
			System.out.println("Using Linear Hashing.....");
			System.out.println("Inserted " + numInsert + " elements, of which " + linearTable.duplicateCount() + " duplicates");
			System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + df.format(linearTable.probeCount()));
			System.out.println("Using Double Hashing.....");
			System.out.println("Inserted " + numInsert + " elements, of which " + doubleTable.duplicateCount() + " duplicates");
			System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + df.format(doubleTable.probeCount()));
			
			FileWriter linearWriter;
			FileWriter doubleWriter;
			
			try
			{
				linearWriter = new FileWriter("linear-dump-0.5");
				doubleWriter = new FileWriter("double-dump-0.5");
				linearWriter.write(linearTable.printHashTable());
				doubleWriter.write(doubleTable.printHashTable());
			}
			catch(IOException exception)
			{
				exception.printStackTrace();
			}
		}
		else
		{
			
		}	
	}
}
