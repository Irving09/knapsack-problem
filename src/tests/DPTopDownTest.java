/**
 * CONFIDENTIAL INFORMATION
 * <p>
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 * <p>
 * Date: Nov 26, 2018
 * Copyright 2018 innoirvinge@gmail.com
 */
package tests;

import knapsack.Knapsack;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;


import java.util.List;

/**
 * Test cases were taken from https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html
 *
 * @author irving09 <innoirvinge@gmail.com>
 */
public class DPTopDownTest {

    @Test
    public void testTopDown1() {
        int w[] = {20, 10, 40, 30};
        int v[] = {40, 100, 50, 60};
        int W = 60;

        Knapsack topdown = new knapsack.DPTopDown(w, v, W);
        List<Integer> items = topdown.solve();
        int optimalValue = items.stream().mapToInt(chosen -> v[chosen]).sum();

        Assert.that(optimalValue == 200, "optimal value is wrong");
    }

    @Test
    public void testTopDown2() {
		int v[] = { 60, 100, 120 };
		int w[] = { 10, 20, 30 };
		int W = 50;

        Knapsack topdown = new knapsack.DPTopDown(w, v, W);
        List<Integer> items = topdown.solve();
        int optimalValue = items.stream().mapToInt(chosen -> v[chosen]).sum();

        Assert.that(optimalValue == 220, "optimal value "  + optimalValue + " is wrong");
    }

    @Test
    public void testTopDown6() {
		int w[] = { 23,31,29,44,53,38,63,85,89,82 };
        int v[] = { 92,57,49,68,60,43,67,84,87,72 };
           //		 1, 1, 1, 1, 0, 1, 0, 0, 0, 0
		int W = 165;

        Knapsack topdown = new knapsack.DPTopDown(w, v, W);
        List<Integer> items = topdown.solve();
        int optimalValue = items.stream().mapToInt(chosen -> v[chosen]).sum();

        Assert.that(optimalValue == 309, "optimal value "  + optimalValue + " is wrong");
    }

    @Test
    public void testTopDown3() {

        int[] w = { 56,59,80,64,75,17 };
        int[] v = { 50,50,64,46,50,5 };
           //        1, 1, 0, 0, 1,0
		int W = 190;

        Knapsack topdown = new knapsack.DPTopDown(w, v, W);
        List<Integer> items = topdown.solve();
        int optimalValue = items.stream().mapToInt(chosen -> v[chosen]).sum();

        Assert.that(optimalValue == 150, "optimal value "  + optimalValue + " is wrong");
    }

    @Test
    public void testTopDown4() {

        int[] w = {  70, 73, 77, 80, 82, 87, 90, 94, 98,106,110,113,115,118,120 };
        int[] v = { 135,139,149,150,156,163,173,184,192,201,210,214,221,229,240 };
           //         1,  0,  1,  0,  1,  0,  1,  1,  1,  0,  0,  0,  0,  1,  1

		int W = 750;

        Knapsack topdown = new knapsack.DPTopDown(w, v, W);
        List<Integer> items = topdown.solve();
        int optimalValue = items.stream().mapToInt(chosen -> v[chosen]).sum();

        Assert.that(optimalValue == 1458, "optimal value "  + optimalValue + " is wrong");
    }

    @Test
    public void testTopDown5() {

        int[] w = { 382745,799601,909247,729069,467902, 44328, 34610,698150,823460,903959,853665,551830,610856,670702,488960,951111,323046,446298,931161, 31385,496951,264724,224916,169684  };
        int[] v = { 825594,1677009,1676628,1523970, 943972,  97426,  69666,1296457,1679693,1902996,1844992,1049289,1252836,1319836, 953277,2067538, 675367, 853655,1826027,  65731, 901489, 577243, 466257, 369261 };

		int W = 6404180;

        Knapsack topdown = new knapsack.DPTopDown(w, v, W);
        List<Integer> items = topdown.solve();
        int optimalValue = items.stream().mapToInt(chosen -> v[chosen]).sum();

        Assert.that(optimalValue == 13549094, "optimal value "  + optimalValue + " is wrong");
    }

}
