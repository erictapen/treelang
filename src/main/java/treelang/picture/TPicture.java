package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.mutate.MExpression;

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

	Integer replace(Stack<Byte> dest, Integer target);

	/**
	 * Takes an rule expression and delivers the locations of the matches.
	 * @param expression
	 * @return
	 */
	ArrayList<Stack<Byte>> findMatches(MExpression expression);

	public int hashCode();

	public void draw(PApplet p);

}
