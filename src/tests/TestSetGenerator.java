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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class TestSetGenerator {

    private static final int MAX_ITEMS_FOR_BRUTE_FORCE = 50;
    private static final int VALUE_LIMIT = 1000;
    private static final int WEIGHT_LIMIT = 1000;

    public static List<TestSet> sampleTestSets() {
        final int numberOfTestCases = 10;

        return Arrays.asList(
            generateTestSetConstantCapacity(numberOfTestCases, 2),
            generateTestSetConstantCapacity(numberOfTestCases, 4),
            generateTestSetConstantCapacity(numberOfTestCases, 8),
            generateTestSetConstantCapacity(numberOfTestCases, 16),
            generateTestSetConstantCapacity(numberOfTestCases, 32),
            generateTestSetConstantCapacity(numberOfTestCases, 64),
            generateTestSetConstantCapacity(numberOfTestCases, 128),
            generateTestSetConstantCapacity(numberOfTestCases, 256),
            generateTestSetConstantCapacity(numberOfTestCases, 512),
            generateTestSetConstantCapacity(numberOfTestCases, WEIGHT_LIMIT),
            generateTestSetConstantItemSize(numberOfTestCases, 2),
            generateTestSetConstantItemSize(numberOfTestCases, 4),
            generateTestSetConstantItemSize(numberOfTestCases, 8),
            generateTestSetConstantItemSize(numberOfTestCases, 16),
            generateTestSetConstantItemSize(numberOfTestCases, 32),
            generateTestSetConstantItemSize(numberOfTestCases, MAX_ITEMS_FOR_BRUTE_FORCE)
//          generateTestSetConstantItemSize(numberOfTestCases, 64),
//          generateTestSetConstantItemSize(numberOfTestCases, 128),
//          generateTestSetConstantItemSize(numberOfTestCases, 256),
//          generateTestSetConstantItemSize(numberOfTestCases, 512),
        );
    }

    public static TestSet generateTestSetConstantCapacity(int numTestCases, int capacity) {

        List<TestCase> testCases = new ArrayList<>(numTestCases);

        for (int i = 0; i < numTestCases; i++) {
            int itemSize = current().nextInt(1, MAX_ITEMS_FOR_BRUTE_FORCE);
            testCases.add(generateTestCase(itemSize, capacity));
        }

        return new TestSet(testCases);
    }

    public static TestSet generateTestSetConstantItemSize(int numTestCases, int itemSize) {

        List<TestCase> testCases = new ArrayList<>(numTestCases);

        for (int i = 0; i < numTestCases; i++) {
            int capacity = current().nextInt(1, WEIGHT_LIMIT);
            testCases.add(generateTestCase(itemSize, capacity));
        }

        return new TestSet(testCases);
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
