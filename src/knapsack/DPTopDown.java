/**
 * CONFIDENTIAL INFORMATION
 * <p>
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 * <p>
 * Date: Nov 18, 2018
 * Copyright 2018 innoirvinge@gmail.com
 */
package knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class DPTopDown extends Knapsack {

	public DPTopDown(int[] weights, int[] values, int capacity) {
		super(weights, values, capacity);
	}

	@Override
	public List<Integer> solve() {
		// Defines the size of the value and weight integer array for use in the
		// auxiliary array.
		int n = weights.length;

		// Create the auxiliary array with the size of (n+1)x(capacity+1)
		int[][] dp = new int[n + 1][capacity + 1];

		// 0 value for first row and column which represents no capacity and no items
		for (int i = 1, rowLen = dp.length; i < rowLen; i++)
			for (int j = 1, colLen = dp[i].length; j < colLen; j++)
				dp[i][j] = -1;

		int optimalValue = recurse(n, capacity, dp);
		System.out.println("Optimal value:" + optimalValue);
		return backtrack(dp);
	}

	private int recurse(int n, int capacity, int[][] cache) {
		int item  = n - 1;

		// No need to recurse the sub problem has already been solved before
		if (cache[n][capacity] != -1) {
			return cache[n][capacity];
		}

		// Otherwise, we recurse on the case when item IS NOT included in bag
		int ignored, chosen = 0;
		cache[item][capacity] = recurse(item, capacity, cache);
		ignored = cache[item][capacity];

		// And we also recurse on the case when item IS included in bag
		if (weights[item] <= capacity) {
			int leftOverCapacity = capacity - weights[item];
			cache[item][leftOverCapacity] = recurse(item, leftOverCapacity, cache);
			chosen = cache[item][leftOverCapacity] + values[item];
		}

		// Return optimal solution as well as filling in the cache
		// to prevent further recursions on same inputs
		return cache[n][capacity] = Math.max(ignored, chosen);
	}

	private List<Integer> backtrack(int[][] dp) {
		int rows = dp.length, cols = dp[0].length;
		int row = rows - 1, col = cols - 1;

		List<Integer> result = new ArrayList<>(rows - 1);
		// TODO Not yet implemented
		//    while (row > 0 && col > 0) {
		//      while (dp[row][col] == -1) {
		//        col--;
		//      }
		//
		//      result.add(row - 1);
		//      row--;
		//    }

		return result;
	}
}
