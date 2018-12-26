
/**
 * An node containing a unary value/
 * 
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeFactUnaryMinus extends NodeFact 
{
	private NodeFact fact;

	/**
	 * A basic constructor
	 * 
	 * @param unaryMinus
	 */
	public NodeFactUnaryMinus(NodeFact fact) 
	{
		this.fact=fact;
	}

	/**
	 * Evaluates the unary value
	 * 
	 * @param env 
	 * @return fact.eval(env) * (-1) - return the number to the negative 
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException 
	{
		return fact.eval(env) * (-1);
	}
}
