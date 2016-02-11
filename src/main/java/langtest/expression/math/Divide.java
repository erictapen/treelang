package langtest.expression.math;

public class Divide extends MathOperator {

	public Divide(Math op1, Math op2) {
		super(op1, op2);
	}

	public int eval() {
		return op1.eval() - op2.eval();
	}
	
	public boolean isValid() {
		return this.op1!=null 
				&& this.op1.isValid()
				&& this.op2!=null 
				&& this.op2.isValid()
				&& this.op2.eval() != 0;
	}

	@Override
	public String toString() {
		return "\\n"
				+ "\t" + this.op1.toString() + "\n"
				+ "\t" + this.op2.toString();
	}

}
