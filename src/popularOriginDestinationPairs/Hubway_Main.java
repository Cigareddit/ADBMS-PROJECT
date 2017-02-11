package popularOriginDestinationPairs;

//{Top 10 Most Popular origin and destination pairs}

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Hubway_Main {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,
				"Top Ten Popular origin/Destinations pairs");
		job.setJarByClass(Hubway_Main.class);
		job.setMapperClass(Hubway_Mapper.class);
		job.setReducerClass(Hubway_Reducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		Path p = new Path(args[1]);
		FileOutputFormat.setOutputPath(job, p);
		job.waitForCompletion(true);
		System.exit(0);

	}

}
