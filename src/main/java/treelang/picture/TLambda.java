package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

public class TLambda implements TPicture {
	private final String ident;

	private final Integer var;
	private final Integer expr;

	public TLambda(String ident, TPicture var, TPicture expr) {
		this.ident = ident;
		this.var = new Integer(var.hashCode());
		TStorage.gI().put(this.var, var);
		this.expr = new Integer(expr.hashCode());
		TStorage.gI().put(this.expr, expr);
	}

	public TPicture getVar() {
		return TStorage.gI().get(var);
	}

	public TPicture getExpr() {
		return TStorage.gI().get(expr);
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public Integer unLambda(String identifier, Integer expression) {
		boolean needsUnLambda = false;
		Integer newVar = TStorage.gI().get(this.var).unLambda(identifier, expression);
		if (newVar != this.var)
			needsUnLambda = true;
		Integer newExpr = TStorage.gI().get(this.expr).unLambda(identifier, expression);
		if (newExpr != this.expr)
			needsUnLambda = true;
		if (needsUnLambda) {
			TPicture newNode = new TLambda(this.ident, TStorage.gI().get(newVar),
					TStorage.gI().get(newExpr));
			Integer newHash = newNode.hashCode();
			TStorage.gI().put(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {
		Integer unfoldedLambda = getExpr().unLambda(ident, var);
		TStorage.gI().get(unfoldedLambda).draw(p);
	}

	@Override
	public String toString() {
		String res = "Lambda";
		res += "\n\t" + ident;
		res += "\n\t" + getVar().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getExpr().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
