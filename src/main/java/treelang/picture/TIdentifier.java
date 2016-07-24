package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;

public class TIdentifier implements TPicture {

	private final String name;
	
	private static int hashInit = 213;
	private final int hash;

	public TIdentifier(String name) {
		this.name = name;
		int hash = hashInit;
		hash = 37 * hash + name.hashCode();
		this.hash = hash;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}
	
	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash==identifier)
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
