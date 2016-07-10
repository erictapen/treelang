package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

public abstract class TArithmetic implements TPicture {

	private Integer op1;
	private Integer op2;

	public TArithmetic(TPicture op1, TPicture op2) {
		this.op1 = new Integer(op1.hashCode());
		TStorage.gI().put(this.op1, op1);
		this.op2 = new Integer(op2.hashCode());
		TStorage.gI().put(this.op2, op2);
	}
	

	@Override
	public Integer unLambda(String identifier, Integer expression) {
		boolean needsUnLambda = false;
		Integer newOp1 = TStorage.gI().get(this.op1).unLambda(identifier, expression);
		if (newOp1 != this.op1)
			needsUnLambda = true;
		Integer newOp2 = TStorage.gI().get(this.op2).unLambda(identifier, expression);
		if (newOp2 != this.op2)
			needsUnLambda = true;
		if (needsUnLambda) {
			this.unLambdaHelper(newOp1, newOp2);
		}
		return this.hashCode();
	}

	protected void unLambdaHelper(Integer newOp1, Integer newOp2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void draw(PApplet p) {

	}

	public Integer getOp1() {
		return op1;
	}

	public Integer getOp2() {
		return op2;
	}

}
