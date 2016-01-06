package dna.examples.plotting;

import java.io.IOException;

import dna.examples.Example;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.random.RandomGraph;
import dna.metrics.Metric;
import dna.metrics.MetricNotApplicableException;
import dna.metrics.clustering.DirectedClusteringCoefficientR;
import dna.metrics.degree.DegreeDistributionR;
import dna.plot.Plotting;
import dna.series.AggregationException;
import dna.series.Series;
import dna.series.data.SeriesData;
import dna.test.gds.GDS;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.util.Config;

/**
 * 
 * This class provides another example of the plotting available in DNA. The
 * focus lies on plotting only a single run, even thou multiple runs are
 * available. This can be useful when one wants to display data from a specific
 * run only.<br>
 * 
 * First the config is set to zip all generated runs. Then all necessary objects
 * for the generation are being prepared:<br>
 * 
 * A GraphGenerator, which generates a random directed graph with 100 nodes and
 * 200 edges.<br>
 * 
 * A BatchGenerator, which randomly adds 20 and removes 10 edges on each update.<br>
 * 
 * An array of metrics, which will be calculated in each batch. Namely a
 * DegreeDistribution and a DirectedClusteringCoefficient.<br>
 * 
 * Then the actual generation takes place with 3 run and 10 batches. Anyhow the
 * plotRun(SeriesData sd, int runId, String plotDir)-method is called. This will
 * cause the plotting process to only plot the second run (with runId = 1).
 * 
 * 
 * @author Rwilmes
 * 
 */
public class SingleRunPlotting extends Example {

	public SingleRunPlotting(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		Config.zipRuns();

		GraphDataStructure gds = GDS.directed();
		GraphGenerator gg = new RandomGraph(gds, 100, 200);
		BatchGenerator bg = new RandomBatch(0, 0, 20, 10);
		Metric[] metrics = new Metric[] { new DegreeDistributionR(),
				new DirectedClusteringCoefficientR() };
		String dir = dataDir();
		String name = "name";

		Series s = new Series(gg, bg, metrics, dir, name);

		int runs = 3;
		int batches = 10;
		int runId = 1
		try {
			SeriesData sd = s.generate(runs, batches);
			Plotting.plotRun(sd, runId, dir + "plots2/");
		} catch (AggregationException | IOException
				| MetricNotApplicableException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
