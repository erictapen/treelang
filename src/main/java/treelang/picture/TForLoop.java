package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.mutate.MExpression;

public final class TForLoop implements TPicture {

	/** ident var expr
	 * 
	 */
	private final int ARGUMENT_COUNT = 3;
	private final Integer[] args;

	private static int hashInit = 976;
	private final int hash;

	public TForLoop(TPicture ident, TPicture var, TPicture expr) {
		this.args = new Integer[ARGUMENT_COUNT];
		this.args[0] = new Integer(ident.hashCode());
		TStorage.gI().put(this.args[0], ident);
		this.args[1] = new Integer(var.hashCode());
		TStorage.gI().put(this.args[1], var);
		this.args[2] = new Integer(expr.hashCode());
		TStorage.gI().put(this.args[2], expr);
		int hash = hashInit;
		hash = 37 * hash + this.args[0];
		hash = 37 * hash + this.args[1];
		hash = 37 * hash + this.args[2];
		this.hash = hash;
	}

	public TPicture getIdent() {
		return TStorage.gI().get(args[0]);
	}

	public TPicture getVar() {
		return TStorage.gI().get(args[1]);
	}

	public TPicture getExpr() {
		return TStorage.gI().get(args[2]);
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
	public Integer[] getArgs() {
		return args;
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
		Integer newVar = TStorage.gI().get(this.args[1]).replaceAll(identifier, expression);
		if (newVar != this.args[1])
			needsUnLambda = true;
		Integer newExpr = TStorage.gI().get(this.args[2]).replaceAll(identifier, expression);
		if (newExpr != this.args[2])
			needsUnLambda = true;
		if (needsUnLambda) {
			TPicture newNode = new TForLoop(TStorage.gI().get(args[0]), TStorage.gI().get(newVar), TStorage.gI().get(newExpr));
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
	public ArrayList<Stack<Byte>> findMatches(MExpression expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		ArrayList<TPicture> newNodes = new ArrayList<TPicture>();
		int amount = TStorage.gI().get(this.args[1]).getNumber().getValue();
		for (int i = amount; i >= 0; i--)
			newNodes.add(new TLambda(TStorage.gI().get(args[0]), new TNumber(i), TStorage.gI().get(args[2])));
		(new TList(newNodes)).draw(p);
	}

	@Override
	public String toString() {
		String res = "For";
		res += "\n\t" + getIdent().toString();
		res += "\n\t" + getVar().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getExpr().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
