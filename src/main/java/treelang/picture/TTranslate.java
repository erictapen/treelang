package treelang.picture;

import processing.core.PApplet;
import treelang.TStorage;

public class TTranslate implements TPicture {

	Integer num1;
	Integer num2;
	Integer pic;

	public TTranslate(TPicture num1, TPicture num2, TPicture pic) {
		super();
		this.num1 = new Integer(num1.hashCode());
		TStorage.getInstance().put(this.num1, num1);
		this.num2 = new Integer(num2.hashCode());
		TStorage.getInstance().put(this.num2, num2);
		this.pic = new Integer(pic.hashCode());
		TStorage.getInstance().put(this.pic, pic);
	}

	@Override
	public TNumber getNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TPoint getPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	private TPicture getNum1() {
		return TStorage.getInstance().get(num1);
	}

	private TPicture getNum2() {
		return TStorage.getInstance().get(num2);
	}

	private TPicture getPic() {
		return TStorage.getInstance().get(pic);
	}

	@Override
	public void draw(PApplet p) {
		if (getPic().getClass() == TPoint.class) {
			p.pushMatrix();
			p.translate(getNum1().getNumber().getValue(), getNum2().getNumber().getValue());
			getPic().draw(p);
		}
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
