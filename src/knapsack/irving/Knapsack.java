/**
 * CONFIDENTIAL INFORMATION
 * <p>
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 * <p>
 * Date: Nov 18, 2018
 * Copyright 2018 innoirvinge@gmail.com
 */
package knapsack.irving;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public abstract class Knapsack {

    protected int[] weights;
    protected int[] values;
    protected int capacity;

    public Knapsack(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
    }

    abstract int solve();

}
