package treelang.picture;

import processing.core.PApplet;

public class TPoint implements TPicture {

	public TPoint() {
		super();
	}

	public TNumber getNumber() {
		return new TNumber(0);
	}

	public TPoint getPoint() {
		return this;
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
