package langtest.expression;

import java.util.ArrayList;

import langtest.expression.math.MathExpression;
import langtest.expression.math.Number;
import langtest.expression.math.Plus;

public class Get extends Expression {
	private MathExpression index;
	private List list;
	
	public Get(MathExpression index, List list) {
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

	public MathExpression getIndex() {
		return index;
	}

	public List getList() {
		return list;
	}
	
	public void mutate() {
		Number n;
		if(Math.random() > 0.5) n = new Number(1);
		else n = new Number(-1);
		this.index = new Plus(this.index, n);
	}
	
	
}
