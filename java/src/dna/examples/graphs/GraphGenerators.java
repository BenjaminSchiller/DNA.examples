package dna.examples.graphs;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.generators.GraphGenerator;
import dna.graph.generators.canonical.CliqueGraph;
import dna.graph.generators.canonical.RingGraph;
import dna.graph.generators.canonical.StarGraph;
import dna.io.GraphWriter;
import dna.util.Log;

/**
 * 
 * Here, three different grqph generators are demonstrated (ring, clique, and
 * start). For all three graph types, a directed and an undirected version with
 * 10 nodes each is generated.
 * 
 * These examples show that the same graph generator can generate graphs with a
 * different meaning when selecting directed or undirected edges. Obviously, a
 * graph generator could also only consider the meaning of undirected graphs and
 * always insert an inverse edge to a directed one in case of generating a
 * directed graph.
 * 
 * @author benni
 *
 */
public class GraphGenerators extends Example {

	public GraphGenerators(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		GraphDataStructure gds_d = GDS.directed();
		GraphDataStructure gds_u = GDS.undirected();

		int nodes = 10;

		execute(new RingGraph(gds_d, nodes));
		execute(new RingGraph(gds_u, nodes));
		execute(new CliqueGraph(gds_d, nodes));
		execute(new CliqueGraph(gds_u, nodes));
		execute(new StarGraph(gds_d, nodes));
		execute(new StarGraph(gds_u, nodes));
	}

	protected void execute(GraphGenerator gg) {
		Graph g = gg.generate();
		Log.infoSep();
		g.printAll();
		Log.infoSep();

		String filename = gg.getNamePlain() + graphSuffix;
		GraphWriter.write(g, outDir(), filename);
		Log.info("=> " + filename);
		Log.infoSep();
		System.out.println();
	}

}
