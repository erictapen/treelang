package treelang.picture;

import java.util.ArrayList;

import processing.core.PApplet;
import treelang.TStorage;

public class TLambda implements TPicture {
	private String ident;
	private ArrayList<Integer> varOccurences = new ArrayList<Integer>();

	private Integer var;
	private Integer expr;

	public TLambda(String ident, TPicture var, TPicture expr) {
		this.ident = ident;
		this.var = new Integer(var.hashCode());
		TStorage.getInstance().put(this.var, var);
		this.expr = new Integer(expr.hashCode());
		TStorage.getInstance().put(this.expr, expr);
		// find occurences of identifier
		
	}

	public Integer getVar() {
		return var;
	}

	public Integer getExpr() {
		return expr;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public void draw(PApplet p) {

	}

	@Override
	public String toString() {
		String res = "Translate";
		res += "\n\t" + ident;
		res += "\n\t" + getVar().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getExpr().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
