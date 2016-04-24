package treelang;

import java.util.ArrayList;

import treelang.expression.*;
import treelang.expression.math.MathBinding;
import treelang.expression.math.Multiply;
import treelang.expression.math.Number;
import treelang.expression.math.Plus;
import treelang.expression.picture.Color;
import treelang.expression.picture.Point;
import treelang.expression.picture.Rectangle;
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
		ArrayList<Expression> list = root.getSubTree();
		Expression mutant = list.get((int) (Math.random() * list.size()));
		mutant.mutate();
		try {
			root.draw(this);
		} catch (TypeException e) {
			System.out.println("Fail");
		}
		System.out.println(root);
	}
}
