package treelang.picture;

import processing.core.PApplet;

public class TPoint implements TPicture {
	
	private TPicture x;
	private TPicture y;

	public TPicture getX() {
		return x;
	}

	public TPicture getY() {
		return y;
	}

	public TPoint(TPicture x, TPicture y) {
		super();
		this.x = x;
		this.y = y;
	}

	public TNumber getNumber() {
		return this.x.getNumber();
	}

	public TPoint getPoint() {
		return this;
	}

	@Override
	public void draw(PApplet p) {
		p.stroke(153);
		p.point(x.getNumber().getValue(),
				y.getNumber().getValue());
	}

}
