package dna.examples.gvis;

import java.io.File;
import java.io.IOException;

import dna.graph.Graph;
import dna.graph.datastructures.GDS;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.canonical.CliqueGraph;
import dna.graph.generators.random.RandomGraph;
import dna.updates.batch.Batch;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.evolvingNetworks.BarabasiAlbertBatch;
import dna.updates.generators.evolvingNetworks.RandomGrowth;

public class BatchVis extends GVis {

	public static final int delay = 10;
	public static final String animatedFilename = "animated.gif";

	public BatchVis(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		init(200, 100, 2000);

		GraphGenerator random = new RandomGraph(GDS.undirected(), 20, 100);
		GraphGenerator clique = new CliqueGraph(GDS.undirected(), 20);
		BatchGenerator rg1 = new RandomGrowth(5, 1);
		BatchGenerator rg3 = new RandomGrowth(5, 3);
		BatchGenerator ba1 = new BarabasiAlbertBatch(5, 1);
		BatchGenerator ba3 = new BarabasiAlbertBatch(5, 3);

		execute("Random-RandomGrowth1", random, rg1, 100);
		execute("Random-PreferentialAttachement1", random, ba1, 100);
		execute("Clique-RandomGrowth1", clique, rg1, 100);
		execute("Clique-PreferentialAttachement1", clique, ba1, 100);
		execute("Random-RandomGrowth3", random, rg3, 100);
		execute("Random-PreferentialAttachement3", random, ba3, 100);
		execute("Clique-RandomGrowth3", clique, rg3, 100);
		execute("Clique-PreferentialAttachement3", clique, ba3, 100);

		exit();
	}

	protected void execute(String name, GraphGenerator gg, BatchGenerator bg,
			int batches) {
		String dir = screenshotDir() + name + "/";
		Graph g = gg.generate();
		screenshot(dir, g.getTimestamp());
		for (int i = 0; i < batches; i++) {
			if (!bg.isFurtherBatchPossible(g)) {
				break;
			}
			Batch b = bg.generate(g);
			b.apply(g);
			screenshot(dir, g.getTimestamp());
		}
		try {
			Runtime.getRuntime().exec(
					"/opt/local/bin/convert -delay " + delay + " *.png "
							+ animatedFilename, new String[0], new File(dir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
