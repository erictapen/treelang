package treelang;

import treelang.parser.Parser;
import treelang.parser.SyntaxErrorException;
import java.io.File;

import processing.core.PApplet;

/**
 * Do everything
 * 
 * @author justin
 *
 */
@SuppressWarnings("serial")
public class LangDrawer extends PApplet {

	private Integer root;

	public void setup() {
		
		
		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);

		Parser p = new Parser();
		try {
			root = p.parse(new File("test.tree"));
		} catch (SyntaxErrorException e) {
			System.out.println("Syntax Error!");
			e.printStackTrace();
		}
		this.pushMatrix();
		TStorage.gI().get(root).draw(this);	//for testing
		System.out.println(root);
		System.out.println(TStorage.gI());
	}

	public void draw() {
		clear();
		TStorage.gI().get(root).draw(this);
	}
}
