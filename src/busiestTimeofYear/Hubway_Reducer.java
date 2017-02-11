package busiestTimeofYear;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Hubway_Reducer extends
		Reducer<Text, IntWritable, Text, IntWritable> {
	
	private static TreeMap<Integer, String> tm = new TreeMap();

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int count = 0;
		for (IntWritable val : values) {
			count += val.get();
		}

		tm.put(count, key.toString());

	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		System.out.println("hellooooooo");
		for (Map.Entry<Integer, String> mp : tm.descendingMap().entrySet()) {
			String hour = mp.getValue();
			context.write(new Text(hour), null);
		}
	}

}
