public class NodeAssn extends Node {

    private String id;
    private NodeExpr expr;

    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }

    public int eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
