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

	public Integer[] getArgs();

	Integer replaceAll(Integer origin, Integer target);

	public int hashCode();

	public void draw(PApplet p);

}
