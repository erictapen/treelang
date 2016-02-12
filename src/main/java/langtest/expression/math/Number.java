package langtest.expression.math;

public class Number extends Math {
	private int value;

	public Number(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return this.value;
	};

	public int eval() {
		return this.value;
	}
	
	public boolean isValid() {
		return true;
	}
	
	public boolean allBound() {
		return true;
	}

	public String toString() {
		return new Integer(this.value).toString();
	}
	
	
}
