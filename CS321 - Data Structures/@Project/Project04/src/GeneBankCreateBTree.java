import java.io.*;
import java.util.*;

/**
 * CS321 B Tree Project - GeneBankCreateBtree
 * This drive class creates a B tree and the dump file prints out the result in order traversal.
 * @author Ella, Ahram, Max
 *
 */
public class GeneBankCreateBTree 
{
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException
	{
		// figure out cache and what to do with sequence length 
		boolean hasCache = false;
		int degree = 0;
		String gbkFile = null ;
		int seqLen =0;
		int cacheSize;
		int debugLevel=0;

		if(args[0].equals("1"))
		{
			hasCache = true;	// Btree with cache
			if(hasCache == true){
				System.err.println("We do not support cache function in this program. please select 0 for the first argument.");
				return;
			}

			degree = Integer.parseInt(args[1]);
			gbkFile = args[2];
			seqLen = Integer.parseInt(args[3]);
			cacheSize = Integer.parseInt(args[4]);
			debugLevel = Integer.parseInt(args[5]);

			if(!(args[5].equals("0") || args[5].equals("1")))
			{
				System.err.println("<Debug Level>: Input argument coule be parsed as a 0 or 1 \n");
				return;
			}

			if(!(args.length == 6))
			{
				System.err.println("Usage: java GeneBankCreateBTree <0/1 (no/with Cache)> <degree> <gbk file> <sequence length> [<cache size>] [<debug level>] \n");
				return;
			}
		}
		else if(args[0].equals("0"))
		{
			hasCache = false;
			degree = Integer.parseInt(args[1]);
			gbkFile = args[2];
			seqLen = Integer.parseInt(args[3]);
			debugLevel = Integer.parseInt(args[4]);

			if(degree == 0){
				degree = 127; //calculated optimum degree
			}

			if(gbkFile == null){
				System.err.println("Please check your gbk file name and retry.");
				return;
			}

			if(!(args[4].equals("0") || args[4].equals("1")))
			{
				System.err.println("<Debug Level>: Input argument should be parsed as a 0 or 1 \n");

				if(args[4].equals("0"))
				{
					System.err.println("Debug level should be 1 if you want to see the output in dump file");
				}
				return;
			}

			if(!(args.length == 5))
			{
				System.err.println("Usage: java GeneBankCreateBTree <0/1 (no/with Cache)> <degree> <gbk file> <sequence length> [<debug level>] \n");
				return;
			}
		}
		else if(!(args[0].equals("0") || args[0].equals("1")))
		{
			System.err.println("<0/1 (no/with Cache)>: Input argument coule be parsed as a 0 or 1 \n");
			return;
		}

		if (Integer.parseInt(args[3]) < 1 || 31 < Integer.parseInt(args[3]))
		{
			System.err.println("<Sequence Length>: The sequence lenth must be between 1 and 31 (inclusive) \n");
			return;
		}

		//starting b tree construction
		BTree b = new BTree(degree, gbkFile+".btree.data."+seqLen+"."+degree);
		BTreeNode bNode = new BTreeNode(degree);

		File file = new File(gbkFile);
		if(!file.exists()){
			System.err.println("Please check your gbk file and retry");
		}

		Scanner scan = new Scanner(file);
		Queue<String> q = new LinkedList<String>();
		StringBuilder strbld = new StringBuilder();
		String qstring;
		String randStr = "b";
		String geneData ="";
		String binaryString = "";
		ArrayList<String> subseq = null;


		//start reading the gbk file
		while(scan.hasNext()) {
			String str = scan.next();
			//the sequence information is between ORIGIN and //.
			while (!str.equals("ORIGIN") && scan.hasNext()) {
				if (scan.hasNext()) {
					str = scan.next();
				}
			}
			while(!str.equals("//") && scan.hasNext()) {

				if( scan.hasNextLine()) {
					str = scan.nextLine();
				}
				StringTokenizer strtkn = new StringTokenizer(str);
				if(strtkn.hasMoreTokens()) {
					strtkn.nextToken();
				}
				while(strtkn.hasMoreTokens() ) {
					randStr = strtkn.nextToken();
					//add the sequence to the queue
					for (int j =0; j < randStr.length(); j++) {
						q.add(Character.toString(randStr.charAt(j)));
						GeneBankConvert gbc= new GeneBankConvert();
						qstring = q.toString();
						qstring = qstring.replaceAll(" ","");
						qstring = qstring.replaceAll(",","");
						qstring = qstring.substring(1,qstring.length()-1);	
						strbld.append(q);
						if (qstring.length() == seqLen) {							

							for(int k = 0; k < qstring.length(); k++) {
								if(qstring.charAt(k) == 'a') {
									binaryString += "00";
								}
								if(qstring.charAt(k) == 'c') {
									binaryString += "01";
								}
								if(qstring.charAt(k) == 'g') {
									binaryString += "10";
								}
								if(qstring.charAt(k) == 't') {
									binaryString += "11";
								}
							}
							q.poll();
						}
					}

				}
				q.clear();
			}

		}
		//reading / parsing sequences as a long value and insert to the b tree.
		long data = 0;
		int c = 0;
		int d = seqLen*2;
		while(c != binaryString.length()) {
			data = Long.parseLong(binaryString.substring(c,d+c), 2);
			TreeObject newObj = new TreeObject(data, 1);
			b.bTreeInsert(newObj);
			c += seqLen*2;
		}

		scan.close();

		//Creating a dump file, in order traversal order.
		if(debugLevel == 1) {
			File dumpFile = new File("dump");
			dumpFile.delete();
			dumpFile.createNewFile();
			FileWriter fw = new FileWriter(dumpFile);
			b.inOrderTraversal(b.root, seqLen, fw);
			fw.close();
		} 	
		b.bTreeClose();
	}
}