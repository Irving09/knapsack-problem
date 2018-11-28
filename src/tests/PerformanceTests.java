/**
 * CONFIDENTIAL INFORMATION
 * <p>
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 * <p>
 * Date: Nov 27, 2018
 * Copyright 2018 innoirvinge@gmail.com
 */
package tests;

import knapsack.parents.Knapsack;
import logger.CSVLogger;

import java.io.FileNotFoundException;
import java.util.Comparator;

import static tests.TestSetGenerator.testsWithConstantCapacity;
import static tests.TestSetGenerator.testsWithConstantItems;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class PerformanceTests {

    private CSVLogger logger;

    public PerformanceTests(CSVLogger logger) {
        this.logger = logger;
    }

    public void run(Knapsack[] algorithms) throws FileNotFoundException {
        logger.openFile();

        for (TestSet testSet : testsWithConstantCapacity()) {
            logger.writeHeader(testSet.itemsSize(), testSet.capacity(), false);
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
            logger.writeNewLineToFile();
            logger.writeNewLineToFile();
        }

        for (TestSet testSet : testsWithConstantItems()) {
            logger.writeHeader(testSet.itemsSize(), testSet.capacity(), true);
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
            logger.writeNewLineToFile();
            logger.writeNewLineToFile();
        }

        logger.closeFile();
    }

}
