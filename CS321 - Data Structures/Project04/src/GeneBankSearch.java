import java.io.*;
import java.util.*;

/**
 * CS321 BTree Project - GeneBankSearch 
 * This driver class searches sequences from binary data file we created from genebankcreate class.
 * @author Ella, Max, Ahram
 *
 */
public class GeneBankSearch 
{
	/**
	 * Taking care of the argument and searching the query.
	 * @param args - has cache, btree file name, query file name, cache size (if cache uses), and debug level
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		boolean hasCache = false;
		String btreeFile = null;
		String queryFile = null;
		int cacheSize = 0;
		int debugLevel = 0;
		TreeObject searchedObject = new TreeObject(0, 0);
		TreeObject resultObject = new TreeObject(0, 0);
		String q = null;
		boolean correctedInput = false;

		if(args[0].equals("1"))
		{
			hasCache = true;
			if(hasCache == true){
				System.err.println("We do not support cache function in this program. please select 0 for the first argument.");
				return;
			}

			if(debugLevel != 0)
			{
				System.err.println("<Debug Level)>: Input argument shoule be 0. \n");
				correctedInput = false;
				return;
			}

			if(args.length != 5)
			{
				System.err.println("Usage: java GeneBankSearch <0/1 (no/with Cache)> <btree file> <query file> [<cache size>] [<debug level>] \n");
				correctedInput = false;
				return;
			}

			hasCache = true;	// Btree with cache
			btreeFile = args[1];
			queryFile = args[2];
			cacheSize = Integer.parseInt(args[3]);
			debugLevel = Integer.parseInt(args[4]);
			correctedInput = true;

		}
		if(args[0].equals("0"))
		{
			if(debugLevel != 0)
			{
				System.err.println("<Debug Level)>: Input argument shoule be 0. \n");
				correctedInput = false;
				return;
			}

			if(args.length != 4)
			{
				System.err.println("Usage: java GeneBankSearch <0/1 (no/with Cache)> <btree file> <query file> [<debug level>] \n");
				correctedInput = false;

				if(btreeFile == null){
					System.err.println("Please check your b tree file name and retry.");
					return;
				}

				if(queryFile == null){
					System.err.println("Please check your query file name and retry.");
					return;
				}

				return;
			}

			hasCache = false;
			btreeFile = args[1];
			queryFile = args[2];
			debugLevel = Integer.parseInt(args[3]);
			correctedInput = true;

		}
		else if(!(args[0].equals("0") || args[0].equals("1")))
		{
			System.err.println("<0/1 (no/with Cache)>: Input argument coule be parsed as a 0 or 1");
			correctedInput = false;
			return;
		}
		//starting search
		try
		{
			GeneBankConvert convert = new GeneBankConvert();
			BTree bT = new BTree(btreeFile);
			@SuppressWarnings("resource")
			RandomAccessFile file = new RandomAccessFile(btreeFile, "rw");			
			Scanner queryScan = new Scanner(new File(queryFile));
			//print out the sequence : frequency
			if(correctedInput == true){
				System.out.println("*********************");
				System.out.println("sequence : frequency ");
			}

			String binaryString = "";

			//read the query file
			while(queryScan.hasNext())
			{
				q = queryScan.nextLine();

				for(int k = 0; k < q.length(); k++) {
					if(q.charAt(k) == 'A') {
						binaryString += "00";
					}
					if(q.charAt(k) == 'C') {
						binaryString += "01";
					}
					if(q.charAt(k) == 'G') {
						binaryString += "10";
					}
					if(q.charAt(k) == 'T') {
						binaryString += "11";
					}
				}

			}

			long data = 0;
			int c = 0;
			int d = q.length()*2;
			while(c != binaryString.length()) {
				data = Long.parseLong(binaryString.substring(c,d+c), 2);

				searchedObject.setKey(data);
				searchedObject.setFrequency(1);

				resultObject = bT.bTreeSearch(bT.root, searchedObject);
				if(resultObject != null & correctedInput == true){
					System.out.println(convert.convertLongToString(data, q.length()) + " : " + resultObject.getFrequency());
				}

				c += q.length()*2;
			}
			if(correctedInput == true){
				System.out.println("*********************");
			}
			queryScan.close(); 
		}
		catch(Exception e)
		{
			if(args.length > 5 || debugLevel != 0) {
				System.err.println("CHECK YOUR INPUT! Usage: <0/1 (no/with Cache)> <btree file> <query file> [<debug level>]");
			}
			e.printStackTrace();
		}

	}
}