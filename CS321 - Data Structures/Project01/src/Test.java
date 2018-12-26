import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test 
{
	public static void main(String[] args) throws IOException 
	{	
		int capOfCache1 = 0;
		int capOfCache2 = 0;
		
		String numOfCache = null;
		String fileName = null;
		String line = null;
		String token;
		
		numOfCache = args[0];
		
		if(numOfCache.equals("1"))
		{
			capOfCache1 = Integer.parseInt(args[1]);
			fileName = args[2];			
		}
		else if(numOfCache.equals("2"))
		{
			capOfCache1 = Integer.parseInt(args[1]);
			capOfCache2 = Integer.parseInt(args[2]);
			fileName = args[3];
		}

		Cache<String> cache1 = new Cache<String>(capOfCache1);
		Cache<String> cache2 = new Cache<String>(capOfCache2);
		
		BufferedReader buffReader;
		FileReader fileReader;
		File file;
		
		try
		{
			file = new File(fileName);
			
			if(!file.exists()) 
			{
				System.out.println("File does not exist");
				System.exit(0);
			}
			
			fileReader = new FileReader(file);
			buffReader = new BufferedReader(fileReader);
			
			while(buffReader.ready())
			{
				line = buffReader.readLine();
				StringTokenizer sToken = new StringTokenizer(line);
				
				while(sToken.hasMoreTokens())
				{
					token = sToken.nextToken();
					
					if(capOfCache2 == 0)
					{
						if(cache1.get(token) != null)
						{
							cache1.write(token);
						}
						else
						{
							cache1.add(token);
						}
					}
					else
					{
						if(cache1.get(token) != null)
						{
							cache1.write(token);
						}
						else
						{
							if(cache2.get(token) != null)
							{
								cache2.write(token);
								cache1.add(token);
							}
							else
							{
								cache2.add(token);
								cache1.add(token);
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.getMessage();
			e.printStackTrace();
			System.exit(0);
		}
		

		
		if(numOfCache.equals("1"))
		{
			double a = (double)(cache1.hitsCache()+cache2.hitsCache())/(double)(cache1.accessCache())*100;
			
			System.out.println("L1 cache with " + capOfCache1 + " entries created.");
			System.out.println("...");
			System.out.println("Number of L1 cache hits: " + (int) cache1.hitsCache());
			System.out.printf("L1 cache Hit rate: %.2f%%%n", (cache1.getHitRate()*100));
			System.out.println();
			System.out.println("Total number of accesses: " + (int) cache1.accessCache());
			System.out.println("Total number of cache hits: " +cache1.hitsCache());
			System.out.printf("Overall hit rate: %.2f%%%n", a);
		}
		else if(numOfCache.equals("2"))
		{
			double a = (double)(cache1.hitsCache()+cache2.hitsCache())/(double)(cache1.accessCache())*100;
			
			System.out.println("L1 cache with " + capOfCache1 + " entries created");
			System.out.println("L2 cache with " + capOfCache2 + " entries created");
			System.out.println("...");
			System.out.println("Number of L1 cache hits: " + (int) cache1.hitsCache());
			System.out.printf("L1 cache Hit rate: %.2f%%%n",(cache1.getHitRate() * 100));
			System.out.println();
			System.out.println("Number of L2 cache hits: " + (int) cache2.hitsCache());
			System.out.printf("L2 cache Hit rate: %.2f%%%n",(cache2.getHitRate() * 100));
			System.out.println();
			System.out.println("Total number of accesses: " + (int) cache1.accessCache());
			System.out.println("Total number of cache hits: " + (cache1.hitsCache() + cache2.hitsCache()));
			System.out.printf("Overall hit rate: %.2f%%%n", a);
		}
	}
}
