package treelang;

import java.util.HashMap;

import treelang.picture.TPicture;

@SuppressWarnings("serial")
public class TStorage extends HashMap<Integer, TPicture> {
	private static TStorage instance = null;

	private TStorage() {

	}

	public static TStorage getInstance() {
		if (instance == null)
			instance = new TStorage();
		return instance;
	}

	@Override
	public String toString() {
		String res = "TStorage:\n";
		for(Entry<Integer, TPicture> x : this.entrySet()) {
			res += x.getKey() + " ==\n";
			res += x.getValue() + "\n";
		}
		return res;
	}
	
	
}
