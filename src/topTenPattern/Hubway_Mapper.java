package topTenPattern;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper extends
		Mapper<LongWritable, Text, Text, LongWritable> {

	private LongWritable one = new LongWritable();
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String columns[] = value.toString().split(",");
		if (columns.length > 8 && !value.toString().contains("Duration")) {

			long duration = Long.parseLong(columns[0]);
			String bike_id = columns[7];
			word.set(bike_id);
			one.set(duration);
			context.write(word, one);
		}
	}
}
