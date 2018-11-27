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

}
