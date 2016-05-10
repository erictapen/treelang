package treelang;

import treelang.picture.TLine;
import treelang.picture.TPicture;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class LangDrawer extends  PApplet {
	
	private TPicture root;
	
	public void setup() {
		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);
		
		root = new treelang.picture.TPoint(
				new treelang.picture.TNumber(256),
				new treelang.picture.TNumber(256));
		
//		root = 	new Line(
//					new treelang.picture.Point(
//							new treelang.picture.Number(256),
//							new treelang.picture.Number(256)),
//					new treelang.picture.Point(
//							new treelang.picture.Number(266),
//							new treelang.picture.Number(266)));
		System.out.println(root);
	}
	
	
	public void draw() {
		clear();
		root.draw(this);
		System.out.println(root);
	}
}
