package treelang.mutate;

import java.util.ArrayList;
import java.util.Stack;

import treelang.TStorage;
import treelang.picture.TPicture;

/**
 * Template for every rule; one origin expression and one target.
 * 
 * @author justin
 *
 */
public class MRule {

	private final MExpression origin;
	private final MExpression target;

	public MRule(MExpression origin, MExpression target) {
		super();
		this.origin = origin;
		this.target = target;
	}

	public boolean matches(TPicture tpic) {
		return origin.matches(tpic);
	}

	public Integer apply(Integer tpic) {
		if (MSimple.class.equals(origin.getClass()) && MSimple.class.equals(target.getClass())) {
			return TStorage.gI().get(tpic).replaceAll(origin.getValue(), target.getValue());
		}
		// hier soll der Fall abgefangen werden, dass MWildcard als Regel
		// verwendet wird. MExpressions mit MWildcard matchen auf bestimmten
		// Ausdrücken, was sich mit MExpression.matches() herausfinden lässt.
		ArrayList<Stack<Byte>> matches = TStorage.gI().get(tpic).findMatches(origin);
		Integer result = null;
		for(Stack<Byte> x : matches) {
			result = TStorage.gI().get(tpic).replace(x, target.getValue());
		}
		return result;
	}

	public String toString() {
		return origin.toString() + "\n-->\n" + target.toString() + "\n";
	}

}
