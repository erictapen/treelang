package treelang.picture;

import processing.core.PApplet;

public class Line implements Picture{
	public Picture start;
	public Picture end;
	
	public Picture getStart() {
		return start;
	}

	public Picture getEnd() {
		return end;
	}

	public Line(Picture start, Picture end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public Number getNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		p.stroke(255);
		p.line(start.getPoint().getX().getNumber().getValue(), 
				start.getPoint().getY().getNumber().getValue(),
				end.getPoint().getX().getNumber().getValue(),
				end.getPoint().getY().getNumber().getValue());
	}
	
	
}
