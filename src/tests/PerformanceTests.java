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

import knapsack.BruteForce;
import knapsack.DPBottomUp;
import knapsack.DPTopDown;
import knapsack.parents.Knapsack;
import logger.CSVLogger;

import java.io.FileNotFoundException;

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

    public void run() throws FileNotFoundException {
        logger.openFile();

        for (TestSet testSet : testsWithConstantCapacity()) {
            logger.writeHeader(testSet.itemsSize(), testSet.capacity(), false);
            for (TestCase testCase : testSet.testCases()) {
                int[] weights = testCase.weights();
                int[] values = testCase.values();
                int capacity = testCase.capacity();

                Knapsack bruteForce = new BruteForce(weights, values, capacity);
                Knapsack bottomUp = new DPBottomUp(weights, values, capacity);
                Knapsack topdown = new DPTopDown(weights, values, capacity);

                logger.logRuntime(bruteForce, testCase.n());
                logger.logRuntime(bottomUp, testCase.n());
                logger.logRuntime(topdown, testCase.n());

                logger.writeNewLineToFile();
            }
            logger.writeNewLineToFile();
            logger.writeNewLineToFile();
        }

        for (TestSet testSet : testsWithConstantItems()) {
            logger.writeHeader(testSet.itemsSize(), testSet.capacity(), true);
            for (TestCase testCase : testSet.testCases()) {
                int[] weights = testCase.weights();
                int[] values = testCase.values();
                int capacity = testCase.capacity();

                Knapsack bruteForce = new BruteForce(weights, values, capacity);
                Knapsack bottomUp = new DPBottomUp(weights, values, capacity);
                Knapsack topdown = new DPTopDown(weights, values, capacity);

                logger.logRuntime(bruteForce, testCase.capacity());
                logger.logRuntime(bottomUp, testCase.capacity());
                logger.logRuntime(topdown, testCase.capacity());

                logger.writeNewLineToFile();
            }
            logger.writeNewLineToFile();
            logger.writeNewLineToFile();
        }

        logger.closeFile();
    }

}
