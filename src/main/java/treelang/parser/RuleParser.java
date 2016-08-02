package treelang.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import treelang.mutate.MRule;
import treelang.mutate.MSimple;

/**
 * Parser for .rule files.
 * 
 * @author justin
 *
 */
public class RuleParser {

	public RuleParser() {

	}

	public ArrayList<MRule> parse(String text) throws IOException, SyntaxErrorException {
		ArrayList<MRule> result = new ArrayList<MRule>();
		TreeLangParser p = new TreeLangParser();
		while (!text.isEmpty()) {
			int originend = text.indexOf("\n-->\n");
			int targetend = text.indexOf("\n\n");
			if (targetend == -1)
				targetend = text.length() - 1;
			Integer origin = p.parse(text.substring(0, originend));
			Integer target = p.parse(text.substring(originend + 5, targetend));
			result.add(new MRule(new MSimple(origin), new MSimple(target))); // TODO
			text = text.substring(targetend + 2);
		}
		return result;
	}

	public ArrayList<MRule> parse(File file) throws IOException, SyntaxErrorException {
		String cat = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			cat += line + "\n";
		}
		br.close();
		return this.parse(cat);
	}

}
