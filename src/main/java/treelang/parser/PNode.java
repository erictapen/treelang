package treelang.parser;

import java.util.ArrayList;

public class PNode {
	private String caption;
	
	private ArrayList<PNode> children;

	public PNode(String caption) {
		super();
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
	
}
