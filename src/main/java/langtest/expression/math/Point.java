package langtest.expression.math;

import java.util.ArrayList;

import processing.core.PApplet;
import langtest.expression.Expression;
import langtest.expression.TypeException;
import langtest.expression.picture.Picture;

public class Point extends Picture{
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
	
	public void draw(PApplet p) throws TypeException {
		try{
			p.fill(255, 255, 255);
			p.stroke(255, 255, 255);
			p.rect(x.eval(), y.eval(), 1, 1);
		} catch (MathException e) {
			throw new TypeException();
		}
	}
	
	public Math getX() {
		return x;
	}
	
	public Math getY() {
		return y;
	}

	public String toString() {
		String res = "point\n";
		String body = this.x.toString() + "\n" + this.y.toString();
		for(String x : body.split("\n")) res += "\t" + x + "\n";
		return res;
	}
}
