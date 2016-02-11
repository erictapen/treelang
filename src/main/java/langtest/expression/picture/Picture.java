package langtest.expression.picture;

import java.util.ArrayList;

import langtest.expression.Expression;
import processing.core.PApplet;

public class Picture extends Expression {
	private ArrayList<Expression> items;
	
	public void draw(PApplet p) {
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
