package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

/**
 * treelang representation of a lambda expression. This can be used as an
 * anonymous function and is useful for abstraction. For multiple use of the
 * same function, use another lambda expression and give it the hash of the
 * field expr. This way you can fake function names.
 * 
 * See also TForLoop for another type of abstraction.
 * 
 * @author justin
 *
 */
public class TLambda implements TPicture {

	/**
	 * Identifier, Variablevalue, Expression
	 * 
	 */
	private final int ARGUMENT_COUNT = 3;
	private final Integer[] args;

	private static int hashInit = 730;
	private final int hash;

	public TLambda(TPicture ident, TPicture var, TPicture expr) {

		this.args = new Integer[ARGUMENT_COUNT];
		this.args[0] = new Integer(ident.hashCode());
		TStorage.gI().putNode(this.args[0], ident);
		this.args[1] = new Integer(var.hashCode());
		TStorage.gI().putNode(this.args[1], var);
		this.args[2] = new Integer(expr.hashCode());
		TStorage.gI().putNode(this.args[2], expr);
		int hash = hashInit;
		hash = 37 * hash + ident.hashCode();
		hash = 37 * hash + this.args[1];
		hash = 37 * hash + this.args[2];
		this.hash = hash;
		TStorage.gI().put(this.hashCode(), this);
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

	@Override
	public TNumber getNumber() {
		Integer unfoldedLambda = getExpr().replaceAll(args[0], args[1]);
		return TStorage.gI().get(unfoldedLambda).getNumber();
	}

	@Override
	public Integer[] getArgs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash == identifier)
			return expression;
		boolean needsUnLambda = false;
		Integer newVar = TStorage.gI().get(this.args[1]).replaceAll(identifier, expression);
		if (newVar != this.args[1])
			needsUnLambda = true;
		Integer newExpr = TStorage.gI().get(this.args[2]).replaceAll(identifier, expression);
		if (newExpr != this.args[2])
			needsUnLambda = true;
		if (needsUnLambda) {
			TPicture newNode = new TLambda(TStorage.gI().get(args[0]), TStorage.gI().get(newVar),
					TStorage.gI().get(newExpr));
			Integer newHash = newNode.hashCode();
			TStorage.gI().putNode(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {
		Integer unfoldedLambda = getExpr().replaceAll(args[0], args[1]);
		TStorage.gI().get(unfoldedLambda).draw(p);
	}

	@Override
	public String toString() {
		String res = "Lambda";
		res += "\n\t" + getIdent();
		res += "\n\t" + getVar().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getExpr().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
