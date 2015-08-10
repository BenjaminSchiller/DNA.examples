package dna.examples.graphs;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.random.RandomGraph;
import dna.io.GraphWriter;
import dna.util.Log;

/**
 * 
 * This is an abstract class that is used by the examples that demonerstrate the
 * use of different weights for nodes and edges. By itself, it does not provide
 * any example that produces results.
 * 
 * @author benni
 *
 */
public abstract class Weighted extends Example {

	public Weighted(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		for (GraphDataStructure gds_ : this.getGDS()) {
			GraphGenerator gg = new RandomGraph(gds_, 10, 20);
			Graph g = gg.generate();
			Log.infoSep();
			Log.info("node type: " + gds_.getNodeType());
			Log.info("node weight type: " + gds_.getNodeWeightType());
			Log.info("node weight selection: " + gds_.getNodeWeightSelection());
			Log.info("edge type: " + gds_.getEdgeType());
			Log.info("edge weight type: " + gds_.getEdgeWeightType());
			Log.info("edge weight selection: " + gds_.getEdgeWeightSelection());
			Log.infoSep();
			g.printAll();
			Log.infoSep();
			System.out.println();

			GraphWriter.write(g, outDir(), gds_.getEdgeWeightType()
					.getSimpleName()
					+ "--"
					+ gds_.getEdgeWeightSelection()
					+ graphSuffix);
		}
	}

	protected abstract GraphDataStructure[] getGDS();
}
