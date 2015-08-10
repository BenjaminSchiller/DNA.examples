package dna.examples.graphs;

import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.weights.Double2dWeight;
import dna.graph.weights.IntWeight;
import dna.graph.weights.Long3dWeight;
import dna.graph.weights.Weight.WeightSelection;

/**
 * 
 * Using weighted edges work analogously to using weighted nodes.
 * 
 * Therefore, this example is restricted to a few Weight and WeightSelection
 * types.
 * 
 * @author benni
 *
 */
public class WeightedEdges extends Weighted {

	public WeightedEdges(String[] args) {
		super(args);
	}

	@Override
	protected GraphDataStructure[] getGDS() {
		GraphDataStructure[] gds = new GraphDataStructure[3];
		int i = 0;

		gds[i++] = GDS.directedE(IntWeight.class, WeightSelection.Rand);
		gds[i++] = GDS.directedE(Double2dWeight.class, WeightSelection.Rand);
		gds[i++] = GDS.directedE(Long3dWeight.class, WeightSelection.Rand);

		return gds;
	}

}
