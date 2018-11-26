import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import knapsack.BruteForce;
import knapsack.DPBottomUp;
import knapsack.DPTopDown;
import knapsack.Knapsack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
    	
    	//init variables
    	int n = 20; 	
    	int[] allValues  = new int[n];
    	int[] allWeights = new int[n];
    	int capacity = 40;
    	long startTime = 0;
    	long endTime = 0;
    	long timeTaken = 0;
    	ArrayList<Map<Integer, Long>> listOfKnapsacTypes = new ArrayList<Map<Integer,Long>>();
    	
    	Random r = new Random(); 	
    	for(int i = 0; i < n; i++) {
    		allValues[i] = r.nextInt(99)+1;
    		allWeights[i] = r.nextInt(99)+1;
    	}
    	
    	//Do a Start so that further tests have a warm start.
    	int[] testVal = {4};
    	int[] testWeight = {3};
    	int testCapacity = 3;
    	
    	Knapsack b = new BruteForce(testWeight,testVal, testCapacity);   	
    	Knapsack bu = new DPBottomUp(testWeight,testVal, testCapacity);
    	Knapsack td = new DPTopDown(testWeight,testVal, testCapacity);
    	
    	
    	//Start Warm Start for Tests for each knapsack implementation, going
    	//through an additional 10% of the original dataset size through each
    	//iteration.
    	
    	Map<Integer, Long> pointsForBruteForce = new HashMap<Integer, Long>();
    	Map<Integer, Long> pointsForBottomUp = new HashMap<Integer, Long>();
    	Map<Integer, Long> pointsForTopUp = new HashMap<Integer, Long>();
    	for(int i = 0; i < 10; i++) {
    		//Define the sub-dataset for the given section size of 
    		//the original dataset
    		int p = (allValues.length/10)*(i+1);
    		int[] tempValues = new int[p];
    		int[] tempWeights = new int[p];
    		for(int j = 0; j < p; j++) {
    			tempValues[j] = allValues[j];
    			tempWeights[j] = allWeights[j];
    		}
    		//Define the portion of the capacity.
    		
            startTime = System.nanoTime();
            Knapsack bruteForce = new BruteForce(tempWeights,tempValues, capacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            pointsForBruteForce.put(p, timeTaken);
            
            startTime = System.nanoTime();
            Knapsack bottomUp = new DPBottomUp(tempWeights,tempValues, capacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            pointsForBottomUp.put(p, timeTaken);
            
            startTime = System.nanoTime();
            Knapsack topDown = new DPTopDown(tempWeights,tempValues, capacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            pointsForTopUp.put(p, timeTaken);
        }
    	
    	//Write out to the N files
    	PrintWriter p = new PrintWriter(new File("graphForDifferentNSizes.csv"));
    	listOfKnapsacTypes.add(pointsForBruteForce);
    	listOfKnapsacTypes.add(pointsForBottomUp);
    	listOfKnapsacTypes.add(pointsForTopUp);
    	//Write to File
    	writeToFileDifCol(p, listOfKnapsacTypes, n, false);
    	//Close File
    	p.close();
    	//Remove Maps from list to start next values
    	listOfKnapsacTypes.remove(pointsForBruteForce);
    	listOfKnapsacTypes.remove(pointsForBottomUp);
    	listOfKnapsacTypes.remove(pointsForTopUp);
    	//Redefine the Maps
    	pointsForBruteForce = new HashMap<Integer, Long>();
    	pointsForBottomUp = new HashMap<Integer, Long>();
    	pointsForTopUp = new HashMap<Integer, Long>();
    	
    	for(int i = 0; i < 10; i++) {
    		
    		int tempCapacity = (capacity/10)*(i+1);
            startTime = System.nanoTime();
            Knapsack bruteForce = new BruteForce(allWeights,allValues, tempCapacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            pointsForBruteForce.put(tempCapacity, timeTaken);
            
            startTime = System.nanoTime();
            Knapsack bottomUp = new DPBottomUp(allWeights,allValues, tempCapacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            pointsForBottomUp.put(tempCapacity, timeTaken);
            
            startTime = System.nanoTime();
            Knapsack topDown = new DPTopDown(allWeights,allValues, tempCapacity);
            endTime = System.nanoTime();
            timeTaken = endTime - startTime;
            pointsForTopUp.put(tempCapacity, timeTaken);
    	}
    	//print out to W files
    	p = new PrintWriter(new File("graphForDifferentCapacities.csv"));
    	listOfKnapsacTypes.add(pointsForBruteForce);
    	listOfKnapsacTypes.add(pointsForBottomUp);
    	listOfKnapsacTypes.add(pointsForTopUp);
    	//Write to File
    	writeToFileDifCol(p, listOfKnapsacTypes, n, true);
    	//Close File
    	p.close();
    	//End Program
    	return;
    }
    public static void writeToFileSameCol(PrintWriter p, ArrayList<Map<Integer,Long>> listOfAllKnapsacTypes
    		, int constantFactorSize, boolean constantFactorIsN) {
    	
    	StringBuilder sb = new StringBuilder();
    	//Create Heading for each Column
    	sb.append(constantFactorIsN?"N ":"Capacity ");
    	sb.append("Size:");
    	sb.append(constantFactorSize);
    	sb.append("\n");
    	sb.append("\"N , Runtime\",\"N , Runtime\",\"N , Runtime\" \n");
    	//Write Data to each Row
    	for(Integer xValue: listOfAllKnapsacTypes.get(0).keySet()) {//Since all Points share same X Values, this is able to work 
    		for(Map<Integer, Long> knapsacType: listOfAllKnapsacTypes) {
    			sb.append("\"\"\"");
        		sb.append(xValue);
        		sb.append(",");
        		sb.append(knapsacType.get(xValue));
        		sb.append("\"\"\",");
    		}
    		sb.append("\n");
    	}
    	sb.setCharAt(sb.length()-1, '\n');
    	p.write(sb.toString());
    }
    
    public static void writeToFileDifCol(PrintWriter p, ArrayList<Map<Integer,Long>> listOfAllKnapsacTypes
    		, int constantFactorSize, boolean constantFactorIsN) {
    	
    	StringBuilder sb = new StringBuilder();
    	//Create Heading for each Column
    	sb.append(constantFactorIsN?"N ":"Capacity ");
    	sb.append("Size:");
    	sb.append(constantFactorSize);
    	sb.append("\n");
    	sb.append("Brute Force,, Bottom-Up,, Top-Down\n");
    	for(int i = 0; i < 3; i++) {
    		sb.append(constantFactorIsN?"W,":"N,");
    		sb.append("Runtime,");
    	}
    	sb.append("\n");
    	//Write Data to each Row
    	for(Integer xValue: listOfAllKnapsacTypes.get(0).keySet()) {//Since all Points share same X Values, this is able to work 
    		for(Map<Integer, Long> knapsacType: listOfAllKnapsacTypes) {
        		sb.append(xValue);
        		sb.append(",");
        		sb.append(knapsacType.get(xValue));
        		sb.append(",");
    		}
    		sb.append("\n");
    	}
    	sb.setCharAt(sb.length()-1, '\n');
    	p.write(sb.toString());
    }
   
    
}
