package treelang.picture;

import processing.core.PApplet;

public class TTranslate implements TPicture {

	TPicture num1;
	TPicture num2;
	TPicture pic;

	public TTranslate(TPicture num1, TPicture num2, TPicture pic) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.pic = pic;
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

	@Override
	public void draw(PApplet p) {
		if (pic.getClass() == TPoint.class) {
			p.pushMatrix();
			p.translate(num1.getNumber().getValue(), num2.getNumber().getValue());
			pic.draw(p);
		}
		p.popMatrix();
	}

	@Override
	public String toString() {
		String res = "Translate";
		res += "\n\t" + num1.toString().replaceAll("\n", "\n\t");
		res += "\n\t" + num2.toString().replaceAll("\n", "\n\t");
		res += "\n\t" + pic.toString().replaceAll("\n", "\n\t");
		return res;
	}

}
