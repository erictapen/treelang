package treelang.expression;

public class TypeException extends Exception {
	private Expression expected;
	private Expression found;
}
