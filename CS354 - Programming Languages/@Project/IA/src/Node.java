// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated.

/**
 * The basic class for all the nodes parsed by the parser
 * 
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public abstract class Node 
{
	protected int pos=0;

	/**
	 * Function which all nodes implement to perform is necessary 
	 * for their type
	 * 
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException 
	{
		throw new EvalException(pos,"cannot eval() node!");
	}
}
