package topTenPattern;

import java.util.TreeMap;

public class MySingleton {

	private static MySingleton instance = null;
	private static TreeMap<Long, String> r = null;

	protected MySingleton() {
		r = new TreeMap();
	}

	public static MySingleton getInstance() {
		if (instance == null) {
			instance = new MySingleton();
		}
		return instance;
	}

	public void setR(TreeMap<Long, String> f) {
		r = f;
	}

	public TreeMap<Long, String> getR() {
		return r;
	}

}
