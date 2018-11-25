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

import java.util.HashMap;
import java.util.Map;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class DPTopDown extends Knapsack {

	public DPTopDown(int[] weights, int[] values, int capacity) {
		super(weights, values, capacity);
		solver_joshua();
	}

	public int solver_joshua() {
		// Defines the size of the value and weight integer array for use in the
		// auxiliary array.
		int n = values.length;
		// Create the auxiliary array with the size of (n+1)x(capacity+1)
		int[][] subProblemChoices = new int[n + 1][capacity + 1];
		// Define the first column of every row to be zero, as it will act as the case
		// were the capacity itself is zero
		for (int i = 0; i < n; i++) {
			subProblemChoices[i][0] = 0;
		}
		// Define the first row to be zero, as it will represent the 0th item, meaning
		// no item.
		for (int i = 0; i < capacity; i++) {
			subProblemChoices[0][i] = 0;
		}
		//Define the rest of the posistions to be -1, meaning that they have no value set for them yet.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= capacity; j++) {
				subProblemChoices[i][j] = -1;
			}
		}
		//start the recursive top down calls and return the result.
		return topDownKnapsack(n, capacity, subProblemChoices);
	}

	public int topDownKnapsack(int i, int j, int[][] subProblemChoices) {
		//The Base Case for the recursion, where if the current item i with a capacity j has already been
		//defined, return that cell.
		if (subProblemChoices[i][j] >= 0) {
			return subProblemChoices[i][j];
		} else if (j - weights[(i - 1)] < 0) {
			//The Second case, if the weight of the current item is greater than the current capacity j	
			
			//If the sub
			if (subProblemChoices[i - 1][j] >= 0) {
				return subProblemChoices[i - 1][j];
			} else {
				subProblemChoices[i - 1][j] = topDownKnapsack(i - 1, j, subProblemChoices);
				return subProblemChoices[i - 1][j];
			}
		} else {
			int chosen = values[(i - 1)] + topDownKnapsack(i - 1, j - weights[(i - 1)], subProblemChoices);
			int notChosen = topDownKnapsack(i - 1, j, subProblemChoices);
			return subProblemChoices[i][j] = Math.max(chosen, notChosen);
		}
		// return subProblemChoices[i][j];
	}

	@Override
	public int solve() {
		int item = 0;
		int weightSoFar = 0;

		return recurse(item, weightSoFar, new HashMap<>());
	}

	private int recurse(int item, int weightSoFar, Map<String, Integer> cache) {
		int n = weights.length;
		if (item == n)
			return 0;

		int nextItem = item + 1;
		int weightItemIncluded = weightSoFar + weights[item];
		if (weightItemIncluded > capacity) {
			// do not include current item,

			// skip and go to next item with just weight so far
			String cacheKey = toCacheKey(nextItem, weightSoFar);
			boolean subProblemAlreadySolved = cache.containsKey(cacheKey);
			if (subProblemAlreadySolved) {
				// do not recurse and get cached subProblem
				return cache.get(cacheKey);
			} else {
				// only recurse when necessary
				int result = recurse(nextItem, weightSoFar, cache);

				// record subproblem result
				cache.put(cacheKey, result);

				// cached subProblem's result
				return result;
			}
		} else {
			String cacheKey = toCacheKey(nextItem, weightItemIncluded);
			boolean subProblemAlreadySolved = cache.containsKey(cacheKey);
			if (subProblemAlreadySolved) {
				// include item's value + cached subProblem
				return values[item] + cache.get(cacheKey);
			} else {
				// only recurse when necessary
				int result = recurse(nextItem, weightItemIncluded, cache);

				// record subproblem result
				cache.put(cacheKey, result);

				// include item's value + subProblem's result
				return values[item] + result;
			}
		}
	}

	private String toCacheKey(int item, int weight) {
		return item + ":" + weight;
	}
}
