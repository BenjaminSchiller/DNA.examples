# Examples for the use of DNA

In this repo, we provide **examples for the use of DNA** (Dynamic Network Analyzer).
Each example handles a specific aspect of implementing new components for or the analysis of dynamic graphs using DNA.
These examples are coarsly grouped into categories while some examples refer to others for better understanding.

In addition to the Java source of these examples (**java/**), we provide input used by some examples (**input/**), scripts for building a jar, executing it, and generating html files from the results (**execution/**), and auxiliary html data (**html/**).

The output generated by the examples and the html generation is stored in **output/** but not contained in this repo.

## Filesystem structure

- **examples**
	- *input/* - input required for some of the examples
	- *java/* - java classes of the examples
- **execution and build scripts**
	- *execution/* - scripts for building, executing, and generating html
- **auxiliary files**
	- *html/* - html and java script files used by the generated html
- **generated output**
	- *output/* - output generated by the examples (not part of the repo)

## Building the examples.jar

For building a single jar file for the execution, we provide an ant build file

	execution/build.xml

The examples contained in *java/src/* are expected to be compiled in *java/bin/*.
This is the default using the *java/.project* and *java/.classpath* files generated while using it as an eclipse project.
In ths project as well as in the build file, the compiled version of DNA is expected (relatively from the build file) in *../../DNA/bin/*.
In addition, the config is expected there (*../../DNA/config/*) as well as all the libraries included in DNA (*../../DNA/lib/*).

After possibly adapting this buid file, the **examples.jar** can be build by executing the regular build command:

	ant -f build.xml

## Running an example

All examples contained in *java/src/* can be executed using the built *examples.jar* as follows:

	java -jar examples.jar $input $output $example

A description of the three required parameters is available when executing the jar with less or more parameters:

	DNA examples - expecting 3 arguments:
	  0: input data directory (without / at the end)
	     e.g., ../input
	  1: output data directory (without / at the end)
	     e.g., ../output
	  2: example (folder structure of packages and class)
	     e.g. /graph/weights/WeightedEdges
	     i.e. excluding dna/examples and .java

For every call, only the specified example is executed.
Assume the following example call:

	java -jar ../input ../output /graph/weights/WeightedEdges

Then, the example *dna.example.graph.weights.WeightedEdges.java* is executed. If input data is required by the example, it is read from *../input/graphs/weights/WeightedEdges/* and all output is written to *../output/graphs/weights/WeightedEdges/* an specific sub-folders.
In more detail:

	~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
	~ Example: dna.examples.graphs.weights.WeightedEdges
	~ Input:   ../input/graphs/weights/WeightedEdges/
	~ Output:  ../output/graphs/weights/WeightedEdges/
	~          ../output/graphs/weights/WeightedEdges/data/
	~          ../output/graphs/weights/WeightedEdges/plots/
	~          ../output/graphs/weights/WeightedEdges/screenshots/
	~          ../output/graphs/weights/WeightedEdges/out/
	~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~

- *data/* - results / series data
- *plots/* - plots generated from the results
- *screenshots/* - used for outputting e.g., graph visualization
- *out/* - writing arbitrary data, e.g., graphs or batches


## Processing all examples

To process all examples provided in *java/src/*, the following script can be used:

	execution/process.sh

Each example is executed using the *example.jar*.
The log output is written to *log* in the output folder of that example, e.g., to *../output/graphs/weights/WeightedEdges/log* in the example above.

In addition, the following script is executed for each example:

	execution/html.sh

