/**
 * CONFIDENTIAL INFORMATION
 * <p>
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 * <p>
 * Date: Nov 27, 2018
 * Copyright 2018 innoirvinge@gmail.com
 */
package tests.performance;

import knapsack.parents.Knapsack;
import logger.CSVLogger;

import java.io.IOException;
import java.util.Comparator;

import static tests.performance.TestSetGenerator.testsWithConstantCapacity;
import static tests.performance.TestSetGenerator.testsWithConstantItems;

/**
 * @author irving09 <innoirvinge@gmail.com>
 * @author Joshua Meigs <joshua.meigs@gmail.com>
 */
public class PerformanceTestRunner {

    private CSVLogger logger;

    public PerformanceTestRunner(CSVLogger logger) {
        this.logger = logger;
    }

    public void run(Knapsack[] algorithms) throws IOException {
        for (TestSet testSet : testsWithConstantCapacity()) {
            int itemSize = testSet.itemsSize();
            int capacity = testSet.capacity();
            String fullPath = logger.generateFileNameBy(itemSize, capacity);
            logger.openFile(fullPath);
            logger.writeHeader(itemSize, capacity, false);

            testSet.testCases().sort(Comparator.comparingInt(TestCase::n));

            for (TestCase testCase : testSet.testCases()) {
                for (Knapsack algorithm : algorithms) {
                    algorithm.weights(testCase.weights());
                    algorithm.values(testCase.values());
                    algorithm.capacity(testCase.capacity());

                    logger.logRuntime(algorithm, testCase.n());
                }

                logger.writeNewLineToFile();
            }

            logger.closeFile();
        }

        for (TestSet testSet : testsWithConstantItems()) {
            int itemSize = testSet.itemsSize();
            int capacity = testSet.capacity();
            String fullPath = logger.generateFileNameBy(itemSize, capacity);
            logger.openFile(fullPath);
            logger.writeHeader(itemSize, capacity, true);

            testSet.testCases().sort(Comparator.comparingInt(TestCase::capacity));

            for (TestCase testCase : testSet.testCases()) {
                for (Knapsack algorithm : algorithms) {
                    algorithm.weights(testCase.weights());
                    algorithm.values(testCase.values());
                    algorithm.capacity(testCase.capacity());

                    logger.logRuntime(algorithm, testCase.capacity());
                }

                logger.writeNewLineToFile();
            }

            logger.closeFile();
        }
    }

}
