
/**
 * An node containing assignment of a statement of the grammar
 * 
 * @class CS354-001
 * @date October 26, 2017
 * @author Ahram Kim
 *
 */
public class NodeStmtAssn extends NodeStmt
{
	private String id;
	private NodeExpr expr;

	/**
	 * A basic constructor
	 * 
	 * @param id
	 * @param expr
	 */
	public NodeStmtAssn(String id, NodeExpr expr) 
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
