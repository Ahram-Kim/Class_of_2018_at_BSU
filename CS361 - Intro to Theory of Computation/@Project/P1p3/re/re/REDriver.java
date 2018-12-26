package re;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import fa.dfa.DFA;
import fa.nfa.NFA;

/**
 * Nov 18, 2016
 * The class reads the input file and builds an RegEx from it.
 * Next it reads a string from the same file and prints "yes" if the string 
 * is in the RegEx's language or "not if the string is not in the RegEx's language.
 * @author elenasherman
 *
 */
public class REDriver {

	/**
	 * @param args the file name
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//The file name is passed as an argument
				String fileName = args[0];
				File file = new File(fileName);
				if(file.exists()){
					Scanner scan = new Scanner(file);
					//the first line is the set of final states
					//get the string of the final states and split it on a space
					String regEx = scan.nextLine().trim();
					REInterface re = new RE(regEx);
					NFA nfa = re.getNFA();
					//now process the strings
					DFA dfa = nfa.getDFA();
					while(scan.hasNext()){
						boolean accept = dfa.accepts(scan.nextLine());
						System.out.println(accept?"yes":"no");
					}
					scan.close();
				} else {
					System.out.println("Cannot find file " + fileName);
				}

	}

}
