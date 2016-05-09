package treelang.picture;

import processing.core.PApplet;

public interface Picture {
	
	public Number getNumber();
	
	public Point getPoint();
	
	public void draw(PApplet p);
}
