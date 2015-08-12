package dna.examples.batches.generators;

import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.random.RandomGraph;
import dna.graph.weights.DoubleWeight;
import dna.graph.weights.IntWeight;
import dna.graph.weights.Weight.WeightSelection;
import dna.updates.generators.random.RandomEdgeWeightChanges;
import dna.updates.generators.random.RandomNodeWeightChanges;

/**
 * 
 * This is an example of batches comprised of random weight changes. First,
 * nodes have weight which are changed by the updates of the batches. Second,
 * edges are weighted and changed. Third, nodes and edges are weighted and
 * changed.
 * 
 * Note that applying the RandomEdgeWeightCHanges batch generator to a graph
 * with unweighted edges will throw an Exception.
 * 
 * @author benni
 *
 */
public class RandomWeightChanges extends BatchGenerators {

	public RandomWeightChanges(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		GraphDataStructure gdsV = GDS.directedV(IntWeight.class,
				WeightSelection.RandPos10);
		GraphDataStructure gdsE = GDS.directedE(DoubleWeight.class,
				WeightSelection.RandTrim1);
		GraphDataStructure gdsVE = GDS.directedVE(IntWeight.class,
				WeightSelection.RandPos10, DoubleWeight.class,
				WeightSelection.RandTrim1);

		GraphGenerator ggV = new RandomGraph(gdsV, 10, 20);
		GraphGenerator ggE = new RandomGraph(gdsE, 10, 20);
		GraphGenerator ggVE = new RandomGraph(gdsVE, 10, 20);

		execute("RandomNodeWeightChanges", ggV, new RandomNodeWeightChanges(5,
				WeightSelection.RandPos100), 10);
		execute("RandomEdgeWeightChanges", ggE, new RandomEdgeWeightChanges(10,
				WeightSelection.RandTrim3), 10);
		execute("RandomWeightChanges", ggVE,
				new dna.updates.generators.random.RandomWeightChanges(5,
						WeightSelection.RandPos100, 10,
						WeightSelection.RandTrim3), 10);
	}
}
