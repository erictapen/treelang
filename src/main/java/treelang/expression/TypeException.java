package treelang.expression;

public class TypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Expression expected;
	@SuppressWarnings("unused")
	private Expression found;
}
