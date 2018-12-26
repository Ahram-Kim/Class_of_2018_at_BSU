import java.util.LinkedList;
import java.util.List;

/**
 * Evaluate the Exception when occur the error.
 *
 * @class CS354-001
 * @date October 26, 2017
 * @author Ahram Kim
 */
public class NodeBlock extends NodeStmt
{
	private NodeStmt stmt;
	private NodeBlock block;
	
	/**
	 * A basic constructor
	 * 
	 * @param statements
	 */
	public NodeBlock(NodeStmt stmt, NodeBlock block) 
	{
		this.stmt = stmt;
		this.block = block;
	}
	
	/**
	 * Evaluates the block statement
	 * 
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException
	{
		return stmt.eval(env);
	}
}
