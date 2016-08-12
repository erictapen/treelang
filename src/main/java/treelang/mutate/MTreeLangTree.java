package treelang.mutate;

import java.util.HashMap;

import treelang.TStorage;
import treelang.picture.TPicture;

/**
 * Simple expression for a MRule which contains only a valid treelang
 * expression. This means, that it cannot have other children of type
 * {@link MExpression}.
 * 
 * @author justin
 *
 */
public class MTreeLangTree implements MExpression {

	/**
	 * {@link TPicture} reference
	 * 
	 */
	private final Integer value;

	public MTreeLangTree(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public MExpression[] getChildren() {
		return new MTreeLangTree[0];
	}

	@Override
	public boolean matches(Integer tpic) {
		return (tpic == value);
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer getTpicture(Integer result, HashMap<String, Integer> vars) {
		return value;
	}

	public String toString() {
		return TStorage.gI().get(value).toString();
	}

}
