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

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class TestSetGenerator {

    private static final int MAX_ITEMS_FOR_BRUTE_FORCE = 20;
    private static final int VALUE_LIMIT = 1000;
    private static final int WEIGHT_LIMIT = 1024;
    private static final int NUM_TEST_CASES_PER_BATCH = 50;

    public static List<TestSet> testsWithConstantCapacity() {
        List<TestSet> testSets = new ArrayList<>();
        for (int capacity = 10; capacity <= WEIGHT_LIMIT; capacity += 50) {
            testSets.add(generateTestSetConstantCapacity(NUM_TEST_CASES_PER_BATCH, capacity));
        }

        return testSets;
    }

    public static List<TestSet> testsWithConstantItems() {
        List<TestSet> testSets = new ArrayList<>();
        for (int itemSize = 10; itemSize <= MAX_ITEMS_FOR_BRUTE_FORCE; itemSize++) {
            testSets.add(generateTestSetConstantItemSize(NUM_TEST_CASES_PER_BATCH, itemSize));
        }

        // TODO figure out capacity loop

        return testSets;
    }

    public static TestSet generateTestSetConstantCapacity(int numTestCases, int capacity) {

        List<TestCase> testCases = new ArrayList<>(numTestCases);

        for (int itemSize = 10; itemSize <= MAX_ITEMS_FOR_BRUTE_FORCE; itemSize++) {
            testCases.add(generateTestCase(itemSize, capacity));
        }

        return new TestSet(testCases, 0, capacity);
    }

    public static TestSet generateTestSetConstantItemSize(int numTestCases, int itemSize) {

        List<TestCase> testCases = new ArrayList<>(numTestCases);

        for (int capacity = 2; capacity <= WEIGHT_LIMIT; capacity *= 2) {
            testCases.add(generateTestCase(itemSize, capacity));
        }

        return new TestSet(testCases, itemSize, 0);
    }

    public static TestCase generateTestCase(int itemSize, int capacity) {
        int[] values  = new int[itemSize];
        int[] weights = new int[itemSize];
        for(int j = 0; j < itemSize; j++) {
            values[j] = current().nextInt(VALUE_LIMIT);
            weights[j] = current().nextInt(WEIGHT_LIMIT);
        }

        return TestCase.builder()
            .capacity(capacity)
            .weights(weights)
            .values(values)
        .build();
    }

}
