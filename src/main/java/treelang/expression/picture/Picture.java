package treelang.expression.picture;

import java.util.ArrayList;

import treelang.expression.Expression;
import treelang.expression.TypeException;
import processing.core.PApplet;

public class Picture extends Expression {
	private ArrayList<Expression> items;
	
	public void draw(PApplet p) throws TypeException {
		for(Expression x : items) x.draw(p);
	}
	
	public boolean isValid() {
		boolean res = true;
		for(Expression x : this.items) res = res && x.isValid();
		return res;
	}
	
	public boolean allBound() {
		boolean res = true;
		for(Expression x : this.items) res = res && x.allBound();
		return res;
	}
}
