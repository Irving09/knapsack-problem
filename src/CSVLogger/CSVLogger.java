package CSVLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import knapsack.parents.Knapsack;

public class CSVLogger {

	PrintWriter p;

	public CSVLogger() throws FileNotFoundException {
		
	}

	public void logRuntime(Knapsack knapsack, int nSize, int capacitySize, boolean constantFactorIsN) {
		long startTime = System.nanoTime();
		knapsack.solve();
		long endTime = System.nanoTime();
		long runtime = endTime - startTime;
		writeToFileDifCol(p,constantFactorIsN?capacitySize:nSize ,runtime);
	}

	public void writeHeader(int nSize, int capacitySize, boolean constantFactorIsN) {
		StringBuilder sb = new StringBuilder();
		// Create Heading for each Column
		sb.append(constantFactorIsN ? "N " : "Capacity ");
		sb.append("Size:");
		sb.append(constantFactorIsN ? nSize : capacitySize);
		sb.append("\n");
		sb.append("Brute Force,, Bottom-Up,, Top-Down\n");
		for (int i = 0; i < 3; i++) {
			sb.append(constantFactorIsN ? "W," : "N,");
			sb.append("Runtime,");
		}
		sb.append("\n");
		p.write(sb.toString());
	}
	public void openFile() throws FileNotFoundException {
		p = new PrintWriter(new File("output.csv"));
	}
	
	public void closeFile() {
		p.close();
	}
	
	public void writeNewLineToFile() {
		p.write("\n");
	}
	private void writeToFileDifCol(PrintWriter p, int xValue,long runtime) {

		StringBuilder sb = new StringBuilder();
		// Write Data to each Row
		sb.append(xValue);
		sb.append(",");
		sb.append(runtime);
		sb.append(",");
		p.write(sb.toString());
	}

}
