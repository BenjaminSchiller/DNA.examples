package dna.examples.batches.generators;

import dna.graph.datastructures.GDS;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.random.RandomGraph;
import dna.updates.generators.random.RandomEdgeAdditions;
import dna.updates.generators.random.RandomEdgeRemovals;
import dna.updates.generators.random.RandomNodeAdditions;
import dna.updates.generators.random.RandomNodeRemovals;

/**
 * 
 * As initial graph, a random graph (directed) with 10 nodes and 20 edges is
 * generated.
 * 
 * Then, four batch generators are used to generate batches: NodeAdditions (NA),
 * NodeRemovals (NR), EdgeAdditions (EA), and EdgeRemovals (ER).
 * 
 * NA: A graph can contain arbitrarily many nodes, therefore, all 10 batches can
 * be generated and a total of 10*4 = 40 nodes are added.
 * 
 * NR: Since the graph contains only 10 nodes, 3 nodes each are removed in the
 * first three batches. In the last possible one, only a single node is removed.
 * 
 * EA: In a directed graph with 10 nodes, a maximum of 10*9 = 90 edges can
 * exist. Therefore, 20 random edges are added in the first three batches. In
 * the fourth and final batch, the remaining 10 edges are added.
 * 
 * ER: With a total of 20 existing edges, 3*6 = 18 are removed in the first six
 * batches and the remaining 2 in the final 7th batch.
 * 
 * @author benni
 *
 */
public class RandomTopologyChanges extends BatchGenerators {

	public RandomTopologyChanges(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		GraphGenerator gg = new RandomGraph(GDS.directed(), 10, 20);

		execute("RandomNodeAdditions", gg, new RandomNodeAdditions(4), 10);
		execute("RandomNodeRemovals", gg, new RandomNodeRemovals(3), 10);
		execute("RandomEdgeAdditions", gg, new RandomEdgeAdditions(20), 10);
		execute("RandomEdgeRemovals", gg, new RandomEdgeRemovals(3), 10);
	}

}
