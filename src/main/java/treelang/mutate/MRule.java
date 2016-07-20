package treelang.mutate;

import treelang.picture.TPicture;

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
	
	public TPicture apply(TPicture tpic) {
		if(matches(tpic))
			return null;
		return tpic;
	}
	
}
