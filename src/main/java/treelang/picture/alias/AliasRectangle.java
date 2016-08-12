package treelang.picture.alias;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.picture.TNumber;
import treelang.picture.TPicture;

public final class AliasRectangle extends AbstractAlias implements TPicture {

	/**
	 * op1, op2
	 * 
	 */
	private final int ARGUMENT_COUNT = 2;
	private final Integer[] args;

	public AliasRectangle(TPicture op0, TPicture op1, Integer hash) {
		super(hash);
		this.args = new Integer[ARGUMENT_COUNT];
		this.args[0] = new Integer(op0.hashCode());
		TStorage.gI().putNode(this.args[0], op0);
		this.args[1] = new Integer(op1.hashCode());
		TStorage.gI().putNode(this.args[1], op1);
	}

	@Override
	public Integer[] getArgs() {
		return args;
	}

	@Override
	public Integer replaceAll(Integer origin, Integer target) {
		return this.hash;
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(0);
	}

	@Override
	public void draw(PApplet p) {
		p.noFill();
		p.rect(0, 0, TStorage.gI().get(this.args[0]).getNumber().getValue(),
				TStorage.gI().get(this.args[1]).getNumber().getValue());
	}

	@Override
	public String toString() {
		String res = "Rectangle:" + this.hash;
		res += "\n\t" + TStorage.gI().get(this.args[0]).toString().replaceAll("\n", "\n\t");
		res += "\n\t" + TStorage.gI().get(this.args[1]).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
