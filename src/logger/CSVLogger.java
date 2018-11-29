package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

import knapsack.parents.Knapsack;

/**
 * @author irving09 <innoirvinge@gmail.com>
 * @author Joshua Meigs <joshua.meigs@gmail.com>
 * The class which will handle the creation and writing to the output file for graph representation of the results.
 */
public class CSVLogger {

	/**The PrintWriter object that will be used to print information to the output file.*/
	private PrintWriter p;
	
	/**The Default Constructor for CSVLogger*/
	public CSVLogger() {}

	/**
	 * Writes the given x value(the size of the current n or the capacity depending on the constant factor) and Y-Value(Runtime) that will be written to the file
	 * for graph representation of the data.
	 *
	 * @param knapsack The Type of Approach that was taken to solve the knapsack problem.
	 */
	public void logRuntime(Knapsack knapsack) {
		long startTime = System.nanoTime();
		knapsack.solve();
		long endTime = System.nanoTime();
		long runtime = endTime - startTime;
		p.write("," + runtime);
	}

	/**
	 * Writes the header for the current test set of data to the output file.
	 * 
	 * @param nSize The size of the current N value
	 * @param capacitySize The current size of the capacity W
	 * @param constantFactorIsN true if the variable that is being changed for testing is the capacity, false otherwise.
	 */
	public void writeHeader(int nSize, int capacitySize, boolean constantFactorIsN, Knapsack[] algorithms) {
		String csv = Arrays.stream(algorithms)
				.map(knapsack -> knapsack.name() + " (ns)")
				.collect(Collectors.joining(","));

		StringBuilder sb = new StringBuilder();
		// Create Heading for each Column
		sb.append(constantFactorIsN ? "N " : "Capacity ");
		sb.append("Size:");
		sb.append(constantFactorIsN ? nSize : capacitySize);
		sb.append("\n");
		sb.append((!constantFactorIsN ? "N" : "W") + "," + csv + "\n");
		p.write(sb.toString());
	}
	/**
	 * Opens the output file for writing the results of the tests
	 * @throws FileNotFoundException if the File cannot be found.z
	 */
	public void openFile() throws FileNotFoundException {
		p = new PrintWriter(new File("output.csv"));
	}
	/**
	 * Opens the output file for writing the results of the tests
	 * @throws FileNotFoundException if the File cannot be found.z
	 */
	public void openFile(String fullPath) throws IOException {
		File f = new File(fullPath);
		f.getParentFile().mkdirs();
		f.createNewFile();

		p = new PrintWriter(f);
	}

	public String generateFileNameBy(int itemsSize, int capacity) {
		boolean itemsSizeIsConstant = itemsSize > 0;
		String prefix = itemsSizeIsConstant ? "n" : "capacity";
		int value = itemsSizeIsConstant ? itemsSize : capacity;
		return "./data/" + prefix + "_" + value + ".csv";
	}
	/**
	 * Closes the file that was opened for output
	 */
	public void closeFile() {
		p.close();
	}
	/**
	 * Writes a new line to the output file
	 */
	public void writeNewLineToFile() {
		p.write("\n");
	}

	public void write(String any) {
		p.write(any);
	}

}
