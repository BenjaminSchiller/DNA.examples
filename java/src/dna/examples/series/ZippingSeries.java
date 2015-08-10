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

// TODO write
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
