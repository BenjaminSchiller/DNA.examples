package dna.examples.batches.combination;

import dna.examples.batches.generators.BatchGenerators;
import dna.graph.datastructures.GDS;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.random.RandomGraph;
import dna.updates.generators.BatchGenerator;
import dna.updates.generators.random.RandomEdgeAdditions;
import dna.updates.generators.random.RandomEdgeRemovals;
import dna.updates.generators.random.RandomNodeAdditions;
import dna.updates.generators.random.RandomNodeRemovals;
import dna.updates.generators.util.BatchRepetition;
import dna.updates.generators.util.BatchRepetition.BatchRepetitionWrapper;
import dna.updates.generators.util.BatchRoundRobin;

/**
 * 
 * Using the simple batch generator BatchRoundRobin, multiple batch generators
 * can be generated in round-robin manner. Each is executed exactly once before
 * the next one is selected.
 * 
 * Using the BatchRepetition batch generator, the same can be achieved. In
 * addition, the repetitions of each batch can be specified. Using 1
 * (Repetition1), the same behavior as for BatchRoundRobin is achieved. Setting
 * this parameter to 2 (Repetition2) implies that each batch generator in the
 * list is applied twice before using the next one. Furthermore, a repetition
 * frequency can be specified for each batch generator separately as shown for
 * the last example (RepetitionX).
 * 
 * @author benni
 *
 */
public class AlternatingBatchGenerators extends BatchGenerators {

	public AlternatingBatchGenerators(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		GraphGenerator gg = new RandomGraph(GDS.undirected(), 50, 300);

		BatchGenerator bg1 = new RandomNodeAdditions(2);
		BatchGenerator bg2 = new RandomEdgeAdditions(20);
		BatchGenerator bg3 = new RandomEdgeRemovals(5);
		BatchGenerator bg4 = new RandomNodeRemovals(1);

		// execute batch generator in round robin order
		BatchGenerator roundRobin = new BatchRoundRobin(bg1, bg2, bg3, bg4);
		execute("RoundRobin", gg, roundRobin, 10);

		// execute batch generator in round robin order (1 turn per bg)
		BatchGenerator repetition1 = new BatchRepetition(1, bg1, bg2, bg3, bg4);
		execute("Repetition1", gg, repetition1, 10);

		// execute batch generator in round robin order (2 turns per bg)
		BatchGenerator repetition2 = new BatchRepetition(2, bg1, bg2, bg3, bg4);
		execute("Repetition2", gg, repetition2, 10);

		// execute batch generator in round robin order (bg-specific number of
		// repetitions)
		BatchRepetitionWrapper w1 = new BatchRepetitionWrapper(bg1, 2);
		BatchRepetitionWrapper w2 = new BatchRepetitionWrapper(bg2, 1);
		BatchRepetitionWrapper w3 = new BatchRepetitionWrapper(bg3, 3);
		BatchRepetitionWrapper w4 = new BatchRepetitionWrapper(bg4, 4);
		BatchGenerator repetitionX = new BatchRepetition(w1, w2, w3, w4);
		execute("RepetitionX", gg, repetitionX, 20);
	}

}
