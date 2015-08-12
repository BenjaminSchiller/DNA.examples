package dna.examples.batches.generators;

import dna.examples.Example;
import dna.graph.Graph;
import dna.graph.generators.GraphGenerator;
import dna.io.BatchWriter;
import dna.io.GraphWriter;
import dna.updates.batch.Batch;
import dna.updates.generators.BatchGenerator;
import dna.util.Log;

/**
 * 
 * This abstract class provides a simple demonstration method for batch
 * generators.
 * 
 * First, a graph is generated using the given graph generator. Then, the
 * specified number of batches is generated and applied to the graph. Before the
 * generation of a new batch, it is tested if another batch is even possible.
 * For example, no further edges can be added to a complete graph and no nodes
 * can be removed in case the graph contains none. This flag is also used when
 * reading batches from files and set false in case no more files are available.
 * 
 * @author benni
 *
 */
public abstract class BatchGenerators extends Example {

	public BatchGenerators(String[] args) {
		super(args);
	}

	protected void execute(String name, GraphGenerator gg, BatchGenerator bg,
			int batches) {
		Log.infoSep();
		Log.info("Name: " + name);
		Log.info("GG: " + gg.getName());
		Log.info("BG: " + bg.getName());
		Graph g = gg.generate();
		GraphWriter.write(g, outDir() + name + "/", g.getTimestamp()
				+ graphSuffix);
		Log.info("G: " + g.toString());
		Log.infoSep();
		for (int i = 0; i < batches; i++) {
			if (!bg.isFurtherBatchPossible(g)) {
				System.out.println("no further batch possible");
				break;
			}
			Batch b = bg.generate(g);
			BatchWriter
					.write(b, outDir() + name + "/", b.getTo() + batchSuffix);
			System.out.println(b);
			b.apply(g);
			System.out.println("  -> " + g);
		}
		Log.infoSep();
	}

}
