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

import static java.lang.Math.max;

/**
 * @author irving09 <innoirvinge@gmail.com>
 * @author Joshua Meigs <joshua.meigs@gmail.com>
 */
public class DPBottomUp extends KnapsackDP {

	public DPBottomUp() {
		super();
	}

	public DPBottomUp(int[] weights, int[] values, int capacity) {
		super(weights, values, capacity);
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
			//the knapsack.
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

		return backtrack(dp);
	}

	@Override
	public String name() {
		return "bottom-up";
	}

}
