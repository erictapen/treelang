package treelang.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Parses an existing treelang file and returns a root object
 * 
 * @author justin
 *
 */
public class TreeLangParser {

	public PNode parse(File infile) throws SyntaxErrorException {
		try (BufferedReader br = new BufferedReader(new FileReader(infile))) {
			return parse(br);
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + infile + "\" not found. Abort.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("IOException. Abort.");
			e.printStackTrace();
			return null;
		}
	}

	public PNode parse(String text) throws SyntaxErrorException {
		try (BufferedReader br = new BufferedReader(new StringReader(text))) {
			return parse(br);
		} catch (IOException e) {
			System.out.println("IOException. Abort.");
			e.printStackTrace();
			return null;
		}
	}

	private PNode parse(BufferedReader br) throws IOException, SyntaxErrorException {
		// turn every line into a PNode
		ArrayList<PNode> cursorList = new ArrayList<PNode>();
		cursorList.add(new PNode("List"));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.trim().isEmpty())
				continue;
			int lvl = determineLevel(line);
			PNode temp = new PNode(line);
			while (cursorList.size() <= lvl)
				cursorList.add(null);
			cursorList.set(lvl, temp);
			if (cursorList.get(lvl - 1) != null)
				cursorList.get(lvl - 1).addChild(temp);
		}
		//return the first child of the implicit list. makes sense, I promise.
		return cursorList.get(0).getChildren().get(0);
	}

	private int determineLevel(String line) {
		int res = 1;
		while (line.startsWith("\t")) {
			line = line.substring(1);
			res++;
		}
		return res;
	}
}
