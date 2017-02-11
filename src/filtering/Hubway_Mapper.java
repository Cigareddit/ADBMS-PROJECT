package filtering;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper extends Mapper<LongWritable, Text, Text, Text> {

	private static Text one = new Text();
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		if (value.toString().contains("Duration")) {
			// continue
		} else {
			String columns[] = value.toString().split(",");
			if (columns.length > 9) {
				String type = columns[8];
				if (type.equals("Member")) {
					String ziplist = columns[9];
					word.set(type);
					one.set(ziplist);
					context.write(word, one);
				}
			}
		}

	}

}
