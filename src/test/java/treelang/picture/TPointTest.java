package treelang.picture;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import processing.core.PApplet;

public class TPointTest {

	@Test
	public final void whenDrawThenSingleWhitePixelInTheMiddleOfTheScreen() {
		//TODO
		PApplet p1 = new PApplet();
		PApplet p2 = new PApplet();
		TPoint x = new TPoint();
		p1.setup();
		p1.size(512, 512);
		p1.background(0);
		x.draw(p1);
		p2.setup();
		p2.size(512, 512);
		p2.background(0);
		p2.stroke(255);
		p2.point(256, 256);
		assertEquals(p1.pixels, p2.pixels);
	}
}