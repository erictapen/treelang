package treelang;

import treelang.mutate.MRule;
import treelang.mutate.MSimple;
import treelang.mutate.Mutator;
import treelang.parser.Parser;
import treelang.parser.SyntaxErrorException;
import treelang.picture.TNumber;
import treelang.picture.TPicture;
import treelang.picture.TPoint;
import treelang.picture.TTranslate;

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

	private Mutator m = new Mutator();

	public void setup() {

		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);

		TPicture pic1 = new TPoint();
		TStorage.gI().put(pic1.hashCode(), pic1);
		Integer point = pic1.hashCode();
		TPicture pic2 = new TTranslate(new TNumber(5), new TNumber(5), new TPoint());
		TStorage.gI().put(pic2.hashCode(), pic2);
		Integer movedPoint = pic2.hashCode();
		TPicture pic3 = new TTranslate(new TNumber(-5), new TNumber(5), new TPoint());
		TStorage.gI().put(pic3.hashCode(), pic3);
		Integer movedMinusPoint = pic3.hashCode();
		m.addRule(new MRule(new MSimple(point), new MSimple(movedPoint)));
		m.addRule(new MRule(new MSimple(point), new MSimple(movedMinusPoint)));

		Parser p = new Parser();
		try {
			root = p.parse(new File("tree"));
		} catch (SyntaxErrorException e) {
			System.out.println("Syntax Error!");
			e.printStackTrace();
		}
		this.pushMatrix();
		TStorage.gI().get(root).draw(this); // for testing
		System.out.println(TStorage.gI().get(root));
		System.out.println(TStorage.gI());
	}

	public void draw() {
		clear();
		TStorage.gI().get(root).draw(this);
		root = m.mutate(root);
		System.out.println(TStorage.gI().get(root));
	}
}
