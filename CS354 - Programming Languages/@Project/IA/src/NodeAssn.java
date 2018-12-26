
/**
 * 	A node for storing assignments
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeAssn extends Node 
{
	private String id;
	private NodeExpr expr;

	/**
	 * A basic constructor
	 * 
	 * @param id
	 * @param expr
	 */
	public NodeAssn(String id, NodeExpr expr) 
	{
		this.id=id;
		this.expr=expr;
	}

	/**
	 * Evaluate the given assignment
	 * 
	 * @return env.put(id, expr.eval(env))
	 */
	public double eval(Environment env) throws EvalException 
	{
		return env.put(id, expr.eval(env));
	}
}
