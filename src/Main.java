import knapsack.BruteForce;
import knapsack.DPBottomUp;
import knapsack.DPTopDown;
import knapsack.parents.Knapsack;
import logger.CSVLogger;
import tests.PerformanceTests;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

    	CSVLogger logger = new CSVLogger();
		PerformanceTests runner = new PerformanceTests(logger);

		Knapsack[] algorithmsToTest = {
			new BruteForce(),
			new DPTopDown(),
			new DPBottomUp()
		};

		runner.run(algorithmsToTest);
	}
    
}
