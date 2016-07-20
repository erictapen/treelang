package treelang;

import treelang.parser.Parser;
import treelang.parser.SyntaxErrorException;
import treelang.picture.TLambda;
import treelang.picture.TNumber;
import treelang.picture.TPicture;
import processing.core.PApplet;

/**
 * Do everything
 * 
 * @author justin
 *
 */
@SuppressWarnings("serial")
public class LangDrawer extends PApplet {

	private TPicture root;
	private int time;

	public void setup() {
		time = 0;
		
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
		this.pushMatrix();
		System.out.println(root);
		System.out.println(TStorage.gI());
	}

	public void draw() {
		clear();
		time++;
		(new TLambda("time", new TNumber(time), root)).draw(this);
	}
}
