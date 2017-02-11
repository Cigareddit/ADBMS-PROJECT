package popularOriginDestinationPairs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private IntWritable one = new IntWritable(1);
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String columns[] = value.toString().split(",");
		if (columns.length > 6 && !value.toString().contains("Duration")) {

			String start_station = columns[3].toString();
			String end_station = columns[5].toString();
			String pair = start_station + "_" + end_station;
			word.set(pair);
			context.write(word, one);
		}
	}
}
