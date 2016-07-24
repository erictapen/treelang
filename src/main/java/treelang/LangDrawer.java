package treelang;

import treelang.mutate.MRule;
import treelang.mutate.MSimple;
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
	
	private MRule myrule = null;

	public void setup() {
		
		super.setup();
		size(512, 512);
		background(0);
		fill(255, 255, 255);

		Integer expr1 = null;
		Integer expr2 = null;
		
		Parser p = new Parser();
		try {
			root = p.parse(new File("test.tree"));
			TPicture pic1 = new TPoint();
			TStorage.gI().put(pic1.hashCode(), pic1);
			expr1 = pic1.hashCode();
			TPicture pic2 = new TTranslate(new TNumber(5), new TNumber(5), new TPoint());
			TStorage.gI().put(pic2.hashCode(), pic2);
			expr2 = pic2.hashCode();
		} catch (SyntaxErrorException e) {
			System.out.println("Syntax Error!");
			e.printStackTrace();
		}
		myrule = new MRule(new MSimple(expr1), new MSimple(expr2));
		this.pushMatrix();
		TStorage.gI().get(root).draw(this);	//for testing
		System.out.println(TStorage.gI().get(root));
		System.out.println(TStorage.gI());
	}

	public void draw() {
		clear();
		TStorage.gI().get(root).draw(this);
		root = myrule.apply(root);
		System.out.println(TStorage.gI().get(root));
	}
}
