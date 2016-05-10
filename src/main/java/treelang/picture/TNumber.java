package treelang.picture;

import processing.core.PApplet;

public class TNumber implements TPicture {
	
	private int value;

	public int getValue() {
		return this.value;
	}
	
	public TNumber(int value) {
		super();
		this.value = value;
	}

	public void draw(PApplet p) {
		p.loadPixels();
		for(int i=0; i<this.value; i++) {
			p.pixels[i] = 0xffffff;
		}
		p.updatePixels();
	}

	@Override
	public TNumber getNumber() {
		return this;
	}

	@Override
	public TPoint getPoint() {
		// TODO Auto-generated method stub
		return null;
	}
}
