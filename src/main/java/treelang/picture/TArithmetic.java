package treelang.picture;

import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import treelang.TStorage;
import treelang.mutate.MExpression;

public abstract class TArithmetic implements TPicture {

	private final Integer op1;
	private final Integer op2;

	private final int hash;

	public TArithmetic(TPicture op1, TPicture op2, int hash) {
		this.op1 = new Integer(op1.hashCode());
		TStorage.gI().put(this.op1, op1);
		this.op2 = new Integer(op2.hashCode());
		TStorage.gI().put(this.op2, op2);
		hash = 37 * hash + this.op1;
		hash = 37 * hash + this.op2;
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public Integer replaceAll(Integer identifier, Integer expression) {
		if (hash==identifier)
			return expression;
		boolean needsUnLambda = false;
		Integer newOp1 = TStorage.gI().get(this.op1).replaceAll(identifier, expression);
		if (newOp1 != this.op1)
			needsUnLambda = true;
		Integer newOp2 = TStorage.gI().get(this.op2).replaceAll(identifier, expression);
		if (newOp2 != this.op2)
			needsUnLambda = true;
		if (needsUnLambda) {
			this.replaceAllHelper(newOp1, newOp2);
		}
		return this.hashCode();
	}

	protected void replaceAllHelper(Integer newOp1, Integer newOp2) {
		System.out.println("Warning! abstract TArithmetic.replaceAllHelper was called, but this must be implemented by child classes!");
	}

	@Override
	public Integer replace(Integer origin, Integer target, Stack<Byte> dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stack<Byte>> match(MExpression expression) {
		ArrayList<Stack<Byte>> res = new ArrayList<Stack<Byte>>();
		if(expression.matches(this))
			res.add(new Stack<Byte>());
		this.getOp1().match(expression);
		this.getOp2().match(expression);
		return null;
	}

	@Override
	public void draw(PApplet p) {

	}

	public TPicture getOp1() {
		return TStorage.gI().get(op1);
	}

	public TPicture getOp2() {
		return TStorage.gI().get(op2);
	}

}
