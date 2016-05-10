package treelang.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import treelang.picture.TPicture;

/** Parses an existing treelang file and returns a root object
 * @author justin
 *
 */
public class Parser {
	
	
	
	public TPicture parse(String infile) {
		//turn every line into a PNode
		int level = 0;
		PNode cursor = new PNode("Picture");
		ArrayList<PNode> cursorList = new ArrayList<PNode>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(infile))) {
			String line;
			int i=0;
			while ((line = br.readLine()) != null) {
				int lvl = determineLevel(line);
				cursor.addChild(new PNode());
				i++;
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
		return null;
	}

	private int determineLevel(String line) {
		int res = 0;
		while(line.startsWith("/t")) res++;
		return res;
	}
}
