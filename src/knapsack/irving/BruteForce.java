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
public class BruteForce extends Knapsack {

    public BruteForce(int[] weights, int[] values, int capacity) {
        super(weights, values, capacity);
        solve();
        //System.out.println(solve());
    }

    /*
     * 
     * This is the brute force algorithm. You need to consider each of the 2^n
		subsets of the n items and for each subset, check to see whether the items fit into the
		knapsack (capacity W), and if they do fit, calculate how much total value the subset has
		and whether it is greater than the maximum valued subset that the algorithm has seen so
		far.
		This implementation involves developing a way to iterate through the subsets. You could
		use an array of integers to indicate which elements are in your current subset, and cycling
		through all subsets systematically. If you iterate through each subset by “incrementing”
		the number represented by the integer array, it may take up to O(n) bit flips in any one
		increment, but on average, there will be a constant number of bit flips for a total of O(2^n
		) work associated with the management of the subsets.
		Determining whether the subset of items fits into the knapsack and (if it does) what its
		total value is should take O(n) time.
     * 
     */
    
    
    @Override
    public int solve() {
        int optimalValue = 0;
    	int tempTotalWeight = 0;
    	int tempTotalValue = 0;
        int n = this.values.length;
        int optimalSubset = 0;
        //test code
        int total = 0;
        for (int i = 0; i<Math.pow(2, n); i++) {
        	tempTotalValue = 0;
        	tempTotalWeight = 0;
        	for(int j = 0; j < n;j++) {
        		total++;
        		if((i & (int)(Math.pow(2, j))) > 0) {
            		tempTotalWeight += weights[j];
            		tempTotalValue += values[j];
        		}
        	}
        	if(tempTotalWeight <= capacity && tempTotalValue > optimalValue) {
        		optimalValue = tempTotalValue;
        	}        	       	
        }
        //int currentPowerOfTwo = Math.pow(2, n);
//        int[][] subsets = new int[(int)Math.pow(2, n)][n];
//    	int divider = 0;//(int) Math.pow(2, n-1);
//    	int totalForDivide = 0;
//    	double powerOfTwoToN = Math.pow(2, n);
//        boolean isOne = false;
//    	for(int i = 0; i < n; i++) {        	
//        	divider = (int) Math.pow(2, n-i-1);
//        	isOne = false;
//        	totalForDivide = 0;
//        	while(totalForDivide < powerOfTwoToN) {
//        		for(int j = totalForDivide; j < totalForDivide +divider;j++) {
//        			if(isOne) {
//        				subsets[j][i]=1;
//        			} else {
//        				subsets[j][i]=0;
//        			}
//        		}
//        		totalForDivide += divider;
//        		isOne = !isOne;
//        	}
//        }
//    	
//    	total = 0;
//    	optimalValue = 0;
//    	for(int i = 0; i < Math.pow(2, n); i++) {
//    		tempTotalValue = 0;
//        	tempTotalWeight = 0;
//        	for(int j = 0; j < n;j++) {
//        		total++;
//        		
//        		if(subsets[i][j] == 1) {	
//            		tempTotalWeight += weights[j];
//            		tempTotalValue += values[j];
//        		}
//        	}
//        	if(tempTotalWeight <= capacity && tempTotalValue > optimalValue) {
//        		optimalValue = tempTotalValue;
//        	}
//    	}
//        int[][] subsets = generateSubsets();
//        for(int i = 0; i < subsets.length; i++) { //IS WRONG FIX LATER
//        	
//        	int[] subsetIndexValues = subsets[i]; //REPRESENTS THE LOCATIONS OR INDEXES OF THE VALUES IN THE MAIN INT ARR
//            
//        	int tempTotalWeight = 0;
//        	int tempTotalValue = 0;
//        	//for each subset
//        	int j = 0;
//        	while(subsetIndexValues[j] > 0) {
//        		tempTotalWeight += weights[subsetIndexValues[j]];
//        		tempTotalValue += values[subsetIndexValues[j]];
//        		j++;
//        	}
////        	for(int j = 0; j < subsetIndexValues.length; j++) {
////        		tempTotalWeight += weights[subsetIndexValues[j]];
////        		tempTotalValue += values[subsetIndexValues[j]];
////            }
//        	if(tempTotalWeight < capacity) {
//        		if(tempTotalValue > optimalValue) {
//        			optimalValue = tempTotalValue;
//        		}
//        	}
//        }
//                        
    	return optimalValue;
    }
    
    private void recursiveSubset(int[] subset, int startingLocation) {
    	
    }
//    private int[][] generateSubsets() {
//    	int[][] subset = new int[values.length][values.length];
//    	Arrays.fill(subset, -1);
//    	
//    	
//    	
//    	return subset;
//    }
}
