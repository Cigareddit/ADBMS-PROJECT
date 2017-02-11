package joinsPattern;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Hubway_Reducer extends
		Reducer<Text, IntWritable, Text, NullWritable> {

	private static HashSet<String> hsout = new HashSet();

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		if (hsout.contains(key.toString())) {
			// do nothing
		} else {
			hsout.add(key.toString());
		}

	}

	// Clean up method called only once after all.

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		System.out.println("hellooooooo");
		Iterator r = hsout.iterator();
		while (r.hasNext()) {
			context.write(new Text(r.next().toString()), null);
		}
	}

}