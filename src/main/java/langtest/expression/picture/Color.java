package langtest.expression.picture;

import java.util.ArrayList;

import langtest.expression.Expression;
import langtest.expression.TypeException;
import langtest.expression.math.MathException;
import langtest.expression.math.Number;
import langtest.expression.math.Math;
import processing.core.PApplet;

public class Color extends Picture {
	private Math red;
	private Math green;
	private Math blue;
	
	public Color(Math red, Math green, Math blue) {
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
	
	
	
	public Math getRed() {
		return red;
	}



	public Math getGreen() {
		return green;
	}



	public Math getBlue() {
		return blue;
	}



	public void draw(PApplet p) throws TypeException {
		try {
			p.background((float) red.eval(), 
					(float) green.eval(), 
					(float) blue.eval());
		} catch (MathException e) {
			throw new TypeException();
		}
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
