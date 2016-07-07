package treelang.picture;

import java.util.ArrayList;

import processing.core.PApplet;
import treelang.TStorage;

/**
 * A treelang node of type Picture, which consists of multiple Pictures. These
 * will be drawn in a defined order.
 * 
 * @author justin
 *
 */
public class TList implements TPicture {

	private ArrayList<Integer> children = new ArrayList<Integer>();

	public TList(ArrayList<TPicture> picChilds) {
		for (TPicture x : picChilds) {
			Integer hash = new Integer(x.hashCode());
			this.children.add(hash);
			TStorage.getInstance().put(hash, x);
		}
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
		for (Integer x : this.children)
			TStorage.getInstance().get(x).draw(p);
	}

	@Override
	public String toString() {
		String res = "List";
		for (Integer x : children)
			res += "\n\t" + TStorage.getInstance().get(x).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
