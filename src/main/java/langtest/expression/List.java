package langtest.expression;

import java.util.ArrayList;

public class List extends Expression {
	private ArrayList<Expression> content;

	public List(ArrayList<Expression> content) {
		super();
		this.content = content;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.addAll(content);
		return res;
	}

	public ArrayList<Expression> getContent() {
		return content;
	}
	
	
}
