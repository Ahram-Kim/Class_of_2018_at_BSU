
/**
 * An node for begin statements
 * 
 * @class CS354-001
 * @date October 26, 2017
 * @author Ahram Kim
 */
public class NodeStmtBegin extends NodeStmt 
{
	private NodeBlock block;

	/**
	 * A basic constructor
	 * 
	 * @param block
	 */
	public NodeStmtBegin(NodeBlock block) 
	{
		this.block = block;
	}

	/**
	 * Evaluate the block statement 
	 * 
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException
	{
		return block.eval(env);
	} 
}