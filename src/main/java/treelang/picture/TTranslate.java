package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.mutate.MExpression;

/**
 * A treelang node of type Number -> Number -> Picture -> Picture which moves a
 * picture along x and y coordinates.
 * 
 * @author justin
 *
 */
public class TTranslate implements TPicture {

	/** num1 num2 pic
	 * 
	 */
	private final int ARGUMENT_COUNT = 3;
	private final Integer[] args;

	private static int hashInit = 364;
	private final int hash;

	public TTranslate(TPicture num1, TPicture num2, TPicture pic) {
		super();
		this.args = new Integer[ARGUMENT_COUNT];
		this.args[0] = new Integer(num1.hashCode());
		TStorage.gI().putNode(this.args[0], num1);
		this.args[1] = new Integer(num2.hashCode());
		TStorage.gI().putNode(this.args[1], num2);
		this.args[2] = new Integer(pic.hashCode());
		TStorage.gI().putNode(this.args[2], pic);
		int hash = hashInit;
		hash = 37 * hash + this.args[0];
		hash = 37 * hash + this.args[1];
		hash = 37 * hash + this.args[2];
		this.hash = hash;
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
	public int hashCode() {
		return hash;
	}

	private TPicture getNum1() {
		return TStorage.gI().get(args[0]);
	}

	private TPicture getNum2() {
		return TStorage.gI().get(args[1]);
	}

	private TPicture getPic() {
		return TStorage.gI().get(args[2]);
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash==identifier)
			return expression;
		boolean needsUnLambda = false;
		Integer newNum1 = TStorage.gI().get(this.args[0]).replaceAll(identifier, expression);
		if (newNum1 != this.args[0])
			needsUnLambda = true;
		Integer newNum2 = TStorage.gI().get(this.args[1]).replaceAll(identifier, expression);
		if (newNum2 != this.args[1])
			needsUnLambda = true;
		Integer newPic = TStorage.gI().get(this.args[2]).replaceAll(identifier, expression);
		if (newPic != this.args[2])
			needsUnLambda = true;
		if (needsUnLambda) {
			TPicture newNode = new TTranslate(TStorage.gI().get(newNum1), TStorage.gI().get(newNum2),
					TStorage.gI().get(newPic));
			Integer newHash = newNode.hashCode();
			TStorage.gI().putNode(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public Integer replace(Integer origin, Integer target, Stack<Byte> dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stack<Byte>> findMatches(MExpression expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		p.pushMatrix();
		p.translate(getNum1().getNumber().getValue(), getNum2().getNumber().getValue());
		getPic().draw(p);
		p.popMatrix();
	}

	@Override
	public String toString() {
		String res = "Translate:" + this.hash;
		res += "\n\t" + getNum1().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getNum2().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getPic().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
