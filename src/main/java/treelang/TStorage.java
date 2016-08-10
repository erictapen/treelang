package treelang;

import java.util.HashMap;
import java.util.HashSet;
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

	private HashSet<Integer> aliasHashes = new HashSet<Integer>();

	private TStorage() {

	}

	public static TStorage gI() {
		if (instance == null)
			instance = new TStorage();
		return instance;
	}

	public TPicture[] getAll(Integer[] keys) {
		TPicture[] result = new TPicture[keys.length];
		for (int i = 0; i < keys.length; i++)
			result[i] = this.get(keys[i]);
		return result;
	}

	public TPicture putNode(Integer key, TPicture val) {
		if (!aliasHashes.contains(key))
			this.put(key, val);
		return val;
	}

	public TPicture forcePutNode(Integer key, TPicture val) {
		return this.put(key, val);
	}

	public void registerAliasHash(Integer hash) {
		aliasHashes.add(hash);
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
