package topTenPattern;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Hubway_Reducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {

	private static TreeMap<Long, String> newMap = new TreeMap<>();
	private static MySingleton msingle = MySingleton.getInstance();

	@Override
	public void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {

		long sum = 0;
		for (LongWritable val : values) {
			sum += val.get();
		}

		TreeMap<Long, String> newMap = new TreeMap<>();
		newMap = msingle.getR();
		newMap.put(sum, key.toString());
		msingle.setR(newMap);

		System.out.println("The size is:" + newMap.size());
		if (newMap.size() > 10) {
			newMap.remove(newMap.firstKey());
		}

	}

	// Clean up method called only once after all.

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		System.out.println("hellooooooo");
		for (Map.Entry<Long, String> mp : msingle.getR().descendingMap()
				.entrySet()) {

			context.write(new Text(mp.getValue()),
					new LongWritable(mp.getKey()));
		}
	}

}