package popularOriginDestinationPairs;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Hubway_Reducer extends Reducer<Text, IntWritable, Text, Text> {

	private static TreeMap<Long, String> newMap = new TreeMap<>();
	private static MySingleton msingle = MySingleton.getInstance();

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}

		TreeMap<Integer, String> newMap = new TreeMap<>();
		newMap = msingle.getR();
		newMap.put(sum, key.toString());
		msingle.setR(newMap);

		System.out.println("The size is:" + newMap.size());
		if (newMap.size() > 20) {
			newMap.remove(newMap.firstKey());
		}

	}

	// Clean up method called only once after all.

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		System.out.println("hellooooooo");
		for (Map.Entry<Integer, String> mp : msingle.getR().descendingMap()
				.entrySet()) {

			String stations[] = mp.getValue().split("_");
			context.write(new Text(stations[0]), new Text(stations[1]));
		}
	}

}