package treelang;

import treelang.picture.Line;
import treelang.picture.Picture;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class LangDrawer extends  PApplet {
	
	private Picture root;
	
	public void setup() {
		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);
		
		root = new treelang.picture.Point(
				new treelang.picture.Number(256),
				new treelang.picture.Number(256));
		
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
