package treelang.picture;

import processing.core.PApplet;

public class Point implements Picture {
	
	private Picture x;
	private Picture y;

	public Picture getX() {
		return x;
	}

	public Picture getY() {
		return y;
	}

	public Point(Picture x, Picture y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Number getNumber() {
		return this.x.getNumber();
	}

	public Point getPoint() {
		return this;
	}

	@Override
	public void draw(PApplet p) {
		p.stroke(153);
		p.point(x.getNumber().getValue(),
				y.getNumber().getValue());
	}

}
