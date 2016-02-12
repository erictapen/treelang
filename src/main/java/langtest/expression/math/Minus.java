package langtest.expression.math;

public class Minus extends MathOperator {

	public Minus(Math op1, Math op2) {
		super(op1, op2);
	}

	public int eval() throws MathException {
		return op1.eval() - op2.eval();
	}

	@Override
	public String toString() {
		return "-\n"
				+ "\t" + this.op1.toString() + "\n"
				+ "\t" + this.op2.toString();
	}
	
	
	
}
