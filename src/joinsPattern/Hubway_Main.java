package joinsPattern;

//{Numer of new start stations that were added in the second part of the financial year}
//Comparing two files of 2014 year and getting the stations that were added.

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Hubway_Main {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,
				"Performing joins on two start_stations");
		// setting the job configuration
		job.getConfiguration().set("join.type", args[2]);
		job.setJarByClass(Hubway_Main.class);
		job.setMapperClass(Hubway_Mapper.class);
		job.setReducerClass(Hubway_Reducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, Hubway_Mapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, Hubway_Mapper.class);

		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		job.waitForCompletion(true);
		System.exit(0);

	}

}
