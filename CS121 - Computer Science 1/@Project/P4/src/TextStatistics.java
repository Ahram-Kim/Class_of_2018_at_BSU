import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * @author Ahram Kim
 *
 */
public class TextStatistics implements TextStatisticsInterface {

	private int numChars;
	private int numWords = 0;
	private int numLines = 0;
	int[] letterFreq = new int [26];
	private int[] wordFreq = new int [24];
	private double avgWordLength;
	private int totalLength;
	private String fileName = "";
	private final String punc = ".;:?!";
	private static final String DELIMITERS = "[\\W\\d_]+";
	private static final int ERROR_CODE = 0;
	
	public TextStatistics(File file) {
		
		try {
			Scanner scanner = new Scanner(file);
			fileName = file.getName();
			
			while(scanner.hasNextLine()) {
				 String line = scanner.nextLine(); 
				 
				 numChars+=line.length() + 1;
				 
				 Scanner tokenizer = new Scanner(line).useDelimiter(DELIMITERS);
				 numLines++;
				 
				 while(tokenizer.hasNext()) {
					 
					 String word = tokenizer.next();
					 wordFreq[word.length()]++;
					 numWords++;
					 word = word.toLowerCase();
					 
					 for(int i=0; i< word.length(); i++) {
						 char letter = word.charAt(i);
						 
						 if(letter >= 'a' && letter <= 'z') {
							 letterFreq[letter - 'a']++ ;
						 
						 }
					 }			 
				 }
				 tokenizer.close();
			}
			scanner.close();
			
			double average = 0;
			for(int i=0; i<wordFreq.length; i++) {
				average = average + (i * wordFreq[i]);
			}
			
			avgWordLength = average / numWords;
			
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path: " + e);
			System.out.println(e.getMessage());
			System.exit(ERROR_CODE);
		}
	
	}
	
	@Override
	public int getCharCount() {
		// TODO Auto-generated method stub
		return numChars;
	}

	@Override
	public int getWordCount() {
		// TODO Auto-generated method stub
		return numWords;
	}

	@Override
	public int getLineCount() {
		// TODO Auto-generated method stub
		return numLines;
	}

	@Override
	public int[] getLetterCount() {
		// TODO Auto-generated method stub
		return letterFreq;
	}

	@Override
	public int[] getWordLengthCount() {
		// TODO Auto-generated method stub
		return wordFreq;
	}

	@Override
	public double getAverageWordLength() {
		// TODO Auto-generated method stub
		return avgWordLength;
	}

	public String toString() {
		
		String s = "statistics for " + fileName + "\n";
		s += "==========================================================\n";
		s += numLines + " lines\n";
		s += numWords + " words\n";
		s += numChars + " characters\n";
		s += "------------------------------\n";
		
		for(int i=0; i<letterFreq.length/2; i++) {	
			s += (char)(i+'a') + " = " + letterFreq[i];
			s += "\t\t" + (char)(i+ 'n') + " = " + letterFreq[i+(letterFreq.length/2)] + "\n";
		} 
		
		
		s += "------------------------------\n";
		s += " length  frequency \n";
		s += " ------  ---------\n";
		
		
		for(int j=0; j<wordFreq.length; j++) {
			if(wordFreq[j] != 0) {
				s += "     " + String.format("%3d", j);
				s += "       " + String.format("%4d", wordFreq[j]) + "\n";
			}
		}
		
		s += "\n";
		s += String.format("Average word length = %.2f\n", getAverageWordLength());
		s += "==========================================================\n";
		
		return s;
	}
	
}
