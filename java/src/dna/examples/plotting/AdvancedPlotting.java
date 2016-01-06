package dna.examples.plotting;

import java.io.IOException;
import java.util.HashMap;

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
import dna.plot.PlottingConfig;
import dna.plot.PlottingConfig.PlotFlag;
import dna.plot.PlottingConfig.ValueSortMode;
import dna.plot.data.PlotData.DistributionPlotType;
import dna.plot.data.PlotData.PlotStyle;
import dna.plot.data.PlotData.PlotType;
import dna.series.AggregationException;
import dna.series.Series;
import dna.series.data.SeriesData;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.util.Config;

/**
 * 
 * This class provides an advanced demonstration of the plotting-mechanisms
 * available in DNA. It shows multiple customizations one can use to fashion the
 * plots in order to fit specific purposes.<br>
 * 
 * The graph- and batch-generation is set up in the same way as in the other
 * examples. <br>
 * 
 * The actual plotting configuration takes place when the PlottingConfig object
 * "pconfig" is being initialized.<br>
 * 
 * PlottingConfig objects are powerful configuration objects, which hold lots of
 * methods to alter the plotting process. Handing one of these to the
 * Plotting-methods will change the plottings behaviour to everything that is
 * configured in the PlottingConfig. <br>
 * 
 * In this case the PlottingConfig is being initialized with the two PlotFlag's
 * "plotStatistics" and "plotMetricEntirely". This will cause the Plotting to
 * only plot statistics and metrics. This means no runtimes will be included in
 * any of the plots. <br>
 * 
 * Next the actual plot-styling is being altered. The PlotStyle is set to lines
 * (default is linespoint) and the PlotType is set to minimum (default is
 * average). In addition the order values will be plotted and appear in the
 * legend is set to be alphabetic.<br>
 * 
 * Next the distribution plot type is being set to "dist and cdf". This means
 * two plots will be generated for each distribution. One as a cumulative
 * distribution function. <br>
 * 
 * Lastly a timestamp map is being added. This maps the timestamps 0,1,2,3 to
 * 60,120,180,240. Since there are only 3 batches there wont be any other
 * timestamps.<br>
 * 
 * 
 * Then the actual generation takes place with 1 run and 3 batches. Lastly the
 * generated series will be plotted to the plotDir. Note how the PlottingConfig
 * is handed over as a parameter. This is basically possible with every
 * plotting-method available including run-plots.<br>
 * 
 * For even more sophisticated ways to customize plotting check out the plotting
 * manual.
 * 
 * 
 * @author Rwilmes
 * 
 */
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

		PlottingConfig pconfig = new PlottingConfig(PlotFlag.plotStatistics,
				PlotFlag.plotMetricEntirely);
		pconfig.setPlotStyle(PlotStyle.lines);
		pconfig.setPlotType(PlotType.median);
		pconfig.setValueSortMode(ValueSortMode.ALPHABETICAL);

		pconfig.setDistPlotType(DistributionPlotType.distANDcdf);

		HashMap<Long, Long> map = new HashMap<Long, Long>();
		map.put(0L, 60L);
		map.put(1L, 120L);
		map.put(2L, 180L);
		map.put(3L, 240L);
		pconfig.setTimestampMap(map);

		int runs = 1;
		int batches = 3;
		try {
			SeriesData sd = s.generate(runs, batches);
			Plotting.plot(sd, plotDir(), pconfig);
		} catch (AggregationException | IOException
				| MetricNotApplicableException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
