package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

/**
 * A basic treelang type which simply holds an Integer. Castable to Picture.
 * 
 * @author justin
 *
 */
public class TNumber implements TPicture {

	private final int ARGUMENT_COUNT = 0;

	private final int value;

	private static int hashInit = 484;
	private final int hash;

	public TNumber(int value) {
		super();
		this.value = value;
		int hash = hashInit;
		hash = 37 * hash + value;
		this.hash = hash;
		TStorage.gI().put(this.hashCode(), this);
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public TNumber getNumber() {
		return this;
	}

	@Override
	public Integer[] getArgs() {
		return new Integer[ARGUMENT_COUNT];
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer origin, Integer target) {
		if (hash == origin)
			return target;
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
