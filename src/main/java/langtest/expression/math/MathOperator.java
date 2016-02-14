package langtest.expression.math;

import java.util.ArrayList;

import langtest.expression.Expression;

public abstract class MathOperator extends MathExpression {
	protected MathExpression op1;
	protected MathExpression op2;
	
	public MathOperator(MathExpression op1, MathExpression op2) {
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
	
	public void mutate() {
		int rand = (int) (Math.random() * 10);
		switch(rand) {
			case 0: op1 = new Plus(op1, new Number(1));
					break;
			case 1: op1 = new Plus(op1, new Number(-1));
					break;
			case 2: op1 = new Minus(op1, new Number(1));
					break;
			case 3: op1 = new Minus(op1, new Number(-1));
					break;
			case 4: op1 = new Multiply(op1, new Number(1));
					break;
			case 5: op1 = new Multiply(op1, new Number(-1));
					break;
			case 6: op1 = new Divide(op1, new Number(1));
					break;
			case 7: op1 = new Divide(op1, new Number(-1));
					break;
			case 8: op1 = new Modulo(op1, new Number(1));
					break;
			case 9: op1 = new Modulo(op1, new Number(-1));
					break;
		}
	}
}
