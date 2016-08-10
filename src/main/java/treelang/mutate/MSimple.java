package treelang.mutate;

import treelang.TStorage;
import treelang.picture.TPicture;

/** Simple expression for a MRule
 * @author justin
 *
 */
public class MSimple implements MExpression {

	/**
	 * {@link TPicture} reference
	 * 
	 */
	private final Integer value;
	
	public MSimple(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public boolean matches(TPicture tpic) {
		return (tpic.hashCode()==value.hashCode());
	}

	@Override
	public Integer getValue() {
		return value;
	}
	
	public String toString() {
		return TStorage.gI().get(value).toString();
	}

}
