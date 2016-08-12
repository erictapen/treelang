package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

/**
 * A treelang node of type Picture which consists solely of a white Pixel in the
 * middle of the screen.
 * 
 * @author justin
 *
 */
public class TPoint implements TPicture {

	private final int ARGUMENT_COUNT = 0;

	private static int hashInit = 173;
	private static int hash;

	public TPoint() {
		super();
		hash = hashInit;
		TStorage.gI().put(this.hashCode(), this);
	}

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
		return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {
		p.point(0, 0);
	}

	@Override
	public String toString() {
		return "Point";
	}

}
