
/**
 * Evaluate the Exception when occur the error.
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class EvalException extends Exception 
{
	private int pos;
	private String msg;

	/**
	 * Basic constructor.
	 * A list of generic objects that should be added.
	 * 
	 * @param pos
	 * @param msg* 
	 */
	public EvalException(int pos, String msg) 
	{
		this.pos=pos;
		this.msg=msg;
	}

	/**
	 * The message of when errors shows.
	 */
	public String toString() 
	{
		return "eval error"
				+", pos="+pos
				+", "+msg;
	}
}
