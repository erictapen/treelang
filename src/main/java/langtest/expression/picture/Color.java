package langtest.expression.picture;

import java.util.ArrayList;

import langtest.expression.Expression;
import langtest.expression.math.Number;
import processing.core.PApplet;

public class Color extends Picture {
	private Number red;
	private Number green;
	private Number blue;
	
	public Color(Number red, Number green, Number blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(red);
		res.add(green);
		res.add(blue);
		return res;
	}
	
	
	
	public Number getRed() {
		return red;
	}



	public Number getGreen() {
		return green;
	}



	public Number getBlue() {
		return blue;
	}



	public void draw(PApplet p) {
		p.background((float) red.eval(), 
				(float) green.eval(), 
				(float) blue.eval());
		System.out.println("bg");
	}
	
	public boolean isValid() {
		return this.red!=null && this.green!=null && this.blue!=null;
	}
	
	public boolean allBound() {
		return this.red!=null && this.green!=null && this.blue!=null;
	}

	@Override
	public String toString() {
		return "color\n"
				+ "\t" + this.red.toString() + "\n"
				+ "\t" + this.green.toString() + "\n"
				+ "\t" + this.blue.toString();
	}
	
	
}
