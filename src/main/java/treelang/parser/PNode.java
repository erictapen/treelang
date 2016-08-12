package treelang.parser;

import java.util.ArrayList;

import treelang.TStorage;
import treelang.mutate.MExpression;
import treelang.mutate.MTreeLangNode;
import treelang.mutate.MTreeLangTree;
import treelang.mutate.MWildcard;
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
import treelang.picture.alias.AbstractAlias;

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
	 * @throws SyntaxErrorException
	 *             in case of wrong amount of arguments
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
			return new TLambda(picChilds.get(0), picChilds.get(1), picChilds.get(2));
		case "For":
			if (picChilds.size() != 3)
				throw new SyntaxErrorException();
			return new TForLoop(picChilds.get(0), picChilds.get(1), picChilds.get(2));
		case "+":
			if (picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TPlus(picChilds.get(0), picChilds.get(1));
		case "-":
			if (picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TMinus(picChilds.get(0), picChilds.get(1));
		case "/":
			if (picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TDivide(picChilds.get(0), picChilds.get(1));
		case "*":
			if (picChilds.size() != 2)
				throw new SyntaxErrorException();
			return new TMultiply(picChilds.get(0), picChilds.get(1));
		case ".": // wildcard character for rule expression, not a valid
					// treelang expression
			throw new SyntaxErrorException();
		default:
			try {
				return new TNumber(Integer.decode(caption));
			} catch (NumberFormatException e) {
				if (Character.isUpperCase(caption.charAt(0)))
					return AbstractAlias.generateAliasExpression(caption, picChilds);
				if (Character.isLowerCase(caption.charAt(0)))
					return new TIdentifier(this.caption);
				throw new SyntaxErrorException(); // for rule expressions (which
													// won't be handled by this
													// function)
			}
		}
	}

	/**
	 * returns a {@link MExpression}: {@link MTreeLangTree} if it is a valid
	 * treelang expression. {@link MWildcard} if it starts with a '.',
	 * {@link MTreeLangNode} if it is a treelang node, but not a valid treelang
	 * tree.
	 * 
	 * @return
	 * @throws SyntaxErrorException
	 */
	public MExpression getMExpression() throws SyntaxErrorException {
		MExpression result = null;
		try { // if its a TreeLangTree
			TPicture tpic = this.getTPic();
			Integer tpicHash = tpic.hashCode();
			TStorage.gI().put(tpicHash, tpic);
			result = new MTreeLangTree(tpicHash);
		} catch (SyntaxErrorException e) {
			MExpression[] childs = new MExpression[this.children.size()];
			for (int i = 0; i < children.size(); i++) {
				childs[i] = children.get(i).getMExpression();
			}
			if (caption.startsWith(".")) {
				result = new MWildcard(caption, childs);
			} else {
				switch (this.caption) { // Alle treelang nodes without children
										// don't need to be handled here,
										// because they will be handled as
										// MTreeLangTree.
				case "List":
					return new MTreeLangNode(TList.class, childs);
				case "Translate":
					if (childs.length != 3)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TTranslate.class, childs);
				case "Lambda":
					if (childs.length != 3)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TLambda.class, childs);
				case "For":
					if (childs.length != 3)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TForLoop.class, childs);
				case "+":
					if (childs.length != 2)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TPlus.class, childs);
				case "-":
					if (childs.length != 2)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TMinus.class, childs);
				case "/":
					if (childs.length != 2)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TDivide.class, childs);
				case "*":
					if (childs.length != 2)
						throw new SyntaxErrorException();
					return new MTreeLangNode(TMultiply.class, childs);
				default: // this should be an alias
					if (Character.isUpperCase(caption.charAt(0)))
						return new MTreeLangNode(AbstractAlias.getAliasType(caption),
								childs, this.caption);
					throw new SyntaxErrorException();
				}
			}

		}
		return result;
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
