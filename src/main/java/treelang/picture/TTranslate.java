package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

/**
 * A treelang node of type Number -> Number -> Picture -> Picture which moves a
 * picture along x and y coordinates.
 * 
 * @author justin
 *
 */
public class TTranslate implements TPicture {

	private final Integer num1;
	private final Integer num2;
	private final Integer pic;

	public TTranslate(TPicture num1, TPicture num2, TPicture pic) {
		super();
		this.num1 = new Integer(num1.hashCode());
		TStorage.gI().put(this.num1, num1);
		this.num2 = new Integer(num2.hashCode());
		TStorage.gI().put(this.num2, num2);
		this.pic = new Integer(pic.hashCode());
		TStorage.gI().put(this.pic, pic);
	}

	@Override
	public TNumber getNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	private TPicture getNum1() {
		return TStorage.gI().get(num1);
	}

	private TPicture getNum2() {
		return TStorage.gI().get(num2);
	}

	private TPicture getPic() {
		return TStorage.gI().get(pic);
	}

	@Override
	public Integer unLambda(String identifier, Integer expression) {
		boolean needsUnLambda = false;
		Integer newNum1 = TStorage.gI().get(this.num1).unLambda(identifier, expression);
		if (newNum1 != this.num1)
			needsUnLambda = true;
		Integer newNum2 = TStorage.gI().get(this.num2).unLambda(identifier, expression);
		if (newNum2 != this.num2)
			needsUnLambda = true;
		Integer newPic = TStorage.gI().get(this.pic).unLambda(identifier, expression);
		if (newPic != this.pic)
			needsUnLambda = true;
		if (needsUnLambda) {
			TPicture newNode = new TTranslate(TStorage.gI().get(newNum1), TStorage.gI().get(newNum2),
					TStorage.gI().get(newPic));
			Integer newHash = newNode.hashCode();
			TStorage.gI().put(newHash, newNode);
			return newHash;
		}
		return this.hashCode();
	}

	@Override
	public void draw(PApplet p) {
		//if (getPic().getClass() == TPoint.class) {
		p.pushMatrix();
		p.translate(getNum1().getNumber().getValue(), getNum2().getNumber().getValue());
		getPic().draw(p);
		//}
		p.popMatrix();
	}

	@Override
	public String toString() {
		String res = "Translate";
		res += "\n\t" + getNum1().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getNum2().toString().replaceAll("\n", "\n\t");
		res += "\n\t" + getPic().toString().replaceAll("\n", "\n\t");
		return res;
	}

}
