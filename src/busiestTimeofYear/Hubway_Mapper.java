package busiestTimeofYear;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			SimpleDateFormat sd = new SimpleDateFormat("M/dd/yyyy HH:mm");
			Date d = null;
			try {
				d = sd.parse(MyDateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SimpleDateFormat hr = new SimpleDateFormat("HH");
			String h = hr.format(d);
			word.set(h);
			context.write(word, one);
		}

	}
}
