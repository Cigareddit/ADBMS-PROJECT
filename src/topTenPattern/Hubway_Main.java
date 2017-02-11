package topTenPattern;

//{Top 10 vehicles used by duration}

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Hubway_Main {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Top Ten vehicles used");
		job.setJarByClass(Hubway_Main.class);
		job.setMapperClass(Hubway_Mapper.class);
		job.setReducerClass(Hubway_Reducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		Path p = new Path(args[1]);
		FileOutputFormat.setOutputPath(job, p);
		job.waitForCompletion(true);

		/*
		 * Job job1 = Job.getInstance(conf, "Top Ten vehicles used part2");
		 * job1.setJarByClass(Hubway_Main.class);
		 * job1.setMapperClass(Hubway_Mapper1.class);
		 * job1.setMapOutputKeyClass(LongWritable.class);
		 * job1.setMapOutputValueClass(Text.class);
		 * FileInputFormat.addInputPath(job1, p);
		 * 
		 * FileOutputFormat.setOutputPath(job1, new Path(p.toString() +
		 * "final2")); job.waitForCompletion(true);
		 */

		System.exit(0);

	}

}
