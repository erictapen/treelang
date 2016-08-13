package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

public class TIdentifier implements TPicture {

	private final int ARGUMENT_COUNT = 0;

	private final String name;

	private static int hashInit = 213;
	private final int hash;

	public TIdentifier(String name) {
		this.name = name;
		int hash = hashInit;
		hash = 37 * hash + name.hashCode();
		this.hash = hash;
		TStorage.gI().put(this.hashCode(), this);
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
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
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash == identifier)
			return expression;
		else
			return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {

	}

	@Override
	public String toString() {
		return "\"" + name + ":" + this.hash + "\"";
	}

}
