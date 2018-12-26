// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

/**
 * 
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class Token 
{
	private String token;
	private String lexeme;

	/**
	 * Basic constructor
	 * Create the token and lexeme with the given name
	 * 
	 * @param token
	 * @param lexeme
	 */
	public Token(String token, String lexeme) 
	{
		this.token=token;
		this.lexeme=lexeme;
	}

	/**
	 * Basic constructor
	 * Create the token with the given value and name.
	 * @param token
	 */
	public Token(String token) 
	{
		this(token,token);
	}

	/**
	 * Return the token
	 * 
	 * @return token
	 */
	public String tok() 
	{
		return token; 
	}
	
	/**
	 * Return the lexeme
	 * 
	 * @return lexeme
	 */
	public String lex()
	{ 
		return lexeme; 
	}

	/**
	 * Examine and return it when token is equal to t.token.
	 * 
	 * @param t
	 * @return token.equals(t.token)
	 */
	public boolean equals(Token t) 
	{
		return token.equals(t.token);
	}

	/**
	 * The message to show the token and lexeme
	 * 
	 * @return "<"+tok()+","+lex()+">"
	 */
	public String toString() 
	{
		return "<"+tok()+","+lex()+">";
	}

}
