package treelang.mutate;

import treelang.picture.TPicture;

public class MSimple implements MExpression {

	private final Integer value;
	
	public MSimple(TPicture value) {
		super();
		this.value = value.hashCode();
	}

	@Override
	public boolean matches(TPicture tpic) {
		return (tpic.hashCode()==value.hashCode());
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
