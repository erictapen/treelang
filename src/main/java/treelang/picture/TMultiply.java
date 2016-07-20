package treelang.picture;

import treelang.TStorage;

public final class TMultiply extends TArithmetic implements TPicture {

	public TMultiply(TPicture op1, TPicture op2) {
		super(op1, op2, 505);
	}

	protected void unLambdaHelper(Integer newOp1, Integer newOp2) {
		TPicture newNode = new TMultiply(TStorage.gI().get(newOp1), TStorage.gI().get(newOp2));
		Integer newHash = newNode.hashCode();
		TStorage.gI().put(newHash, newNode);
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(TStorage.gI().get(getOp1()).getNumber().getValue() * TStorage.gI().get(getOp2()).getNumber().getValue());
	}

	@Override
	public String toString() {
		String res = "*";
		res += "\n\t" + TStorage.gI().get(getOp1()).toString().replaceAll("\n", "\n\t");
		res += "\n\t" + TStorage.gI().get(getOp2()).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
