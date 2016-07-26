package treelang.mutate;

import java.util.ArrayList;

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

	public Mutator() {

	}

	public void addRule(MRule rule) {
		this.randomRuleset.add(rule);
	}

	public Integer mutate(Integer origin) {
		// if no MIDI event happened
		if(randomRuleset.size()==0)
			return origin;
		int rnd = (int)(Math.random() * randomRuleset.size());
		return this.randomRuleset.get(rnd).apply(origin);
	}
}
