package langtest.expression.math;

import java.util.ArrayList;

import langtest.expression.Expression;

public abstract class MathOperator extends Math {
	protected Math op1;
	protected Math op2;
	
	public MathOperator(Math op1, Math op2) {
		super();
		this.op1 = op1;
		this.op2 = op2;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(op1);
		res.add(op2);
		return res;
	}

	public boolean isValid() {
		return this.op1!=null 
				&& this.op1.isValid()
				&& this.op2!=null 
				&& this.op2.isValid();
	}
	
	public boolean allBound() {
		return this.op1!=null 
				&& this.op1.allBound()
				&& this.op2!=null 
				&& this.op2.allBound();
	}
}
