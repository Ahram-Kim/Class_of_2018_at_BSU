
/**
 * An node for fact containing a number
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeFactNum extends NodeFact 
{
	private String num;

	/**
	 * A basic constructor
	 * 
	 * @param num
	 */
	public NodeFactNum(String num) 
	{
		this.num=num;
	}

	/**
	 * Evaluate the fact number
	 * 
	 * @return num.eval(env)
	 */
	public double eval(Environment env) throws EvalException
	{
		return Integer.parseInt(num);
	}
}
