package average_combiner;

//Average trip duration by Annual Member and Casual Member}
//Time it took using combiner is 6439 milliseconds

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Hubway_Main {

	public static void main(String[] args) throws Exception {

		long starttime = System.currentTimeMillis();
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Hubway_Main_summarization");
		job.setJarByClass(Hubway_Main.class);
		job.setMapperClass(Hubway_Mapper.class);
		job.setCombinerClass(Hubway_Combiner.class);
		job.setReducerClass(Hubway_Reducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.waitForCompletion(true);
		long endtime = System.currentTimeMillis();
		System.out.println("time took to execute using combiner is: "
				+ (endtime - starttime));
		System.exit(0);
	}

}
