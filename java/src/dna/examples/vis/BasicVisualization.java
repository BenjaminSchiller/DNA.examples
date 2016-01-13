package dna.examples.vis;

import dna.visualization.MainDisplay;

/**
 * This example shows basic use of the DNA visualizer. It can be used to
 * visualize the graphs metrics and values over time in two different modes:
 * PlayBack or Live.<br>
 * 
 * In this example the Visualizer will run in PlayBack mode. First a Series is
 * being generated in the default directory.<br>
 * 
 * Next we initialize the MainDisplay with no parameters. This will create a
 * default MainDisplay-window at the default path.<br>
 * 
 * Lastly we take a screenshot.
 * 
 * @author Rwilmes
 * 
 */
public class BasicVisualization extends Vis {

	public BasicVisualization(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		String defaultDir = "data/scenario1/";
		generateSeries(defaultDir);

		MainDisplay display = new MainDisplay();

		screenshot(display, screenshotDir(), "Vis_basic_example",
				screenshotWaitTime());
	}

}
