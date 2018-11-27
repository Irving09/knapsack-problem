import java.io.FileNotFoundException;
import java.util.Random;

import CSVLogger.CSVLogger;
import knapsack.BruteForce;
import knapsack.DPBottomUp;
import knapsack.DPTopDown;
import knapsack.parents.Knapsack;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
    	
    	//init variables
    	int n = 20; 	
    	int[] allValues  = new int[n];
    	int[] allWeights = new int[n];
    	int capacity = 40;
    	Random r = new Random(); 	
    	for(int i = 0; i < n; i++) {
    		allValues[i] = r.nextInt(99)+1;
    		allWeights[i] = r.nextInt(99)+1;
    	}
    	//Create the csvLogger Class and open output file
    	CSVLogger csv = new CSVLogger();
    	csv.openFile();
    	//Do a Start so that further tests have a warm start.
    	int[] testVal = {4};
    	int[] testWeight = {3};
    	int testCapacity = 3;
    	
    	Knapsack b = new BruteForce(testWeight,testVal, testCapacity);   	
    	Knapsack bu = new DPBottomUp(testWeight,testVal, testCapacity);
    	Knapsack td = new DPTopDown(testWeight,testVal, testCapacity);
    	
    	b.solve();
    	bu.solve();
    	td.solve();
    	//Start Warm Start for Tests for each knapsack implementation, going
    	//through an additional 10% of the original dataset size through each
    	//iteration.
    	//W will now be the constant factor with varying item size n.
    	boolean nIsConstantFactor = false;
    	//Write header for varying item size n
    	csv.writeHeader(n, capacity, nIsConstantFactor);
    	
    	//Loop for varying item size n
    	for(int i = 0; i < 10; i++) {
    		//Define the sub-dataset for the given section size of 
    		//the original dataset
    		nIsConstantFactor = false;
    		int p = (allValues.length/10)*(i+1);
    		int[] tempValues = new int[p];
    		int[] tempWeights = new int[p];
    		for(int j = 0; j < p; j++) {
    			tempValues[j] = allValues[j];
    			tempWeights[j] = allWeights[j];
    		}
    		//create new instances of the algorithms and print to file
            Knapsack bruteForce = new BruteForce(tempWeights,tempValues, capacity);
            csv.logRuntime(bruteForce, p, capacity, nIsConstantFactor);
            
            Knapsack bottomUp = new DPBottomUp(tempWeights,tempValues, capacity);
            csv.logRuntime(bottomUp, p, capacity, nIsConstantFactor);
            
            Knapsack topDown = new DPTopDown(tempWeights,tempValues, capacity);
            csv.logRuntime(topDown, p, capacity, nIsConstantFactor);
            //Write new line for next row
            csv.writeNewLineToFile();
        }
    	//add spacing for next graph
    	csv.writeNewLineToFile();
    	csv.writeNewLineToFile();
    	//N will now be the constant factor with varying capacity W.
    	nIsConstantFactor = true;
    	//Write header for varying capacity W
    	csv.writeHeader(n, capacity, nIsConstantFactor);
    	//Loop for varying capacity size W
    	for(int i = 0; i < 10; i++) {
    		
    		int tempCapacity = (capacity/10)*(i+1);
    		//Define the portion of the capacity.
    		//create new instances of the algorithms and print to file
            
            Knapsack bruteForce = new BruteForce(allWeights,allValues, tempCapacity);
            csv.logRuntime(bruteForce, n, tempCapacity, nIsConstantFactor);
            
            Knapsack bottomUp = new DPBottomUp(allWeights,allValues, tempCapacity);
            csv.logRuntime(bottomUp, n, tempCapacity, nIsConstantFactor);
            
            Knapsack topDown = new DPTopDown(allWeights,allValues, tempCapacity);
            csv.logRuntime(topDown, n, tempCapacity, nIsConstantFactor);
            csv.writeNewLineToFile();
    	}
    	//Close the output file
    	csv.closeFile();
    	//End Program
    	return;
    }
    
    
      
    
}
