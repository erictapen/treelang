package treelang.mutate;

import treelang.TStorage;
import treelang.picture.TPicture;

/** Template for every rule; one origin expression and one target. Rules are always invertable
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
		if(MSimple.class.equals(origin.getClass()) && MSimple.class.equals(target.getClass())) {
			return TStorage.gI().get(tpic).replaceAll(origin.getValue(), target.getValue());
		}
		return null;
	}
	
}
