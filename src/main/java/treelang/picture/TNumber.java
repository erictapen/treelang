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

	private static int hashInit = 484;
	private final int hash;

	public TNumber(int value) {
		super();
		this.value = value;
		int hash = hashInit;
		hash = 37 * hash + value;
		this.hash = hash;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public TNumber getNumber() {
		return this;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer unLambda(String identifier, Integer expression) {
		return this.hashCode();
	}

	public void draw(PApplet p) {
		p.loadPixels();
		for (int i = 0; i < this.value; i++) {
			p.pixels[i] = 0xffffff;
		}
		p.updatePixels();
	}

	@Override
	public String toString() {
		return (new Integer(value)).toString();
	}
}
