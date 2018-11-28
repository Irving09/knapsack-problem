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
import java.util.List;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class TestSet {

    private List<TestCase> batch;

    public TestSet() {
        this(new ArrayList<>());
    }

    public TestSet(List<TestCase> batch) {
        this.batch = new ArrayList<>();
        this.batch.addAll(batch);
    }

    public List<TestCase> testCases() {
        return this.batch;
    }

    public void addTestCase(TestCase test) {
        this.batch.add(test);
    }

}
