package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;

/**
 * The type Picture is castable to Number.
 * 
 * @author justin
 *
 */
public interface TPicture {

	public TNumber getNumber();

	Integer replaceAll(Integer origin, Integer target);

	Integer replace(Integer origin, Integer target, Stack<Byte> dest);

	ArrayList<Stack<Byte>> match(Integer expression);

	public int hashCode();

	public void draw(PApplet p);

}
