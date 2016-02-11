package langtest.expression;

import java.util.ArrayList;

public class Lambda extends Expression {
	private ArrayList<Binding> variables;
	private Expression expression;
	

	
	public boolean isValid() {
		return this.expression!=null && this.expression.isValid();
	}
	
	public boolean allBound() {
		return this.expression!=null && this.expression.allBound();
	}
}
