
/**
 * Present the node of term.
 *
 * @class CS354-001
 * @date October 3, 2017
 * @author Ahram Kim
 */
public class NodeTerm extends Node {

	private NodeFact fact;
	private NodeMulop mulop;
	private NodeTerm term;

	/**
	 * A basic constructor
	 * 
	 * @param fact
	 * @param mulop
	 * @param term
	 */
	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) 
	{
		this.fact=fact;
		this.mulop=mulop;
		this.term=term;
	}

	/**
	 * Allow a term to contain another term
	 * 
	 * @param term
	 */
	public void append(NodeTerm term) 
	{
		if (this.term==null) 
		{
			this.mulop=term.mulop;
			this.term=term;
			term.mulop=null;
		} 
		else
		{
			this.term.append(term);
		}
	}
	
	/**
	 * Perform the multiplication and division
	 */
	public double eval(Environment env) throws EvalException 
	{
		return term==null
				? fact.eval(env)
				: mulop.op(term.eval(env),fact.eval(env));
	}

}
