
/**
 * An node of multiple operation * and /
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeMulop extends Node 
{

	private String mulop;

	/**
	 * A basic constructor
	 * 
	 * @param pos
	 * @param mulop
	 */
	public NodeMulop(int pos, String mulop) 
	{
		this.pos=pos;
		this.mulop=mulop;
	}

	/**
	 * Perform a multiplication and a division
	 * 
	 * @param o1
	 * @param o2
	 * @return o1*o2; o1/o2
	 * @throws EvalException
	 */
	public double op(double o1, double o2) throws EvalException 
	{
		if (mulop.equals("*"))
		{
			return o1*o2;
		}
		if (mulop.equals("/"))
		{
			return o1/o2;
		}
		throw new EvalException(pos,"bogus mulop: "+mulop);
	}

}
