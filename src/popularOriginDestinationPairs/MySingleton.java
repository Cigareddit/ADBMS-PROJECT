package popularOriginDestinationPairs;

import java.util.TreeMap;

public class MySingleton {

	private static MySingleton instance = null;
	private static TreeMap<Integer, String> r = null;

	protected MySingleton() {
		r = new TreeMap();
	}

	public static MySingleton getInstance() {
		if (instance == null) {
			instance = new MySingleton();
		}
		return instance;
	}

	public void setR(TreeMap<Integer, String> f) {
		r = f;
	}

	public TreeMap<Integer, String> getR() {
		return r;
	}

}
