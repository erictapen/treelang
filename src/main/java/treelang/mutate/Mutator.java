package treelang.mutate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import treelang.parser.RuleParser;
import treelang.parser.SyntaxErrorException;

/**
 * Manages rules and their use cases. For example Mutator can choose a rule
 * randomly or apply it only if a certain MIDI signal is sent.
 * 
 * @author justin
 *
 */
public class Mutator {

	/**
	 * rules with same probability
	 * 
	 */
	private ArrayList<MRule> randomRuleset = new ArrayList<MRule>();

	public Mutator(String filepath) {
		try {
			randomRuleset.addAll((new RuleParser()).parse(new File(filepath)));
		} catch (IOException | SyntaxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addRule(MRule rule) {
		this.randomRuleset.add(rule);
	}

	public Integer mutate(Integer origin) {
		// if no MIDI event happened
		if (randomRuleset.size() == 0)
			return origin;
		int rnd = (int) (Math.random() * randomRuleset.size());
		return this.randomRuleset.get(rnd).apply(origin);
	}

	@Override
	public String toString() {
		if (randomRuleset.isEmpty())
			return "Mutator doesn't have any rules";
		String result = "";
		for (MRule x : randomRuleset) {
			result += x.toString() + "\n";
		}
		return result;
	}
}
