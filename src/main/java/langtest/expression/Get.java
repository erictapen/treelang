package langtest.expression;

import java.util.ArrayList;

import langtest.expression.math.Math;

public class Get extends Expression {
	private Math index;
	private List list;
	
	public Get(Math index, List list) {
		super();
		this.index = index;
		this.list = list;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(index);
		res.add(list);
		return res;
	}

	public Math getIndex() {
		return index;
	}

	public List getList() {
		return list;
	}
	
	
}
