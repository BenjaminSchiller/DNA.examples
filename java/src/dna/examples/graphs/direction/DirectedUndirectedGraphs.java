package dna.examples.graphs.direction;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.datastructures.GDS;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.generators.GraphGenerator;
import dna.io.GraphWriter;
import dna.util.Log;

/**
 * 
 * Graphs can be directed or undirected.
 * 
 * In this example, two random graphs with 10 nodes and 30 edges are created. In
 * the first graph, edges are directed and undirected in the second case.
 * 
 * @author benni
 *
 */
public class DirectedUndirectedGraphs extends Example {

	public DirectedUndirectedGraphs(String[] args) {
		super(args);
	}

	@Override
	protected void execute() {
		GraphDataStructure gds_d = GDS.directed();
		GraphDataStructure gds_u = GDS.undirected();
		int nodes = 10;
		int edges = 30;

		GraphGenerator gg_d = new dna.graph.generators.random.RandomGraph(
				gds_d, nodes, edges);
		GraphGenerator gg_u = new dna.graph.generators.random.RandomGraph(
				gds_u, nodes, edges);

		Graph g_d = gg_d.generate();
		Graph g_u = gg_u.generate();

		Log.infoSep();
		Log.info("DIRECTED");
		Log.info("node type: " + gds_d.getNodeType());
		Log.info("edge type: " + gds_d.getEdgeType());
		Log.infoSep();
		g_d.printAll();
		Log.infoSep();
		System.out.println();

		Log.infoSep();
		Log.info("UNDIRECTED");
		Log.info("node type: " + gds_u.getNodeType());
		Log.info("edge type: " + gds_u.getEdgeType());
		Log.infoSep();
		g_u.printAll();
		Log.infoSep();
		System.out.println();

		GraphWriter.write(g_d, outDir(), "g_d" + graphSuffix);
		GraphWriter.write(g_u, outDir(), "g_u" + graphSuffix);

	}

}
