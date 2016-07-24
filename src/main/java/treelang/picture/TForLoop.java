package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.TStorage;

public final class TForLoop implements TPicture {

	private final String ident;

	private final Integer var;
	private final Integer expr;

	private static int hashInit = 976;
	private final int hash;

	public TForLoop(String ident, TPicture var, TPicture expr) {
		this.ident = ident;
		this.var = new Integer(var.hashCode());
		TStorage.gI().put(this.var, var);
		this.expr = new Integer(expr.hashCode());
		TStorage.gI().put(this.expr, expr);
		int hash = hashInit;
		hash = 37 * hash + ident.hashCode();
		hash = 37 * hash + this.var;
		hash = 37 * hash + this.expr;
		this.hash = hash;
	}

	public TPicture getVar() {
		return TStorage.gI().get(var);
	}

	public TPicture getExpr() {
		return TStorage.gI().get(expr);
	}

	/*
	 * (non-Javadoc) At the moment, For only gives meaningful output for
	 * Pictures
	 * 
	 * @see treelang.picture.TPicture#getNumber()
	 */
	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash==identifier)
			return expression;
		boolean needsUnLambda = false;
		Integer newVar = TStorage.gI().get(this.var).replaceAll(identifier, expression);
		if (newVar != this.var)
			needsUnLambda = true;
		Integer newExpr = TStorage.gI().get(this.expr).replaceAll(identifier, expression);
		if (newExpr != this.expr)
			needsUnLambda = true;
		if (needsUnLambda) {
			TPicture newNode = new TForLoop(this.ident, TStorage.gI().get(newVar), TStorage.gI().get(newExpr));
			Integer newHash = newNode.hashCode();
			TStorage.gI().put(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public Integer replace(Integer origin, Integer target, Stack<Byte> dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stack<Byte>> match(Integer expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		ArrayList<TPicture> newNodes = new ArrayList<TPicture>();
		int amount = TStorage.gI().get(this.var).getNumber().getValue();
		for (int i = amount; i >= 0; i--)
			newNodes.add(new TLambda(ident, new TNumber(i), TStorage.gI().get(expr)));
		(new TList(newNodes)).draw(p);
	}

	@Override
	public String toString() {
		String res = "For";
		res += "\n\t" + ident;
		res += "\n\t" + getVar().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getExpr().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
