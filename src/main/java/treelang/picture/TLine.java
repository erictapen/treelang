package treelang.picture;

import java.util.ArrayList;

import processing.core.PApplet;

public class TLine implements TPicture{
	public TPicture start;
	public TPicture end;
	
	public TPicture getStart() {
		return start;
	}

	public TPicture getEnd() {
		return end;
	}

	public TLine(TPicture start, TPicture end) {
		super();
		this.start = start;
		this.end = end;
	}

	public TLine(ArrayList<TPicture> picChilds) {
		
	}

	@Override
	public TNumber getNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TPoint getPoint() {
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
