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
}
