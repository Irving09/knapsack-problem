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

import knapsack.parents.Knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author irving09 <innoirvinge@gmail.com>
 * @author Joshua Meigs <joshua.meigs@gmail.com>
 */
public class BruteForce extends Knapsack {

	public BruteForce() {
		super();
	}

    public BruteForce(int[] weights, int[] values, int capacity) {
        super(weights, values, capacity);

    }

    /**
	 * Computes the optimal list of items for the knapsack problem.
	 *
	 * @return A list representing the indices of the chosen items which give the optimal knapsack value
	 * */
    @Override
    public List<Integer> solve() {
        int optimalValue = 0;
    	int tempTotalWeight = 0;
    	int tempTotalValue = 0;
        int n = this.values.length;
        int optimalIndex = -1;
        //Loop through every subset
        for (int i = 0; i<Math.pow(2, n); i++) {
        	tempTotalValue = 0;
        	tempTotalWeight = 0;
        	//Loop for every index within the subset
        	for(int j = 0; j < n;j++) {
        		//If the current value of j is within the subset, add its
        		//potential weight and value
        		if((i & (int)(Math.pow(2, j))) > 0) {
            		tempTotalWeight += weights[j];
            		tempTotalValue += values[j];
            	}
        	}
        	//if a new optimal value was found and its weight does not
        	//exceed the bags capacity, make it the new optimal index
        	if(tempTotalWeight <= capacity && tempTotalValue > optimalValue) {
        		optimalIndex = i;
        		optimalValue = tempTotalValue;
        	}        	       	
        }
        //Create List to Return
        List<Integer> theOptimalSubsetList = new ArrayList<>();
        //Populate List
        for(int j = 0; j < n && optimalIndex >= 0; j++) {
        	if((optimalIndex & (int)(Math.pow(2, j))) > 0) {
        		theOptimalSubsetList.add(j);
    		}
        }
    	return theOptimalSubsetList;
    }

	@Override
	public String name() {
		return "brute-force";
	}
}
