package treelang.expression.math;

public class Modulo extends MathOperator {

	public Modulo(MathExpression op1, MathExpression op2) {
		super(op1, op2);
	}

	public int eval() throws MathException {
		if(op2.eval() == 0) throw new MathException();
		return op1.eval() % op2.eval();
	}

	@Override
	public String toString() {
		return "mod\n"
				+ "\t" + this.op1.toString() + "\n"
				+ "\t" + this.op2.toString();
	}
	

}
