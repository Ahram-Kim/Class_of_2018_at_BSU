
/**
 * An node containing terms, add operations, expressions.
 * 
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeExpr extends Node 
{
	private NodeTerm term;
	private NodeAddop addop;
	private NodeExpr expr;

	/**
	 * A basic constructor
	 * 
	 * @param term
	 * @param addop
	 * @param expr
	 */
	public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) 
	{
		this.term=term;
		this.addop=addop;
		this.expr=expr;
	}

	/**
	 * Allow an expression to have another expression
	 * @param expr
	 */
	public void append(NodeExpr expr)
	{
		if (this.expr==null) 
		{
			this.addop=expr.addop;
			this.expr=expr;
			expr.addop=null;
		} 
		else
		{
			this.expr.append(expr);
		}
	}

	/**
	 * Evaluate the expression
	 * 
	 * @return expr==null
	 */
	public double eval(Environment env) throws EvalException {
		return expr==null
				? term.eval(env)
						: addop.op(expr.eval(env),term.eval(env));
	}

}
