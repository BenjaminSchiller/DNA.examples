package dna.examples.graphs;

import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.weights.Double2dWeight;
import dna.graph.weights.IntWeight;
import dna.graph.weights.Weight.WeightSelection;

/**
 * 
 * Node and edges can both be weighted. Obviously, their weight types and
 * default selection strategy can differ.
 * 
 * Here, only some examples are given, for an exhaustive list of possibilities,
 * we refer to the node weights example.
 * 
 * @author benni
 *
 */
public class WeightedNodesAndEdges extends Weighted {

	public WeightedNodesAndEdges(String[] args) {
		super(args);
	}

	@Override
	protected GraphDataStructure[] getGDS() {
		GraphDataStructure[] gds = new GraphDataStructure[2];

		int i = 0;

		gds[i++] = GDS.directedVE(IntWeight.class, WeightSelection.RandNeg,
				IntWeight.class, WeightSelection.RandPos);
		gds[i++] = GDS.directedVE(IntWeight.class, WeightSelection.RandPos100,
				Double2dWeight.class, WeightSelection.RandTrim3);

		return gds;
	}
}
