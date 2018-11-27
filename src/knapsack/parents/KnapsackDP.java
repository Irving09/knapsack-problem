/**
 * CONFIDENTIAL INFORMATION
 * <p>
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 * <p>
 * Date: Nov 27, 2018
 * Copyright 2018 innoirvinge@gmail.com
 */
package knapsack.parents;

import knapsack.interfaces.Backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public abstract class KnapsackDP extends Knapsack implements Backtrack {

    protected KnapsackDP(int[] weights, int[] values, int capacity) {
        super(weights, values, capacity);
    }

    /**
     * Backtracks the populated auxillary array dp to find items that were added to the knapsack
     * which also contributes to the overall optimal value.
     *
     * Reference: https://www.geeksforgeeks.org/printing-items-01-knapsack/
     *
     * @param dp Auxiliary array that stores the optimal value at cell dp[i][j].
     *           Variables i is the number of items and j is the capacity.
     * @return A list representing the indices of the choesn items which give optimal knapsack value.
     * */
    @Override
    public List<Integer> backtrack(int[][] dp) {
        int row = values.length;
        int col = capacity;

        int optimalValue = dp[row][col];

        List<Integer> result = new ArrayList<>();

        while (row > 0 && optimalValue > 0) {
            // optimal value did not include this item at dp[row][col]
            if (optimalValue == dp[row - 1][col]) {
                row--; // move to next item
            } else {

                // This item is included.
                result.add(row - 1);

                // Since this weight is included its
                // value is deducted
                optimalValue = optimalValue - values[row - 1];
                col = col - weights[row - 1];
            }
        }

        return result;
    }

}
