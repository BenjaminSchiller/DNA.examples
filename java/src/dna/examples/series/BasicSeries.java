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

/**
 * 
 * A series contains object holds all the components required to execute the
 * analysis of a dynamic graph with a specified list of metrics. Hence, it
 * holds:
 * 
 * <i>GraphGenerator</i> - generates the initial instance of the dynamic graph
 * 
 * <i>BatchGenerator</i> - generates the stream of updates grouped to batches
 * 
 * <i>Metrics</i> - list of metrics to be analyzed for each timestamp
 * 
 * <i>Dir</i> - target directory where the results should be stored
 * 
 * <i>Name</i> - name of the series (used for plots and logging)
 * 
 * For the generattion of such a series, two parameters are required:
 * 
 * <i>runs</i> - the number of times the analysis is executed
 * 
 * <i>batches</i> - the (maximum) number of batches to be generated (less
 * batches might be generated and applied in case the batch generator determines
 * that is cannot generate further updates)
 * 
 * The output of such a generation is then found in <i>Dir</i>: one folder per
 * run (run.0, run.1, ...) as well as an aggregation folder that holds the
 * aggregation of all runs (aggr). Each of those holds a semerate folder for
 * results and statistics for the initial graph (batch.0) as well as all further
 * snapshots induced by the applications of each batch (batch.1, batch.2, ...).
 * 
 * Each result dir contains statistics abount the batch (___stats.values), the
 * runtimes of all metrics (___metric.runtimes), and general runtimes like
 * overhead, total, and batch generation (___general.runtimes). For each metric,
 * a separate dir is created for each metric which contains the analysis results
 * of the graph at the respective timestamp. All values are written to a single
 * file (values.values) while each distribution, node value list and node-node
 * value list is stored in a separate file.
 * 
 * @author benni
 *
 */
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
