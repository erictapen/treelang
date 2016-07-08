package treelang.picture;

import processing.core.PApplet;

/**
 * A basic treelang type which simply holds an Integer. Castable to Picture.
 * 
 * @author justin
 *
 */
public class TNumber implements TPicture {

	private final int value;

	public int getValue() {
		return this.value;
	}

	public TNumber(int value) {
		super();
		this.value = value;
	}

	public void draw(PApplet p) {
		p.loadPixels();
		for (int i = 0; i < this.value; i++) {
			p.pixels[i] = 0xffffff;
		}
		p.updatePixels();
	}

	@Override
	public TNumber getNumber() {
		return this;
	}
	
	@Override
	public String toString() {
		return (new Integer(value)).toString();
	}
}
