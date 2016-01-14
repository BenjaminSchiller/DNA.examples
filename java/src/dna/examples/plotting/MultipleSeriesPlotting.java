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

/**
 * 
 * This class provides a simple demonstration of how to plot and compare
 * multiple graphs in DNA.<br>
 * 
 * First the config is set to zip all generated runs. Then all necessary objects
 * for the generation are being prepared:<br>
 * 
 * The graph datastructure to be used. In this case a directed graph.<br>
 * 
 * An array of metrics, which will be used in both series. It includes a
 * DegreeDistribution and a DirectedClusteringCoefficient.<br>
 * 
 * The first series is called "s1" and consists of a random directed graph with
 * 100 nodes and 200 edges. In each batch 20 edges will be added and 10 edges
 * will be removed randomly. <br>
 * 
 * The second series is called "s2" and consists of a random directed graph with
 * 200 nodes and 300 edges. In each batch 30 edges will be added and 15 edges
 * will be removed randomly. <br>
 * 
 * Then the actual generations takes place with 1 run and 10 batches each.
 * Lastly an array of both generated SeriesData objects is handed over to the
 * plotting method. This will cause both series to be plotted in the same
 * directory. Similar metrics and values in both series will automatically be
 * merged in the same plots.
 * 
 * 
 * @author Rwilmes
 * 
 */
public class MultipleSeriesPlotting extends Example {

	public MultipleSeriesPlotting(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		Config.zipRuns();
		String dir = dataDir();
		GraphDataStructure gds = GDS.directed();
		Metric[] metrics = new Metric[] { new DegreeDistributionR(),
				new DirectedClusteringCoefficientR() };

		String name1 = "s1";
		GraphGenerator gg1 = new RandomGraph(gds, 100, 200);
		BatchGenerator bg1 = new RandomBatch(0, 0, 20, 10);
		Series s1 = new Series(gg1, bg1, metrics, dir + name1 + "/", name1);

		String name2 = "s2";
		GraphGenerator gg2 = new RandomGraph(gds, 200, 300);
		BatchGenerator bg2 = new RandomBatch(0, 0, 30, 15);
		Series s2 = new Series(gg2, bg2, metrics, dir + name2 + "/", name2);

		int runs = 1;
		int batches = 10;
		try {
			SeriesData sd1 = s1.generate(runs, batches);
			SeriesData sd2 = s2.generate(runs, batches);
			SeriesData[] series = new SeriesData[] { sd1, sd2 };
			Plotting.plot(series, plotDir());
		} catch (AggregationException | IOException
				| MetricNotApplicableException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
