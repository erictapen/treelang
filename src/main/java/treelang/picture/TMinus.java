package treelang.picture;

import treelang.TStorage;

public final class TMinus extends TArithmetic implements TPicture {

	public TMinus(TPicture op1, TPicture op2) {
		super(op1, op2);
	}

	protected void replaceAllHelper(Integer newOp1, Integer newOp2) {
		TPicture newNode = new TMinus(TStorage.gI().get(newOp1), TStorage.gI().get(newOp2));
		Integer newHash = newNode.hashCode();
		TStorage.gI().put(newHash, newNode);
	}

	@Override
	public TNumber getNumber() {
		return new TNumber(TStorage.gI().get(getOp1()).getNumber().getValue() - TStorage.gI().get(getOp2()).getNumber().getValue());
	}

	@Override
	public String toString() {
		String res = "-";
		res += "\n\t" + TStorage.gI().get(getOp1()).toString().replaceAll("\n", "\n\t");
		res += "\n\t" + TStorage.gI().get(getOp2()).toString().replaceAll("\n", "\n\t");
		return res;
	}

}
