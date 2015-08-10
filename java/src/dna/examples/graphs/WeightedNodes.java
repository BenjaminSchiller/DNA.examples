package dna.examples.graphs;

import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.weights.Double2dWeight;
import dna.graph.weights.Double3dWeight;
import dna.graph.weights.DoubleWeight;
import dna.graph.weights.Int2dWeight;
import dna.graph.weights.Int3dWeight;
import dna.graph.weights.IntWeight;
import dna.graph.weights.Long2dWeight;
import dna.graph.weights.Long3dWeight;
import dna.graph.weights.LongWeight;
import dna.graph.weights.Weight.WeightSelection;

/**
 * 
 * In this example, graphs with weighted edges are generated. The type of weight
 * that is assigned to all nodes is specified by a class extending the
 * dna.graph.weights.Weight class. By default, weight that hold integer, double,
 * or long values have been implemented. For each one, the basic weight is a
 * single number while the 2d and 3d versions hold 2 or 3 values.
 * 
 * In addition to the weight type, the default WeightSelection must be
 * specified. It is used to determine the value(s) of a new weight in case no
 * parameters are specified by the generating graph or batch generator. Here,
 * the random graph generator is used which does not include the definition of
 * weights. Hence, all weights generated during the graph generation are
 * generated using the specified WeightSelection.
 * 
 * Please note, that not all types of WeightSelection are applicable to all
 * weight types. E.g., RandPos is only applicable to integer and long weights
 * while RandTrim1 is only applicable to double weights. For multi-dimensional
 * weights, the same weight selection strategy is used for each coordinate
 * separately.
 * 
 * In the example, all valid WeightSelection types are combined with int,
 * double, and long weights. For the 2d and 3d versions, only a single example
 * is generated. While all examples use directed graphs, the use of undirected
 * graph is the same.
 * 
 * @author benni
 *
 */
public class WeightedNodes extends Weighted {

	public WeightedNodes(String[] args) {
		super(args);
	}

	@Override
	protected GraphDataStructure[] getGDS() {
		GraphDataStructure[] gds = new GraphDataStructure[28];
		int i = 0;

		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.Zero);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.One);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.Min);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.Max);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.Rand);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.RandPos);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.RandNeg);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.RandPos10);
		gds[i++] = GDS.directedV(IntWeight.class, WeightSelection.RandPos100);

		gds[i++] = GDS.directedV(Int2dWeight.class, WeightSelection.RandPos100);
		gds[i++] = GDS.directedV(Int3dWeight.class, WeightSelection.RandPos100);

		gds[i++] = GDS.directedV(DoubleWeight.class, WeightSelection.Zero);
		gds[i++] = GDS.directedV(DoubleWeight.class, WeightSelection.One);
		gds[i++] = GDS.directedV(DoubleWeight.class, WeightSelection.Rand);
		gds[i++] = GDS.directedV(DoubleWeight.class, WeightSelection.RandTrim1);
		gds[i++] = GDS.directedV(DoubleWeight.class, WeightSelection.RandTrim2);
		gds[i++] = GDS.directedV(DoubleWeight.class, WeightSelection.RandTrim3);

		gds[i++] = GDS.directedV(Double2dWeight.class,
				WeightSelection.RandTrim3);
		gds[i++] = GDS.directedV(Double3dWeight.class,
				WeightSelection.RandTrim3);

		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.Zero);
		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.One);
		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.Min);
		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.Max);
		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.Rand);
		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.RandPos);
		gds[i++] = GDS.directedV(LongWeight.class, WeightSelection.RandNeg);

		gds[i++] = GDS.directedV(Long2dWeight.class, WeightSelection.RandNeg);
		gds[i++] = GDS.directedV(Long3dWeight.class, WeightSelection.RandNeg);

		return gds;
	}

}
