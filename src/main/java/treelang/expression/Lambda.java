package treelang.expression;

import java.util.ArrayList;

public class Lambda extends Expression {
	@SuppressWarnings("unused")
	private ArrayList<Binding> variables;
	private Expression expression;
	

	
	public boolean isValid() {
		return this.expression!=null && this.expression.isValid();
	}
	
	public boolean allBound() {
		return this.expression!=null && this.expression.allBound();
	}
}
