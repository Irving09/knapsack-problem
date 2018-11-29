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
import java.util.List;

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
        runTestSet(testsWithConstantCapacity(), false, algorithms);
        runTestSet(testsWithConstantItems(), true, algorithms);
    }

    public void runTestSet(List<TestSet> testSets, boolean constantFactorIsN, Knapsack[] algorithms) throws IOException {
        for (TestSet testSet : testSets) {
            int itemSize = testSet.itemsSize();
            int capacity = testSet.capacity();

            String fullPath = logger.generateFileNameBy(itemSize, capacity);
            logger.openFile(fullPath);
            logger.writeHeader(itemSize, capacity, constantFactorIsN, algorithms);

            testSet.testCases().sort(Comparator.comparingInt(TestCase::n));

            for (TestCase testCase : testSet.testCases()) {
                logger.write(testCase.n() + "");
                for (Knapsack algorithm : algorithms) {
                    algorithm.weights(testCase.weights());
                    algorithm.values(testCase.values());
                    algorithm.capacity(testCase.capacity());

                    logger.logRuntime(algorithm);
                }

                logger.writeNewLineToFile();
            }

            logger.closeFile();
        }
    }

}
