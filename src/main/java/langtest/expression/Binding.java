package langtest.expression;

import java.util.ArrayList;

public class Binding extends Expression {
	private String identifier;
	private Expression value;
	
	public Binding(String identifier, Expression value) {
		super();
		this.identifier = identifier;
		this.value = value;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(value);
		return res;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Expression getValue() {
		return value;
	}
	
	public boolean isValid() {
		return this.value!=null && this.value.isValid();
	}
	
	public boolean allBound() {
		return this.value!=null && this.value.allBound();
	}

	@Override
	public String toString() {
		String res = "define " + this.identifier + "\n";
		for(String x : this.value.toString().split("\n")) {
			res += "\t" + x + "\n";
		}
		return res;
	}
	
	public void mutate() {
		
	}
	
	
}
