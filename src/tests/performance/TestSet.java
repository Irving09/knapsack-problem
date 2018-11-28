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

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class TestSet {

    private List<TestCase> batch;

    private int itemsSize;

    private int capacity;

    public TestSet() {
        this(new ArrayList<>(), 0, 0);
    }

    public TestSet(List<TestCase> batch, int itemsSize, int capacity) {
        this.batch = new ArrayList<>();
        this.batch.addAll(batch);
        this.itemsSize = itemsSize;
        this.capacity = capacity;
    }

    public List<TestCase> testCases() {
        return this.batch;
    }

    public void addTestCase(TestCase test) {
        this.batch.add(test);
    }

    public int itemsSize() {
        return itemsSize;
    }

    public int capacity() {
        return capacity;
    }

}
