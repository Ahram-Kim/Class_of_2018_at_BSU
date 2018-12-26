
/**
 * An node for add operation containing a plus and minus sign
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeBoolExpr 
{
	private final NodeExpr leftSide;
	private final NodeRelop relOperator;
	private final NodeExpr rightSide;
	
	/**
	 * A constructor 
	 * 
	 * @param leftSide
	 * @param relOperator
	 * @param rightSide
	 */
	public NodeBoolExpr(NodeExpr leftSide, NodeRelop relOperator, NodeExpr rightSide)
	{
		this.leftSide = leftSide;
		this.relOperator = relOperator;
		this.rightSide = rightSide;
	}
	
	/**
	 * Evaluates the boolean expression
	 * 
	 * @param env
	 * @return
	 * @throws EvalException
	 */
	public boolean eval(Environment env) throws EvalException
	{
//		double leftVal = leftSide.eval(env);
//		double rightVal = rightSide.eval(env);
//		
//		if(relOperator.equals("=="))
//		{
//			return leftVal == rightVal;
//		}
//		if(relOperator.equals("!="))
//		{
//			return leftVal != rightVal;
//		}
//		if(relOperator.equals("<="))
//		{
//			return leftVal <= rightVal;
//		}
//		if(relOperator.equals(">="))
//		{
//			return leftVal >= rightVal;
//		}
//		if(relOperator.equals("<"))
//		{
//			return leftVal < rightVal;
//		}
//		if(relOperator.equals(">"))
//		{
//			return leftVal > rightVal;
//		}
		return relOperator.op(leftSide.eval(env), rightSide.eval(env));
	}
}
