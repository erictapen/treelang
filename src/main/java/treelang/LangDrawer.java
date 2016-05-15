package treelang;

import treelang.parser.Parser;
import treelang.parser.SyntaxErrorException;
import treelang.picture.TLine;
import treelang.picture.TNumber;
import treelang.picture.TPicture;
import treelang.picture.TPoint;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class LangDrawer extends  PApplet {
	
	private TPicture root;
	
	public void setup() {
		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);
		
		root = new TNumber(1000);

		
		Parser p = new Parser();
		try {
			root = p.parse("test.tree");
		} catch (SyntaxErrorException e) {
			System.out.println("Syntax Error!");
			e.printStackTrace();
		}
		
		
//		root = new treelang.picture.TPoint(
//				new treelang.picture.TNumber(256),
//				new treelang.picture.TNumber(256));
		
//		root = 	new TLine(
//					new TPoint(
//							new TNumber(256),
//							new TNumber(256)),
//					new TPoint(
//							new TNumber(266),
//							new TNumber(266)));
		System.out.println(root);
	}
	
	
	public void draw() {
		clear();
		root.draw(this);
		//System.out.println(root);
	}
}
