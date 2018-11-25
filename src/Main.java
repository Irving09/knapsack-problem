import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Random;

import knapsack.irving.BruteForce;
import knapsack.irving.DPBottomUp;
import knapsack.irving.DPTopDown;
import knapsack.irving.Knapsack;

public class Main {

    public static void main(String[] args) {
    	
    	//init variables
    	int n = 20; 	
    	int[] allValues  = new int[n];
    	int[] allWeights = new int[n];
    	int capacity = 40;
    	long startTime = 0;
    	long endTime = 0;
    	long timeTaken = 0;
    	Random r = new Random(); 	
    	for(int i = 0; i < n; i++) {
    		allValues[i] = r.nextInt(99)+1;
    		allWeights[i] = r.nextInt(99)+1;
    	}
    	
    	//Files.write(, bytes, options)
    	//Do a Start so that further tests have a warm start.
    	int[] testVal = {4};
    	int[] testWeight = {3};
    	int testCapacity = 3;
    	
    	Knapsack b = new BruteForce(testWeight,testVal, testCapacity);   	
    	Knapsack bu = new DPBottomUp(testWeight,testVal, testCapacity);
    	Knapsack td = new DPTopDown(testWeight,testVal, testCapacity);
    	
    	
    	System.out.println("--Tests for varying n size--");
    	//Start Warm Start for Tests for each knapsack implementation, going
    	//through an additional 10% of the original dataset size through each
    	//iteration.
    	
    	for(int i = 0; i < 10; i++) {
    		//Define the sub-dataset for the given section size of 
    		//the original dataset
    		int[] tempValues = new int[(allValues.length/10)*(i+1)];
    		int[] tempWeights = new int[(allWeights.length/10)*(i+1)];
    		for(int j = 0; j < (allValues.length/10)*(i+1); j++) {
    			tempValues[j] = allValues[j];
    			tempWeights[j] = allWeights[j];
    		}
    		//Define the portion of the capacity.
    		
            startTime = System.nanoTime();
            Knapsack bruteForce = new BruteForce(tempWeights,tempValues, capacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            System.out.println("Brute Force Time: "+ timeTaken);
            
            startTime = System.nanoTime();
            Knapsack bottomUp = new DPBottomUp(tempWeights,tempValues, capacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            System.out.println("Bottom Time: "+ timeTaken);
            
            startTime = System.nanoTime();
            Knapsack topDown = new DPTopDown(tempWeights,tempValues, capacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            System.out.println("Top Time: "+ timeTaken);
            System.out.println();
    	}
    	System.out.println("--Tests for varying Capacity size W--");
    	//
    	for(int i = 0; i < 10; i++) {
    		
    		int tempCapacity = (capacity/10)*(i+1);
            startTime = System.nanoTime();
            Knapsack bruteForce = new BruteForce(allWeights,allValues, tempCapacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            System.out.println("Brute Force Time: "+ timeTaken);
            
            startTime = System.nanoTime();
            Knapsack bottomUp = new DPBottomUp(allWeights,allValues, tempCapacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            System.out.println("Bottom Time: "+ timeTaken);
            
            startTime = System.nanoTime();
            Knapsack topDown = new DPTopDown(allWeights,allValues, tempCapacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            System.out.println("Top Time: "+ timeTaken);
            System.out.println();
    	}
//        startTime = System.nanoTime();
//        Knapsack bruteForce = new BruteForce(allWeights,allValues, capacity);
//        endTime = System.nanoTime();
//        timeTaken = endTime - startTime;
//        System.out.println("Brute Force Time: "+ timeTaken);
//        startTime = System.nanoTime();
//        Knapsack bottomUp = new DPBottomUp(allWeights,allValues, capacity);
//        endTime = System.nanoTime();
//        timeTaken = endTime - startTime;
//        System.out.println("Bottom Up Time: "+ timeTaken);
//        startTime = System.nanoTime();
//        Knapsack topDown = new DPTopDown(allWeights,allValues, capacity);
//        endTime = System.nanoTime();
//        timeTaken = endTime - startTime;
//        System.out.println("Top Down Time: "+ timeTaken);
        //topDown.solve();
        
        // TODO implement each knapsack and run test cases
    }
    
}
