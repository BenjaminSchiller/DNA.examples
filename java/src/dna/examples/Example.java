package dna.examples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import dna.util.Log;

/**
 * 
 * This abstract class provides basic methods and a main method for implementing
 * and executing examples.
 * 
 * As input parameters, each examples takes the input and output dir as well as
 * the path to the example class to be executed.
 * 
 * @author benni
 *
 */
public abstract class Example {

	protected String inputDir;
	protected String outputDir;

	protected String[] packages;
	protected String name;

	public static final String graphSuffix = ".dnag.txt";
	public static final String batchSuffix = ".dnab.txt";

	public Example(String[] args) {
		inputDir = args[0];
		outputDir = args[1];
		String[] temp = args[2].split("/");
		packages = new String[temp.length - 2];
		for (int i = 1; i < temp.length - 1; i++) {
			packages[i - 1] = temp[i];
		}
		name = temp[temp.length - 1].replace(".java", "");
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		if (args.length == 0) {
			String in = "../input";
			String out = "output";
			args = new String[] { in, out, "/gvis/GraphVis" };
		}

		if (args.length != 3) {
			System.err.println("DNA examples - expecting 3 arguments:");
			System.err
					.println("  0: input data directory (without / at the end)");
			System.err.println("     e.g., ../input");
			System.err
					.println("  1: output data directory (without / at the end)");
			System.err.println("     e.g., ../output");
			System.err
					.println("  2: example (folder structure of packages and class)");
			System.err.println("     e.g. /graph/weights/WeightedEdges");
			System.err.println("     i.e. excluding dna/examples and .java");
			return;
		}

		@SuppressWarnings("unchecked")
		Class<Example> c = (Class<Example>) Class.forName("dna.examples"
				+ args[2].replace("/", ".").replace(".java", ""));

		if (Modifier.isAbstract(c.getModifiers())) {
			System.out.println("Cannot execute class " + c);
			System.out.println("It is abstract...");
			return;
		}

		Example ex = c.getConstructor(String[].class).newInstance(
				new Object[] { args });

		Log.infoSep();
		Log.info("Example: " + ex.getClass().getName());
		Log.info("Input:   " + ex.inputDir());
		Log.info("Output:  " + ex.outputDir());
		Log.info("         " + ex.dataDir());
		Log.info("         " + ex.plotDir());
		Log.info("         " + ex.screenshotDir());
		Log.info("         " + ex.outDir());
		Log.infoSep();
		System.out.println("");
		System.out.println("");

		ex.execute();
	}

	protected abstract void execute();

	public String getDirs() {
		StringBuffer buff = new StringBuffer();
		for (String cat : packages) {
			buff.append(cat + "/");
		}
		buff.append(name + "/");
		return buff.toString();
	}

	public String inputDir() {
		return inputDir + "/" + getDirs();
	}

	private String outputDir() {
		return outputDir + "/" + getDirs();
	}

	public String dataDir() {
		return outputDir() + "data/";
	}

	public String plotDir() {
		return outputDir() + "plots/";
	}

	public String screenshotDir() {
		return outputDir() + "screenshots/";
	}

	public String outDir() {
		return outputDir() + "out/";
	}
}
