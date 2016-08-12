package treelang.mutate;

import java.util.HashMap;

public interface MExpression {
	
	public MExpression[] getChildren();

	public boolean matches(Integer tpic);
	
	public Integer getValue();

	public Integer getTpicture(Integer result, HashMap<String, Integer> vars);
}
