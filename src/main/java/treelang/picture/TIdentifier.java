package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;

public class TIdentifier implements TPicture {

	private final String name;

	public TIdentifier(String name) {
		this.name = name;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (this.name.equals(identifier))
			return expression;
		else
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

	@Override
	public void draw(PApplet p) {

	}

	@Override
	public String toString() {
		return "\"" + name + "\"";
	}

}
