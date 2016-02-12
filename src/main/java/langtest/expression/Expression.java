package langtest.expression;

import java.util.ArrayList;

import processing.core.PApplet;

public class Expression {
	
	public void draw(PApplet p) throws TypeException {
		
	}
	
	public ArrayList<Expression> getSubTree() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.addAll(this.getChildren());
		for(Expression x : this.getChildren()) {
			if(x != null) res.addAll(x.getSubTree());
		}
		return res;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		return res;
	}
	
	public boolean isValid() {
		return true;
	}
	
	public boolean allBound() {
		return true;
	}

	@Override
	public String toString() {
		return "";
	}
	
}
