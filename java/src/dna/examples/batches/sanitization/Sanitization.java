package dna.examples.batches.sanitization;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.datastructures.GDS;
import dna.graph.generators.random.RandomGraph;
import dna.updates.batch.Batch;
import dna.updates.batch.BatchSanitization;
import dna.updates.batch.BatchSanitizationStats;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.util.Log;

/**
 * 
 * In case batch generators are combined arbitrarily (or not well implemented),
 * it can happen that certain updates contained in a batch are useless /
 * meaningless and their application could cause problems, errors, or
 * inconsistencies.
 * 
 * Resolving such issues is the task of the batch sanitization component. It
 * handles the following problematic updates: (NR) node removal of non-existing
 * node, (ER) edge removal of non-existing edge, (NA) node addition of existing
 * node, (EA) edge addition of existing edge, (NW) node weight change of
 * non-existing node, and (EW) edge weight change of non-existing edge. In case
 * such updates are found, they are removed from the batch.
 * 
 * Similarly, the batch itself is checked for consistency. If a node is removed,
 * all edges connected to it are implicitly removed as well. Therefore, all edge
 * removal updates are removed from the batch in case they are connected to a
 * node which is removed as well. Analogously, edge removal updates are stripped
 * from the batch in case they introduce an edge to a node which is also removed
 * as part of the batch.
 * 
 * The last two cases are shown in this example. First, an undirected random
 * graph with 10 nodes and 20 edges is generated. Then, a random graph with 4
 * node removals, 10 edge additions, and 11 edge removals is generated. As a
 * large part of nodes is removed, the probability is high that some of them are
 * connected to the randomly chosen edges to be removed as well as to the
 * randomly generated new edges. After sanitization, these obsolete updates are
 * removed.
 * 
 * @author benni
 *
 */
public class Sanitization extends Example {

	public Sanitization(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		Graph g = new RandomGraph(GDS.undirected(), 10, 20).generate();

		BatchGenerator bg = new RandomBatch(0, 4, 10, 11);

		Batch b = bg.generate(g);

		Log.infoSep();
		b.print();
		BatchSanitizationStats stats = BatchSanitization.sanitize(b);
		Log.infoSep();
		Log.info("====>");
		Log.infoSep();
		b.print();
		Log.infoSep();
		System.out.println(stats.toString());
		Log.infoSep();
	}

}
