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

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;

/**
 * @author irving09 <innoirvinge@gmail.com>
 */
public class DPBottomUp extends Knapsack {

	public DPBottomUp(int[] weights, int[] values, int capacity) {
		super(weights, values, capacity);
//		List<Integer> results = solver_josh();
//		Collections.sort(results);
//		for(Integer num: results) {
//        	System.out.print(num + ", ");
//        }
//        System.out.println();
	}

	/***
	 * This purpose of this function is to solve the knapsack problem by implementing
	 * a bottom-up dynamic programming approach.
	 * 
	 * @return The value which represents the optimal choice of items for a given
	 *         Knapsack.
	 */
	@Override
	public List<Integer> solve() {
		//Defines the size of the value and weight integer array for use in the auxiliary array.
		int n = values.length;
		//Create the auxiliary array with the size of (n+1)x(capacity+1)
		int[][] dp = new int[n + 1][capacity + 1];
		//Define the first column of every row to be zero, as it will act as the case were the capacity itself is zero
		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}
		//Define the first row to be zero, as it will represent the 0th item, meaning no item.
		for (int i = 0; i < capacity; i++) {
			dp[0][i] = 0;
		}
		int currentItemWeight = 0;
		int currentItemValue = 0;
		//The outer for loop that will loop through each row starting from 1 to n
		for (int i = 1; i <= n; i++) {
			//The inner for loop that will loop through an incrementing capacity starting from 1 to the overall capacity of
			//the knapsac.
			for (int j = 1; j <= capacity; j++) {
				currentItemValue = values[(i-1)]; //Represents the current items value, due to indexing it is at i-1
				currentItemWeight = weights[(i-1)];//Represents the current items weight, due to indexing it is at i-1
				//The First case, if the weight of the current item is greater than the current capacity j		
				if (currentItemWeight > j) {
					//set the current cell to be the previous rows value
					dp[i][j] = dp[i - 1][j];
				} else {
					int included = dp[i - 1][j - currentItemWeight] + currentItemValue;
					int notIncluded = dp[i - 1][j];
					//Set the current cell to be one of two options, the current item is included or is excluded.
					dp[i][j] = max(notIncluded, included);
				}
			}
		}
		//return the final cell, as it will have the optimal value for the knapsac problem.
		return backtrack(dp);
	}

//	@Override
//	public List<Integer> solve() {
//		int n = weights.length;
//
//		int[][] dp = new int[n + 1][capacity + 1];
//		for (int i = 1; i < n; i++) {
//			for (int j = 0; j < capacity; j++) {
//				if (weights[i] > j) {
//					dp[i][j] = dp[i - 1][j];
//				} else {
//					int included = dp[i - 1][j - weights[i]] + values[i];
//					int notIncluded = dp[i - 1][j];
//
//					dp[i][j] = max(notIncluded, included);
//				}
//			}
//		}
//		int optimalValue = dp[n][capacity];
//		//System.out.println(optimalValue);
//
//		// TODO back track not yet implemented
//
//		return new ArrayList<>();
//	}
	
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
	private List<Integer> backtrack(int[][] dp) {
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
