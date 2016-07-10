package treelang.picture;

import treelang.TStorage;

public final class TDivide extends TArithmetic implements TPicture {

	public TDivide(TPicture op1, TPicture op2) {
		super(op1, op2);
	}

	protected void unLambdaHelper(Integer newOp1, Integer newOp2) {
		TPicture newNode = new TDivide(TStorage.gI().get(newOp1), TStorage.gI().get(newOp2));
		Integer newHash = newNode.hashCode();
		TStorage.gI().put(newHash, newNode);
	}

	@Override
	public TNumber getNumber() {
		int op2 = TStorage.gI().get(getOp2()).getNumber().getValue();
		if(op2==0) op2 = 1;
		return new TNumber(TStorage.gI().get(getOp1()).getNumber().getValue() / op2);
	}

	@Override
	public String toString() {
		String res = "/";
		res += "\n\t" + TStorage.gI().get(getOp1()).toString().replaceAll("\n", "\n\t");
		res += "\n\t" + TStorage.gI().get(getOp2()).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
