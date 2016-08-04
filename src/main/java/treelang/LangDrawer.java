package treelang;

import treelang.mutate.Mutator;
import treelang.parser.TreeLangParser;
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

	private Mutator m = new Mutator("rule.rule");

	public void setup() {

		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);

		TreeLangParser p = new TreeLangParser();
		try {
			root = p.parse(new File("tree.tree"));
		} catch (SyntaxErrorException e) {
			System.out.println("Syntax Error!");
			e.printStackTrace();
		}
		this.pushMatrix();
		TStorage.gI().get(root).draw(this); // for testing
		System.out.println(TStorage.gI().get(root));
		System.out.println(TStorage.gI());
		System.out.println(m);
	}

	public void draw() {
		clear();
		TStorage.gI().get(root).draw(this);
		root = m.mutate(root);
		//System.out.println(TStorage.gI().get(root));
	}
}
