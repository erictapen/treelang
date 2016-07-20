package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.mutate.MRule;

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
	public Integer applyRule(MRule r) {
		TPicture newPic = r.apply(this);
		TStorage.gI().put(newPic.hashCode(), newPic);
		return newPic.hashCode();
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
