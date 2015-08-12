package dna.examples.series;

import java.io.IOException;

import dna.examples.Example;
import dna.graph.datastructures.GDS;
import dna.graph.generators.random.RandomGraph;
import dna.metrics.Metric;
import dna.metrics.MetricNotApplicableException;
import dna.metrics.degree.DegreeDistributionR;
import dna.series.AggregationException;
import dna.series.Series;
import dna.updates.generators.random.RandomBatch;
import dna.util.Config;

/**
 * 
 * When generating the data for a series, a large number of files might be
 * generated. Since the generation of a large number of files can cause problems
 * (e.g., running out of inodes), it is possible to write the results into zip
 * files at different granularity.
 * 
 * <i>Config.zipNone()</i> - no zips are created.
 * 
 * <i>Config.zipBatches()</i> - a zip is created for each batch.
 * 
 * <i>Config.zipRuns()</i> - a zip is created for each run.
 * 
 * While the impact on runtime is low for small zip file sizes, it can grow
 * drastically as the archives become larger. Especially reading from suck a zip
 * file (e.g., for aggregation or plotting) can be quite slow. Therefore, it may
 * greatly increase performance to generate and plot data without any zip files
 * and zip them afterwards.
 * 
 * In the example, the same series is generated with each of the three
 * possibilities mentioned before (with 3 runs and 10 batches each).
 * 
 * @author benni
 *
 */
public class ZippingSeries extends Example {

	public ZippingSeries(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		Config.zipNone();
		this.execute("nothingZipped");
		Config.zipBatches();
		this.execute("batchesZipped");
		Config.zipRuns();
		this.execute("runsZipped");
	}

	protected void execute(String name) {
		Series s = new Series(new RandomGraph(GDS.undirected(), 10, 20),
				new RandomBatch(1, 0, 10, 3),
				new Metric[] { new DegreeDistributionR() }, dataDir() + name
						+ "/", name);
		try {
			s.generate(3, 10);
		} catch (AggregationException | IOException
				| MetricNotApplicableException e) {
			e.printStackTrace();
		}
	}
}
