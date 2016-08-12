package treelang.mutate;

import java.util.HashMap;

import treelang.TStorage;
import treelang.picture.TPicture;

/**
 * Eine Wildcard ist ein beliebiger Treelang Ausdruck.
 * 
 * @author justin
 *
 */
public class MWildcard implements MExpression {

	private final String name;
	private final MExpression[] children;

	public MWildcard(String name, MExpression[] children) {
		this.name = name;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	@Override
	public MExpression[] getChildren() {
		return children;
	}

	@Override
	public boolean matches(Integer tpic) {
		boolean result = true;
		Integer[] tpicChilds = TStorage.gI().get(tpic).getArgs();
		for (int i = 0; i < tpicChilds.length; i++) {
			if (!children[i].matches(tpicChilds[i]))
				result = false;
		}
		return result;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTpicture(Integer result, HashMap<String, Integer> vars) {
		return vars.get(this.name);
	}

}
