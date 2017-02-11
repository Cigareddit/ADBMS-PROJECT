package joinsPattern;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private IntWritable one = new IntWritable(1);
	private Text word = new Text();
	private static HashSet<String> hs = new HashSet();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String columns[] = value.toString().split(",");
		if (columns.length > 6 && !value.toString().contains("Duration")) {
			String start_station = columns[3].toString();
			word.set(start_station);
			if (hs.contains(word.toString())) {
				// do not do anything
			} else {
				// add to the hashmap
				hs.add(word.toString());
			}
		}
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		System.out.println("hellooooooo");
		Iterator r = hs.iterator();
		while (r.hasNext()) {
			context.write(new Text(r.next().toString()), one);

		}
	}
}
