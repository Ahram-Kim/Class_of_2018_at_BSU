
/**
 * An node for if then statements
 * 
 * @class CS354-001
 * @date October 26, 2017
 * @author Ahram Kim
 */
public class NodeStmtIfThen extends NodeStmt 
{
	private NodeBoolExpr boolexpr;
	private NodeStmt ifThenStmt;
	
	/**
	 * A basic constructor
	 * 
	 * @param boolexpr
	 * @param ifThenStmt
	 */
	public NodeStmtIfThen(NodeBoolExpr boolexpr, NodeStmt ifThenStmt)
	{
		this.boolexpr = boolexpr;
		this.ifThenStmt = ifThenStmt;
	}
	
	/**
	 * Evaluate the if then statement 
	 * 
	 * @param env
	 * @throws EvalException
	 */
	public double eval(Environment env) throws EvalException
	{
//		if(boolexpr.eval(env))
//		{
//			return ifThenStmt.eval(env);
//		}
		return ifThenStmt.eval(env);
	} 
}
