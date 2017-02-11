package daysMostTraffic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Hubway_Mapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		if (value.toString().contains("Duration")) {
			// continue
		} else {
			String columns[] = value.toString().split(",");
			String MyDateString = columns[1];

			// using SimpleDateFormat class to parse the string to dateformat
			SimpleDateFormat c = new SimpleDateFormat("M/dd/yyyy");
			Date d = new Date();
			try {
				d = c.parse(MyDateString);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// using SimpleDateFormat class to format the date to string
			SimpleDateFormat e = new SimpleDateFormat("EEE", Locale.US);
			String asWeek = e.format(d);

			word.set(asWeek);
			context.write(word, one);
		}

	}
}
