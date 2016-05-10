package treelang.picture;

import processing.core.PApplet;

public interface TPicture {
	
	public TNumber getNumber();
	
	public TPoint getPoint();
	
	public void draw(PApplet p);
}
