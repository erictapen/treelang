package treelang.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import treelang.picture.TPicture;

/**
 * Parses an existing treelang file and returns a root object
 * 
 * @author justin
 *
 */
public class Parser {

	public TPicture parse(String infile) throws SyntaxErrorException {
		// turn every line into a PNode
		ArrayList<PNode> cursorList = new ArrayList<PNode>();
		cursorList.add(new PNode("List"));

		try (BufferedReader br = new BufferedReader(new FileReader(infile))) {
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
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + infile + "\" not found. Abort.");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("IOException. Abort.");
			e.printStackTrace();
			return null;
		}
		return cursorList.get(0).getTPic();
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
