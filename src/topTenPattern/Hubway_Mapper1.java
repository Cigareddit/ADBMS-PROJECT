package topTenPattern;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper1 extends
		Mapper<LongWritable, Text, LongWritable, Text> {

	private LongWritable one = new LongWritable();
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String columns[] = value.toString().split("\t");
		one.set(Long.parseLong(columns[1]));
		word.set(columns[0].toString());

		context.write(one, word);
	}
}