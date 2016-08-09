package treelang.picture.alias;

import processing.core.PApplet;
import treelang.picture.TIdentifier;
import treelang.picture.TNumber;
import treelang.picture.TPicture;

public final class AliasRectangle extends AbstractAlias implements TPicture {
	
	final int ALIAS_ARGUMENT_COUNT;
	
	private final TIdentifier arg0 = new TIdentifier("xCoord");
	private final TIdentifier arg1 = new TIdentifier("yCoord");
	

	public AliasRectangle(TPicture num1, TPicture num2) {
		this.ALIAS_ARGUMENT_COUNT = 2;
	}

	@Override
	public TNumber getNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub

	}

}
