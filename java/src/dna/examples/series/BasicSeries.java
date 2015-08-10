package dna.examples.series;

import java.io.IOException;

import dna.examples.Example;
import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.random.RandomGraph;
import dna.metrics.Metric;
import dna.metrics.MetricNotApplicableException;
import dna.metrics.degree.DegreeDistributionR;
import dna.series.AggregationException;
import dna.series.Series;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.util.Config;

// TODO write
public class BasicSeries extends Example {

	public BasicSeries(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		Config.zipNone();

		GraphDataStructure gds = GDS.directed();
		GraphGenerator gg = new RandomGraph(gds, 100, 200);
		BatchGenerator bg = new RandomBatch(0, 0, 20, 10);
		Metric[] metrics = new Metric[] { new DegreeDistributionR() };
		String dir = dataDir();
		String name = "Example of a basic series";

		Series s = new Series(gg, bg, metrics, dir, name);

		int runs = 3;
		int batches = 10;
		try {
			s.generate(runs, batches);
		} catch (AggregationException | IOException
				| MetricNotApplicableException e) {
			e.printStackTrace();
		}
	}

}
