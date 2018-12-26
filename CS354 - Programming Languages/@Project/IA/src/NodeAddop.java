
/**
 * An node for add operation containing a plus and minus sign
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeAddop extends Node 
{
	private String addop;

	/**
	 * A basic constructor
	 * 
	 * @param pos
	 * @param addop
	 */
	public NodeAddop(int pos, String addop) 
	{
		this.pos=pos;
		this.addop=addop;
	}

	/**
	 * Perform an addition and subtraction.
	 * 
	 * @param d
	 * @param e
	 * @throws EvalException
	 */
	public double op(double d, double e) throws EvalException 
	{
		if (addop.equals("+"))
		{
			return d+e;			
		}
		if (addop.equals("-"))
		{
			return d-e;
		}
		throw new EvalException(pos,"bogus addop: "+addop);
	}
}

