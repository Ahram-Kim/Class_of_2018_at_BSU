// This class is a scanner for the program
// and programming language being interpreted.

import java.util.*;

/**
 * This is a class for a scanner for the program.
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class Scanner 
{
	private String program;	// source program being interpreted
	private int pos;		// index of next char in program
	private Token token;	// last/current scanned token

	// sets of various characters and lexemes
	private Set<String> whitespace=new HashSet<String>();
	private Set<String> digits=new HashSet<String>();
	private Set<String> letters=new HashSet<String>();
	private Set<String> legits=new HashSet<String>();
	private Set<String> keywords=new HashSet<String>();
	private Set<String> operators=new HashSet<String>();
	private Set<String> comments = new HashSet<String>();
	private Set<String> reloperators = new HashSet<String>();

	// initializers for previous sets

	/**
	 * Fill the chars digits in for range
	 * @param s
	 * @param lo
	 * @param hi
	 */
	private void fill(Set<String> s, char lo, char hi) 
	{
		for (char c=lo; c<=hi; c++)
			s.add(c+"");
	}    

	/**
	 * Fills the set by the scanner to determine whitespace characters
	 * 
	 * @param s
	 */
	private void initWhitespace(Set<String> s) 
	{
		s.add(" ");
		s.add("\n");
		s.add("\t");
	}

	/**
	 * Fills the set by the scanner to determine digits
	 * 
	 * @param s
	 */
	private void initDigits(Set<String> s) {
		fill(s,'0','9');
		s.add(".");
	}

	/**
	 * Fills the set by the scanner to determine letters
	 * 
	 * @param s
	 */
	private void initLetters(Set<String> s) {
		fill(s,'A','Z');
		fill(s,'a','z');
	}

	/**
	 * Fills the set by the scanner to determine operands
	 * 
	 * @param s
	 */
	private void initLegits(Set<String> s) {
		s.addAll(letters);
		s.addAll(digits);
	}

	/**
	 * Fills the set by the scanner to determine operators
	 * 
	 * @param s
	 */
	private void initOperators(Set<String> s) {
		s.add("=");
		s.add("+");
		s.add("-");
		s.add("*");
		s.add("/");
		s.add("(");
		s.add(")");
		//s.add(">");
		//s.add("<");
	}

	/**
	 * Fills the set by the scanner to determine relational operators
	 * 
	 * @param s 
	 */
	private void initReloperators(Set<String> s)
	{
		s.add("<");
		s.add(">");
		s.add("<=");
		s.add(">=");
		s.add("<>");
		s.add("==");
	}
	
	/**
	 * Fills the set by the scanner to determine keywords
	 * 
	 * @param s
	 */
	private void initKeywords(Set<String> s) 
	{
		s.add("rd");
		s.add("wr");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
	}

	/**
	 * Fills the set by the scanner to determine comments
	 * 
	 * @param s
	 */
	private void initComments(Set<String> s)
	{
		s.add("%");
	}

	// constructor:
	//   - squirrel-away source program
	//   - initialize sets
	/**
	 * Take the input to scan through and to generate tokens from, 
	 * by the scan to determine their location while scanning.
	 * 
	 * @param program
	 */
	public Scanner(String program) 
	{
		this.program=program;
		pos=0;
		token=null;
		initWhitespace(whitespace);
		initDigits(digits);
		initLetters(letters);
		initLegits(legits);
		initKeywords(keywords);
		initOperators(operators);
		initComments(comments);
		initReloperators(reloperators);
	}

	// handy string-processing methods

	/**
	 * Notify the scanner if it scanned the whole program
	 * 
	 * @return pos >= program.length()
	 */
	public boolean done() 
	{
		return pos>=program.length();
	}

	/**
	 * Find to form the largest, valid, meaningful token
	 * 
	 * @param s
	 */
	private void many(Set<String> s) 
	{
		while (!done() && s.contains(program.charAt(pos)+""))
			pos++;
	}

	/**
	 * Determine whether it is gone past a char
	 * 
	 * @param c
	 */
	private void past(char c) 
	{
		while (!done() && c!=program.charAt(pos))
		{
			pos++;
		}
		if (!done() && c==program.charAt(pos))
		{
			pos++;
		}
	}

	// scan various kinds of lexeme

	/**
	 * Scan the next number
	 */
	private void nextNumber()
	{
		int old=pos;
		many(digits);
		token=new Token("num",program.substring(old,pos));
	}

	/**
	 * Scan the next keyword id
	 */
	private void nextKwId() 
	{
		int old=pos;
		many(letters);
		many(legits);
		String lexeme=program.substring(old,pos);
		token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme);
	}

	/**
	 * Scan the next operator
	 */
	private void nextOp() 
	{
		int old=pos;
		pos=old+2;
		
		if (!done()) 
		{
			String lexeme=program.substring(old,pos);
			
			if (operators.contains(lexeme) || /*TODO*/ reloperators.contains(lexeme)) 
			{
				token=new Token(lexeme); // two-char operator
				return;
			}
		}
		pos=old+1;
		String lexeme=program.substring(old,pos);
		token=new Token(lexeme); // one-char operator
	}
	
	/**
	 * Scan the next comments
	 */
	private void nextComments()
	{
		int old = pos;
		pos = old + 1;
		past('%');
	}

	/**
	 * Scan through each character in the input
	 * 
	 * @return true
	 */
	public boolean next() 
	{
		if (done())
		{
			return false;
		}
		many(whitespace);
		String c=program.charAt(pos)+"";
		
		if (digits.contains(c))
		{
			nextNumber();
		}
		else if (letters.contains(c))
		{
			nextKwId();
		}
		else if (operators.contains(c))
		{
			nextOp();
		}
		else if(comments.contains("%"))
		{
			nextComments();
		}
		else
		{
			System.err.println("illegal character at position "+pos);
			pos++;
			return next();
		}
		return true;
	}
	
	// This method scans the next lexeme,
	// if the current token is the expected token.
	/**
	 * Match the current token
	 * 
	 * @param t
	 * @throws SyntaxException
	 */
	public void match(Token t) throws SyntaxException 
	{
		if (!t.equals(curr()))
		{
			throw new SyntaxException(pos,t,curr());
		}
		next();
	}

	/**
	 * @return token
	 * @throws SyntaxException
	 */
	public Token curr() throws SyntaxException 
	{
		if (token==null)
		{
			throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
		}
		return token;
	}

	/**
	 * Return the value of pos
	 * @return pos
	 */
	public int pos() 
	{ 
		return pos; 
	}

	// for unit testing
	/**
	 * This is for unit testing.
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			Scanner scanner=new Scanner(args[0]);
			while (scanner.next())
			{
				System.out.println(scanner.curr());
			}
		} 
		catch (SyntaxException e) 
		{
			System.err.println(e);
		}
	}

}
