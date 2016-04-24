package treelang.expression.picture;

import java.util.ArrayList;

import treelang.expression.Expression;
import treelang.expression.TypeException;
import treelang.expression.math.MathException;
import processing.core.PApplet;

public class Rectangle extends Picture {
	private Point p1;
	private Point p2;
	private Color color;
	
	public Rectangle(Point p1, Point p2, Color color) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
	}
	
	public ArrayList<Expression> getChildren() {
		ArrayList<Expression> res = new ArrayList<Expression>();
		res.add(p1);
		res.add(p2);
		res.add(color);
		return res;
	}
	
	public void draw(PApplet p) throws TypeException {
		try{
			p.fill((float) color.getRed().eval(),
					(float) color.getGreen().eval(),
					(float) color.getBlue().eval());
			p.stroke((float) color.getRed().eval(),
					(float) color.getGreen().eval(),
					(float) color.getBlue().eval());
			p.rect( p1.getX().eval(), 
					p1.getY().eval(), 
					p2.getX().eval(), 
					p2.getY().eval());
		} catch (MathException e) {
			throw new TypeException();
		}
	}

	@Override
	public String toString() {
		String res = "rectangle\n";
		String body = p1.toString() + "\n" + p2.toString() + "\n" + color.toString();
		for(String x : body.split("\n")) res += "\t" + x + "\n";
		return res;
	}
	
	
	
}
