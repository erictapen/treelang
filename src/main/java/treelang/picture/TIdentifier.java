package treelang.picture;

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
