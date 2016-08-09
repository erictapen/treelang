package treelang.mutate;

import treelang.picture.TPicture;

public interface MExpression {

	public boolean matches(TPicture tpic);
	
	public Integer getValue();
}
