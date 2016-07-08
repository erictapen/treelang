package treelang;

import treelang.parser.Parser;
import treelang.parser.SyntaxErrorException;
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
		
		System.out.println(root);
		System.out.println(TStorage.gI());
	}

	public void draw() {
		clear();
		root.draw(this);
		// System.out.println(root);
	}
}
