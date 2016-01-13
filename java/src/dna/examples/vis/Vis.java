package dna.examples.vis;

import java.io.File;
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
import dna.series.AggregationException;
import dna.series.Series;
import dna.series.data.SeriesData;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.visualization.MainDisplay;
import dna.visualization.VisualizationUtils;

public abstract class Vis extends Example {

	public Vis(String[] args) {
		super(args);
	}

	protected void generateSeries(String dir) {
		File f = new File(dir + "run.0/");
		if (f.exists())
			return;

		try {
			GraphDataStructure gds = GDS.directed();
			GraphGenerator gg = new RandomGraph(gds, 100, 200);
			BatchGenerator bg = new RandomBatch(0, 0, 20, 10);
			Metric[] metrics = new Metric[] { new DegreeDistributionR(),
					new DirectedClusteringCoefficientR() };
			String name = "name";
			Series s = new Series(gg, bg, metrics, dir, name);

			int runs = 1;
			int batches = 10;

			SeriesData sd = s.generate(runs, batches);
		} catch (AggregationException | IOException
				| MetricNotApplicableException e) {
			e.printStackTrace();
		}
	}

	protected long screenshotWaitTime = 300;

	protected long screenshotWaitTime() {
		return screenshotWaitTime;
	}

	protected void screenshot(MainDisplay display, String dir, String filename) {
		VisualizationUtils.captureScreenshot(display, dir, filename);
	}

	protected void screenshot(MainDisplay display, String dir, String filename,
			long millis) {
		VisualizationUtils.captureScreenshotInMilliseconds(display, dir,
				filename, millis);
	}

}
