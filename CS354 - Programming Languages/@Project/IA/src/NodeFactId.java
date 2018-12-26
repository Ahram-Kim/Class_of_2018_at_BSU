
/**
 * An node for the fact containing an identifier
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeFactId extends NodeFact 
{
	private String id;

	/**
	 * A basic contructor
	 * 
	 * @param pos
	 * @param id
	 */
	public NodeFactId(int pos, String id) 
	{
		this.pos=pos;
		this.id=id;
	}

	/**
	 * Evaluate the fact id by getting their position from the environment
	 * 
	 * @return env.get(pos,id)
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException 
	{
		return env.get(pos,id);
	}
}
