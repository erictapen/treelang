package treelang.parser;

import java.util.ArrayList;

import treelang.picture.TDivide;
import treelang.picture.TForLoop;
import treelang.picture.TIdentifier;
import treelang.picture.TLambda;
import treelang.picture.TList;
import treelang.picture.TMinus;
import treelang.picture.TMultiply;
import treelang.picture.TNumber;
import treelang.picture.TPicture;
import treelang.picture.TPlus;
import treelang.picture.TPoint;
import treelang.picture.TTranslate;

/**
 * Helping structure in the parsing process. Makes up a tree structure.
 * 
 * @author justin
 *
 */
public class PNode {
	private String caption;

	private ArrayList<PNode> children = new ArrayList<PNode>();

	public PNode(String caption) {
		super();
		// strip caption from whitespace and comments
		caption = caption.trim();
		this.caption = caption;
	}

	public String getCaption() {
		return caption;
	}

	public ArrayList<PNode> getChildren() {
		return children;
	}

	public void addChild(PNode child) {
		this.children.add(child);
	}

	/**
	 * Turns an PNode into a TPicture
	 * 
	 * @return
	 * @throws SyntaxErrorException in case of wrong amount of arguments
	 */
	public TPicture getTPic() throws SyntaxErrorException {
		ArrayList<TPicture> picChilds = new ArrayList<TPicture>();
		for (PNode x : this.children)
			picChilds.add(x.getTPic());
		switch (this.caption) {
		case "List":
			return new TList(picChilds);
		case "Point":
			if (picChilds.size() != 0)
				throw new SyntaxErrorException();
			return new TPoint();
		case "Translate":
			if (picChilds.size() != 3)
				throw new SyntaxErrorException();
			return new TTranslate(picChilds.get(0), picChilds.get(1), picChilds.get(2));
		case "Lambda":
			if (picChilds.size() != 3)
				throw new SyntaxErrorException();
			return new TLambda(this.children.get(0), picChilds.get(1), picChilds.get(2));
		case "For":
			if (picChilds.size() != 3)
				throw new SyntaxErrorException();
			return new TForLoop(this.children.get(0), picChilds.get(1), picChilds.get(2));
		case "+":
			if(picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TPlus(picChilds.get(0), picChilds.get(1));
		case "-":
			if(picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TMinus(picChilds.get(0), picChilds.get(1));
		case "/":
			if(picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TDivide(picChilds.get(0), picChilds.get(1));
		case "*":
			if(picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TMultiply(picChilds.get(0), picChilds.get(1));
		default:
			try {
				return new TNumber(Integer.decode(caption));
			} catch (NumberFormatException e) {
				return new TIdentifier(this.caption);
			}
		}
	}

	@Override
	public String toString() {
		String res = "";
		for (PNode x : this.children)
			res += x.getCaption() + " is child of " + this.caption + "\n";
		for (PNode x : this.children)
			res += x.toString();
		return res;
	}

}
