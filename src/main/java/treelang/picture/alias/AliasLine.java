package treelang.picture.alias;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.picture.TNumber;
import treelang.picture.TPicture;

public class AliasLine extends AbstractAlias implements TPicture {

	/**
	 * op1, op2
	 * 
	 */
	private final int ARGUMENT_COUNT = 2;
	private final Integer[] args;

	public AliasLine(TPicture op0, TPicture op1, Integer hash) {
		super(hash);
		this.args = new Integer[ARGUMENT_COUNT];
		this.args[0] = new Integer(op0.hashCode());
		this.args[1] = new Integer(op1.hashCode());
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public Integer[] getArgs() {
		return args;
	}

	@Override
	public void draw(PApplet p) {
		p.line(0, 0, TStorage.gI().get(args[0]).getNumber().getValue(),
				TStorage.gI().get(args[1]).getNumber().getValue());
	}

	@Override
	public String toString() {
		String res = "Line:" + this.hash;
		res += "\n\t" + TStorage.gI().get(this.args[0]).toString().replaceAll("\n", "\n\t");
		res += "\n\t" + TStorage.gI().get(this.args[1]).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
