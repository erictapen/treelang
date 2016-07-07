package treelang.picture;

import processing.core.PApplet;

/**
 * The type Picture is castable to Number.
 * 
 * @author justin
 *
 */
public interface TPicture {

	public TNumber getNumber();

	public TPoint getPoint();

	public void draw(PApplet p);

	@Override
	public int hashCode();

}
