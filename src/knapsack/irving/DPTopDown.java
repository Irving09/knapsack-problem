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
    }

    @Override
    public int solve() {
        int item = 0;
        int weightSoFar = 0;

        return recurse(item, weightSoFar, new HashMap<>());
    }

    private int recurse(int item, int weightSoFar, Map<String, Integer> cache) {
        int n = weights.length;
        if (item == n) return 0;

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
                return values[item]     + cache.get(cacheKey);
            } else {
                // only recurse when necessary
                int result = recurse(nextItem, weightItemIncluded, cache);

                // record subproblem result
                cache.put(cacheKey, result);

                // include item's value + subProblem's result
                return values[item]     + result;
            }
        }
    }

    private String toCacheKey(int item, int weight) {
        return item + ":" + weight;
    }
}
