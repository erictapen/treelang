package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

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

	@Override
	public TNumber getNumber() {
		return this;
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
