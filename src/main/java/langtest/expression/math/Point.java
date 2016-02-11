package langtest.expression.math;

import java.util.ArrayList;

import langtest.expression.Expression;

public class Point extends Math {
	private Math x;
	private Math y;
	
	public Point(Math x, Math y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(x);
		res.add(y);
		return res;
	}
	
	public Math getX() {
		return x;
	}
	
	public Math getY() {
		return y;
	}

	@Override
	public String toString() {
		String res = "point\n";
		String body = this.x.toString() + "\n" + this.y.toString();
		for(String x : body.split("\n")) res += "\t" + x + "\n";
		return res;
	}
}
