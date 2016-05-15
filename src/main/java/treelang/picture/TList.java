package treelang.picture;

import java.util.ArrayList;

import processing.core.PApplet;

public class TList implements TPicture {

	private ArrayList<TPicture> children = new ArrayList<TPicture>();
	
	public TList(ArrayList<TPicture> picChilds) {
		this.children = picChilds;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(this.children.size());
	}

	@Override
	public TPoint getPoint() {
		return this.getNumber().getPoint();
	}

	@Override
	public void draw(PApplet p) {
		for(TPicture x : this.children) x.draw(p);
	}

}
