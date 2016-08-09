package treelang;

import java.util.HashMap;

import treelang.picture.TPicture;

/**
 * Efficient storage for treelang nodes. Relies on good hash function.
 * 
 * @author justin
 *
 */
@SuppressWarnings("serial")
public class TStorage extends HashMap<Integer, TPicture> {
	private static TStorage instance = null;

	private TStorage() {

	}

	public static TStorage gI() {
		if (instance == null)
			instance = new TStorage();
		return instance;
	}

	@Override
	public String toString() {
		String res = this.size() + " Nodes in storage:\n";
		for (Entry<Integer, TPicture> x : this.entrySet()) {
			res += x.getKey() + " ==\n";
			res += x.getValue() + "\n";
		}
		res += this.size() + " Nodes in storage\n";
		return res;
	}

}
