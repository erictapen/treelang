package treelang.parser;

import java.util.ArrayList;

import treelang.picture.TLine;
import treelang.picture.TList;
import treelang.picture.TNumber;
import treelang.picture.TPicture;
import treelang.picture.TPoint;

public class PNode {
	private String caption;
	
	private ArrayList<PNode> children = new ArrayList<PNode>();

	public PNode(String caption) {
		super();
		//strip caption from whitespace and comments
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

	public TPicture getTPic() throws SyntaxErrorException{
		ArrayList<TPicture> picChilds = new ArrayList<TPicture>();
		for(PNode x : this.children) picChilds.add(x.getTPic());
		switch(this.caption) {
			case "List":
				return new TList(picChilds);
			case "Line":
				if(picChilds.size()!=2) throw new SyntaxErrorException();
				return new TLine(picChilds.get(0), picChilds.get(1));
			case "Point":
				if(picChilds.size()!=2) throw new SyntaxErrorException();
				return new TPoint(picChilds.get(0), picChilds.get(1));
			default:
				return new TNumber(Integer.decode(caption));
		}
	}
	
	@Override
	public String toString() {
		String res = "";
		for(PNode x : this.children) res += x.getCaption() + " is child of " + this.caption + "\n";
		for(PNode x : this.children) res += x.toString();
		return res;
	}
	
	
	
}