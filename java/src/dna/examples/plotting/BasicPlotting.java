package dna.examples.plotting;

import java.io.IOException;

import dna.examples.Example;
import dna.graph.datastructures.GDS;
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
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.util.Config;

// TODO write
public class BasicPlotting extends Example {

	public BasicPlotting(String[] args) {
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

		int runs = 1;
		int batches = 10;
		try {
			SeriesData sd = s.generate(runs, batches);
			Plotting.plot(sd, plotDir());
		} catch (AggregationException | IOException
				| MetricNotApplicableException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
