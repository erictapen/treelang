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

	private final ArrayList<Integer> children = new ArrayList<Integer>();

	private static int hashInit = 911;
	private final int hash;

	public TList(ArrayList<TPicture> picChilds) {
		for (TPicture x : picChilds) {
			Integer hash = new Integer(x.hashCode());
			this.children.add(hash);
			TStorage.gI().put(hash, x);
		}
		int hash = hashInit;
		for (Integer x : this.children)
			hash = 37 * hash + x;
		this.hash = hash;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(this.children.size());
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer unLambda(String identifier, Integer expression) {
		boolean needsUnLambda = false;
		ArrayList<TPicture> newList = new ArrayList<TPicture>();
		for (Integer x : this.children) {
			Integer newEl = TStorage.gI().get(x).unLambda(identifier, expression);
			newList.add(TStorage.gI().get(newEl));
			if (x != newEl)
				needsUnLambda = true;
		}
		if (needsUnLambda) {
			TPicture newNode = new TList(newList);
			Integer newHash = newNode.hashCode();
			TStorage.gI().put(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {
		for (Integer x : this.children)
			TStorage.gI().get(x).draw(p);
	}

	@Override
	public String toString() {
		String res = "List";
		for (Integer x : children)
			res += "\n\t" + TStorage.gI().get(x).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
