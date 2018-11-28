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

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class TestCase {

    private int[] weights;
    private int[] values;
    private int capacity;

    public TestCase(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
    }

    public int[] weights() {
        return weights;
    }

    public int capacity() {
        return capacity;
    }

    public int[] values() {
        return values;
    }

    public static TestCaseBuilder builder() {
        return new TestCaseBuilder();
    }

    public static class TestCaseBuilder {
        private int[] weights;
        private int[] values;
        private int capacity;

        TestCaseBuilder() {
            this.weights = new int[0];
            this.values = new int[0];
        }

        public TestCaseBuilder weights(int[] weights) {
            this.weights = weights;
            return this;
        }

        public TestCaseBuilder values(int[] values) {
            this.values = values;
            return this;
        }

        public TestCaseBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public TestCase build() {
            return new TestCase(this.weights, this.values, this.capacity);
        }
    }

}
