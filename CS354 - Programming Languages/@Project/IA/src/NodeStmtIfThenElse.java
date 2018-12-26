
/**
 * /**
 * An node for if then else statements
 * 
 * @class CS354-001
 * @date October 26, 2017
 * @author Ahram Kim
 */
public class NodeStmtIfThenElse extends NodeStmt 
{
	private NodeBoolExpr boolexpr;
	private NodeStmt ifThenStmt;
	private NodeStmt elseStmt;
	
	/**
	 * A basic constructor
	 * 
	 * @param boolexpr
	 * @param ifThenStmt
	 * @param elseStmt
	 */
	public NodeStmtIfThenElse(NodeBoolExpr boolexpr, NodeStmt ifThenStmt, NodeStmt elseStmt)
	{
		this.boolexpr = boolexpr;
		this.ifThenStmt = ifThenStmt;
		this.elseStmt = elseStmt;
	}
	
	/**
	 * Evaluate the if then else statements
	 * 
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException
	{
		if(boolexpr.eval(env))
		{
			return ifThenStmt.eval(env);
		}
		else
		{
			return elseStmt.eval(env);
		}
	}
}
