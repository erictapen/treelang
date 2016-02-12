package langtest;

import langtest.expression.*;
import langtest.expression.math.MathBinding;
import langtest.expression.math.Multiply;
import langtest.expression.math.Number;
import langtest.expression.math.Plus;
import langtest.expression.math.Point;
import langtest.expression.picture.Color;
import langtest.expression.picture.Rectangle;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class LangDrawer extends  PApplet {
	
	private Expression root;
	
	public void setup() {
		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);
		
		root = 	new ForLoop(
					new MathBinding(
							"x",
							null
					),
					new Number(79),
					new Rectangle(
						new Point(
							new Plus(
								new MathBinding(
									"x",
									null
								),
								new Number(100)
							),
							new Multiply(
								new MathBinding(
									"x",
									null
								),
								new Number(10)
							)
						),
						new Point(
									new Number(10),
									new Number(10)
						),
						new Color(
							new Number(255),
							new Number(0),
							new Number(255)
						)
					)
				);
		System.out.println(root);
	}
	
	
	public void draw() {
		clear();
		loadPixels();
		try {
			root.draw(this);
		} catch (TypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updatePixels();
		//System.out.println("Frame rendered.");
	}
}
