package dna.examples.batches.sanitization;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.datastructures.GDS;
import dna.graph.generators.random.RandomGraph;
import dna.updates.batch.Batch;
import dna.updates.generators.random.RandomEdgeRemovals;
import dna.util.Log;

/**
 * 
 * Updates cannot be applied more than once: a node/edge cannot be added if it
 * already exists but also not removed if it has already been removed (or never
 * existed).
 * 
 * In this example, we use a simple example of a batch, consisting of a single
 * edge removal update. The first application of this update is successful and
 * the edge is removed from E. The second application of the batch and update
 * fails because the edge that is supposed to be removed does not exist in the
 * graph anymore.
 * 
 * The same problems are caused by repeated node removal, node addition, and
 * edge addition.
 * 
 * @author benni
 *
 */
public class BatchReApplication extends Example {

	public BatchReApplication(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		// initial graph is generated
		Log.infoSep();
		Graph g = (new RandomGraph(GDS.directed(), 3, 2)).generate();
		g.printE();

		// generating the batch
		Log.infoSep();
		Batch b = new RandomEdgeRemovals(1).generate(g);
		b.print();

		// applying the batch
		Log.infoSep();
		b.apply(g);
		g.printE();

		// re-applying the batch
		Log.infoSep();
		b.apply(g);

	}

}
