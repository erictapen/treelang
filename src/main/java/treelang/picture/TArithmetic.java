package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.mutate.MExpression;

public abstract class TArithmetic implements TPicture {

	private final int ARGUMENT_COUNT = 2;
	private final Integer[] args;

	private final int hash;

	public TArithmetic(TPicture op1, TPicture op2, int hash) {
		this.args = new Integer[ARGUMENT_COUNT];
		this.args[0] = new Integer(op1.hashCode());
		TStorage.gI().putNode(this.args[0], op1);
		this.args[1] = new Integer(op2.hashCode());
		TStorage.gI().putNode(this.args[1], op2);
		hash = 37 * hash + this.args[0];
		hash = 37 * hash + this.args[0];
		this.hash = hash;
		TStorage.gI().put(this.hashCode(), this);
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer[] getArgs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash == identifier)
			return expression;
		boolean needsUnLambda = false;
		Integer newOp1 = TStorage.gI().get(this.args[0]).replaceAll(identifier, expression);
		if (newOp1 != this.args[0])
			needsUnLambda = true;
		Integer newOp2 = TStorage.gI().get(this.args[1]).replaceAll(identifier, expression);
		if (newOp2 != this.args[1])
			needsUnLambda = true;
		if (needsUnLambda) {
			this.replaceAllHelper(newOp1, newOp2);
		}
		return this.hashCode();
	}

	protected void replaceAllHelper(Integer newOp1, Integer newOp2) {
		System.out.println(
				"Warning! abstract TArithmetic.replaceAllHelper was called, but this must be implemented by child classes!");
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

	}

	public TPicture getOp1() {
		return TStorage.gI().get(args[0]);
	}

	public TPicture getOp2() {
		return TStorage.gI().get(args[1]);
	}

}
