
/**
 * An node for writing statements
 * 
 * @class CS354-001
 * @date October 26, 2017
 * @author Ahram Kim
 */
public class NodeStmtWr extends NodeStmt 
{
	private NodeExpr expr;
	
	/**
	 * A basic constructor
	 * 
	 * @param assn
	 */
	public NodeStmtWr(NodeExpr expr) 
	{
		this.expr = expr;
	}
	
	/**
	 * Evaluates to write statements
	 * 
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException
	{
		double a = expr.eval(env);
		System.out.println(a);
		return a;
	}
}
