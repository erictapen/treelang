package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;

/**
 * A treelang node of type Picture which consists solely of a white Pixel in the
 * middle of the screen.
 * 
 * @author justin
 *
 */
public class TPoint implements TPicture {

	public TPoint() {
		super();
	}

	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		return this.hashCode();
	}

	@Override
	public Integer replace(Integer origin, Integer target, Stack<Byte> dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stack<Byte>> match(Integer expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		p.stroke(255);
		p.point(p.width / 2, p.height / 2);
	}

	@Override
	public String toString() {
		return "Point";
	}

}
