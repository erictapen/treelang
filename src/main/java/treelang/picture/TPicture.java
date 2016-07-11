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

	public Integer unLambda(String identifier, Integer expression);

	public void draw(PApplet p);

}
