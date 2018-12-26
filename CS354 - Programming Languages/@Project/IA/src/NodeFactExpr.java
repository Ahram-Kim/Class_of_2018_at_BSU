
/**
 * An node for the fact of the gramar containing an expression
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeFactExpr extends NodeFact 
{
	private NodeExpr expr;

	/**
	 * Create the expression with the given name from the 
	 * data file located.
	 * 
	 * @param expr
	 */
	public NodeFactExpr(NodeExpr expr) {
		this.expr=expr;
	}

	/**
	 * Evaluate the fact expression
	 * 
	 * @return expr.eval(env)
	 */
	public double eval(Environment env) throws EvalException 
	{
		return expr.eval(env);
	}

}
