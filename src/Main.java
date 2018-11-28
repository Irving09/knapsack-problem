import logger.CSVLogger;
import tests.PerformanceTests;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

    	CSVLogger logger = new CSVLogger();
		PerformanceTests runner = new PerformanceTests(logger);

		runner.run();
	}
    
}
