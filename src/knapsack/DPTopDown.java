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

import knapsack.parents.KnapsackDP;

import java.util.List;

/**
 * @author irving09 <innoirvinge@gmail.com>
 * @author Joshua Meigs <joshua.meigs@gmail.com>
 */
public class DPTopDown extends KnapsackDP {

	public DPTopDown() {
		super();
	}

	public DPTopDown(int[] weights, int[] values, int capacity) {
		super(weights, values, capacity);
	}

	/**
	 * Computes the optimal list of items for the knapsack problem.
	 *
	 * @return A list representing the indices of the choesn items which give optimal knapsack value
	 * */
	@Override
	public List<Integer> solve() {
		int n = weights.length;
		int[][] dp = new int[n + 1][capacity + 1];
		for (int i = 1, rowLen = dp.length; i < rowLen; i++)
			for (int j = 1, colLen = dp[i].length; j < colLen; j++)
				dp[i][j] = -1;

		recurse(n, capacity, dp);
		return backtrack(dp);
	}

	/**
	 * Recurses through the item of choices to be placed in the knapsack.
	 * Each layer of the recursion stack has two options
	 * 		- include the item
	 * 		- do not include the item
	 *
	 * @param n Remaining number of items in the knapsack.
	 * @param capacity Remaining capacity in the knapsack.
	 * @param cache Auxiliary array that stores the optimal value at cell dp[i][j].
	 *              Variables i is the number of items and j is the capacity.
	 * @return The optimal value in the knapsack
	 * */
	private int recurse(int n, int capacity, int[][] cache) {
		int item  = n - 1;

		// No need to recurse the sub problem that has already been solved before
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

		// Return optimal solution between when item is included/not included
		// fill in cache to prevent further recursions on same inputs
		return cache[n][capacity] = Math.max(ignored, chosen);
	}
}
