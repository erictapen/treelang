package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.mutate.MExpression;

/**
 * A treelang node of type Picture, which consists of multiple Pictures. These
 * will be drawn in a defined order.
 * 
 * @author justin
 *
 */
public class TList implements TPicture {

	private final Integer[] args;

	private static int hashInit = 911;
	private final int hash;

	public TList(ArrayList<TPicture> picChilds) {
		this.args = new Integer[picChilds.size()];
		for (int i = 0; i < args.length; i++) {
			Integer pichash = picChilds.get(i).hashCode();
			args[i] = pichash;
			TStorage.gI().putNode(pichash, picChilds.get(i));
		}
		int hash = hashInit;
		for (Integer x : this.args)
			hash = 37 * hash + x;
		this.hash = hash;
		TStorage.gI().put(this.hashCode(), this);
	}

	public TList(TPicture picChild) {
		this.args = new Integer[1];
		Integer pichash = picChild.hashCode();
		TStorage.gI().putNode(pichash, picChild);
		args[0] = pichash;
		int hash = hashInit;
		hash = 37 * hash + args[0];
		this.hash = hash;
	}

	public TList(TPicture p0, TPicture p1) {
		this.args = new Integer[2];
		Integer pic0hash = p0.hashCode();
		TStorage.gI().putNode(pic0hash, p0);
		args[0] = pic0hash;
		Integer pic1hash = p1.hashCode();
		TStorage.gI().putNode(pic1hash, p1);
		args[1] = pic1hash;
		int hash = hashInit;
		hash = 37 * hash + args[0];
		hash = 37 * hash + args[1];
		this.hash = hash;
	}

	public Integer[] getChildren() {
		return args;
	}

	public int getSize() {
		return args.length;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(this.args.length);
	}

	@Override
	public Integer[] getArgs() {
		return args;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash == identifier)
			return expression;
		boolean needsUnLambda = false;
		ArrayList<TPicture> newList = new ArrayList<TPicture>();
		for (Integer x : this.args) {
			Integer newEl = TStorage.gI().get(x).replaceAll(identifier, expression);
			newList.add(TStorage.gI().get(newEl));
			if (x != newEl)
				needsUnLambda = true;
		}
		if (needsUnLambda) {
			TPicture newNode = new TList(newList);
			Integer newHash = newNode.hashCode();
			TStorage.gI().putNode(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public Integer replace(Integer origin, Integer target, Stack<Byte> dest) {
		// TODO
		return null;
	}

	@Override
	public ArrayList<Stack<Byte>> findMatches(MExpression expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		for (Integer x : this.args)
			TStorage.gI().get(x).draw(p);
	}

	@Override
	public String toString() {
		String res = "List";
		for (Integer x : args)
			res += "\n\t" + TStorage.gI().get(x).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
