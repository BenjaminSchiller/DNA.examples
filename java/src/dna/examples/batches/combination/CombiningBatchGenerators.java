package dna.examples.batches.combination;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.datastructures.GDS;
import dna.graph.generators.random.RandomGraph;
import dna.updates.batch.Batch;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomBatch;
import dna.updates.generators.random.RandomEdgeAdditions;
import dna.updates.generators.random.RandomNodeAdditions;
import dna.updates.generators.util.BatchCombinator;
import dna.util.Log;

/**
 * 
 * Using the batch generator BatchCombinator, multiple batch generators can be
 * combined into one, i.e., to generate a new batch, multiple batches are
 * generated using each generator and their results are merged.
 * 
 * In the first example a combination of 2 node additions and 5 edge additions
 * is generated.
 * 
 * In the second example, an instance of the RandomBatch batch generator is
 * shown which simply combined node/edge addition/removal.
 * 
 * Combining arbitrary batches can lead to problems. For an example and a brief
 * discussion of how to resolve them, please checkout example
 * batches.sanitization.BatchSanitization.
 * 
 * @author benni
 *
 */
public class CombiningBatchGenerators extends Example {

	public CombiningBatchGenerators(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		Graph g = new RandomGraph(GDS.undirected(), 100, 500).generate();

		BatchGenerator na = new RandomNodeAdditions(2);
		BatchGenerator ea = new RandomEdgeAdditions(5);

		Log.infoSep();
		BatchGenerator combined_bg = new BatchCombinator(na, ea);
		Batch combined_b = combined_bg.generate(g);
		Log.info(combined_b.toString());
		combined_b.print();
		Log.infoSep();

		Log.infoSep();
		BatchGenerator random_bg = new RandomBatch(2, 1, 10, 3);
		Batch random_b = random_bg.generate(g);
		Log.info(random_b.toString());
		random_b.print();
		Log.infoSep();
	}

}
