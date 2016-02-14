package langtest.expression.math;

import java.util.ArrayList;

import langtest.expression.Expression;

public class MathBinding extends MathExpression {
	private String identifier;
	private MathExpression value;
	
	public MathBinding(String identifier, MathExpression value) {
		super();
		this.identifier = identifier;
		this.value = value;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(value);
		return res;
	}

	public int eval() throws MathException {
		return value.eval();
	}

	public String getIdentifier() {
		return identifier;
	}

	public MathExpression getValue() {
		return value;
	}
	
	public void setValue(MathExpression value) {
		this.value = value;
	}

	public boolean isValid() {
		return this.value!=null && this.value.isValid();
	}
	
	public boolean allBound() {
		return this.value!=null && this.value.allBound();
	}

	@Override
	public String toString() {
		if(value == null) return this.identifier;
		String res = "define " + this.identifier + "\n";
		for(String x : this.value.toString().split("\n")) {
			res += "\t" + x + "\n";
		}
		return res;
	}
	
	
}
