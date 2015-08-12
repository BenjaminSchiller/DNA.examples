package dna.examples.gvis;

import java.io.File;

import dna.examples.Example;
import dna.visualization.graph.GraphVisualization;

public abstract class GVis extends Example {

	public long waitBeforeScreenshot = 100;
	public long waitAfterScreenshot = 100;
	public long waitBeforeExit = 2000;

	public GVis(String[] args) {
		super(args);
	}

	protected void init(long waitBeforeScreenshot, long waitAfterScreenshot,
			long waitBeforeExit) {
		GraphVisualization.enable();
		this.waitBeforeScreenshot = waitBeforeScreenshot;
		this.waitAfterScreenshot = waitAfterScreenshot;
		this.waitBeforeExit = waitBeforeExit;
	}

	protected void screenshot(String dir, long timestamp) {
		String index = "" + timestamp;
		if (timestamp < 10)
			index = "000" + timestamp;
		else if (timestamp < 100)
			index = "00" + timestamp;
		else if (timestamp < 1000)
			index = "0" + timestamp;
		else if (timestamp < 10000)
			index = "" + timestamp;
		screenshot(dir, index);
	}

	protected void screenshot(String dir, String filename) {
		(new File(dir)).mkdirs();
		sleep(waitBeforeScreenshot);
		String dest = dir + filename + ".png";
		System.out.println("    => " + dest);
		GraphVisualization.getCurrentGraphPanel().getGraph()
				.addAttribute(GraphVisualization.screenshotsKey, dest);
		sleep(waitAfterScreenshot);
		if (!(new File(dest)).exists()) {
			System.out.println("    screenshot was NOT generated ! ! !");
		}
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void exit() {
		sleep(waitBeforeExit);
		System.exit(0);
	}

}
