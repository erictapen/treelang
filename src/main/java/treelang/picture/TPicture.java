package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

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
	
	Integer replace(Integer origin, Integer target, Stack<Byte> dest);
	
	Integer replaceAll(Integer origin, Integer target);
	
	ArrayList<Stack<Byte>> match(Integer expression); 

	public void draw(PApplet p);

}
