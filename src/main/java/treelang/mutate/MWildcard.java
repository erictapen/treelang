package treelang.mutate;

import treelang.TStorage;
import treelang.picture.TPicture;

public class MWildcard implements MExpression {
	
	private final String name;
	private final MExpression[] children;
	
	public MWildcard(String name, MExpression[] children) {
		this.name = name;
		this.children = children;
	}

	@Override
	public boolean matches(TPicture tpic) {
		boolean result = true;
		TPicture[] tpicChilds = TStorage.gI().getAll(tpic.getArgs());
		for (int i = 0; i < tpicChilds.length; i++) {
			if(!children[i].matches(tpicChilds[i]))
				result = false;
		}
		return result;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
