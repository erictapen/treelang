package treelang.picture;

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
	public Integer unLambda(String identifier, Integer expression) {
		return this.hashCode();
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
