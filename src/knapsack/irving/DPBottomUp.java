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

import static java.lang.Math.max;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class DPBottomUp extends Knapsack {

    public DPBottomUp(int[] weights, int[] values, int capacity) {
        super(weights, values, capacity);
    }

    @Override
    public int solve() {
        int n = weights.length;

        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < capacity; j++) {
                if (weights[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int included = dp[i - 1][ j - weights[i]] + values[i];
                    int notIncluded = dp[i - 1][j];

                    dp[i][j] = max(notIncluded, included);
                }
            }
        }
        return 0;
    }
}
