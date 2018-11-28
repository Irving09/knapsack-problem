import knapsack.BruteForce;
import knapsack.DPBottomUp;
import knapsack.DPTopDown;
import knapsack.parents.Knapsack;
import logger.CSVLogger;
import tests.performance.PerformanceTestRunner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

    	CSVLogger logger = new CSVLogger();
		PerformanceTestRunner runner = new PerformanceTestRunner(logger);

		Knapsack[] algorithmsToTest = {
			new BruteForce(),
			new DPTopDown(),
			new DPBottomUp()
		};

		runner.run(algorithmsToTest);
	}
    
}
