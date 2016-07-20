package treelang.picture;

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
	public Integer unLambda(String identifier, Integer expression) {
		if (this.name.equals(identifier))
			return expression;
		else
			return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {

	}

	@Override
	public String toString() {
		return "\"" + name + "\"";
	}

}
