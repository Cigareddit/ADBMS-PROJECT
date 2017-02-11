package summation;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String columns[] = value.toString().split(",");
		if (value.toString().contains("Duration") || columns.length < 9) {
			// continue
		} else {

			String type = columns[8].toString();
			word.set(type);
		}
		context.write(word, one);
	}

}
