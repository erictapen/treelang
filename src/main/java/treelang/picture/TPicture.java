package treelang.picture;

import processing.core.PApplet;
import treelang.mutate.MRule;

/**
 * The type Picture is castable to Number.
 * 
 * @author justin
 *
 */
public interface TPicture {

	public TNumber getNumber();

	public Integer unLambda(String identifier, Integer expression);
	
	public Integer applyRule(MRule r);

	public void draw(PApplet p);

}
