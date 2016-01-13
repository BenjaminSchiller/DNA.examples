package dna.examples.vis;

import dna.visualization.MainDisplay;

/**
 * This example shows basic use of the DNA visualizer. It can be used to
 * visualize the graphs metrics and values over time in two different modes:
 * PlayBack or Live.<br>
 * 
 * In this example the Visualizer will run in PlayBack mode. First a Series is
 * being generated in the dataDir().<br>
 * 
 * Next we initialize the MainDisplay with a configPath, which points to the
 * MainDisplay-Configuration file to be used, as well as the data directory
 * where the first run will be located. Since the generation takes place in
 * dataDir, the first run will be located in dataDir + "run.0/".<br>
 * 
 * Lastly we take a screenshot.
 * 
 * @author Rwilmes
 * 
 */
public class VisualizationWithConfig extends Vis {

	public VisualizationWithConfig(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		generateSeries(dataDir());

		String configPath = "config/vis/config_1024_x_640.cfg";
		MainDisplay display = new MainDisplay(configPath, dataDir() + "run.0/");

		screenshot(display, screenshotDir(), "Vis_config_example",
				screenshotWaitTime());
	}

}
