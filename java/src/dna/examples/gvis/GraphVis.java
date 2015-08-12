package dna.examples.gvis;

import dna.graph.datastructures.GDS;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.canonical.CliqueGraph;
import dna.graph.generators.canonical.Grid2dGraph;
import dna.graph.generators.canonical.Grid3dGraph;
import dna.graph.generators.canonical.HoneyCombGraph.ClosedType;
import dna.graph.generators.canonical.RingGraph;
import dna.graph.generators.canonical.StarGraph;
import dna.graph.generators.random.RandomGraph;

public class GraphVis extends GVis {

	public GraphVis(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		init(1000, 100, 2000);

		execute(new RandomGraph(GDS.undirected(), 100, 500));

		execute(new RingGraph(GDS.undirected(), 15));
		execute(new CliqueGraph(GDS.undirected(), 10));
		execute(new StarGraph(GDS.undirected(), 40));

		execute(new Grid2dGraph(GDS.undirected(), 8, 6, ClosedType.OPEN));
		execute(new Grid3dGraph(GDS.undirected(), 3, 4, 5, ClosedType.OPEN));

		exit();
	}

	protected void execute(GraphGenerator gg) {
		gg.generate();
		screenshot(screenshotDir(), gg.getName());
	}

}
