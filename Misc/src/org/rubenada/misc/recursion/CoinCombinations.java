package org.rubenada.misc.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * Given 4 coins values (1, 5, 10, 20) find all the combinations of coins that can add up to a certain value
 */
public class CoinCombinations {

    static Map<Integer, Integer> comb20 = new HashMap<>();
    static Map<Integer, Integer> comb10 = new HashMap<>();
    static Map<Integer, Integer> comb5 = new HashMap<>();

    // Given 4 coins values (1, 5, 10, 20) find all the combinations of coins that can add up to a certain value
    public static final int findCoinCombinations (int k, boolean withDP) {
        if (k<=0)
            return 0;

        if (withDP) {
            return findCoinCombinations(k, 20, comb20, comb10, comb5);
        }

        return findCoinCombinations(k, 20);
    }

    // pure recursion without DP
    public static final int findCoinCombinations (int k, int denomination) {
        int nextDenomination = 0;

        switch (denomination) {
            case 20:
                nextDenomination = 10;
                break;
            case 10:
                nextDenomination = 5;
                break;
            case 5:
                nextDenomination = 1;
                break;
            case 1:
                return 1;
        }
        int result = 0;

        for (int i=0; i*denomination<=k;  i++) {
            result += findCoinCombinations(k - i*denomination, nextDenomination);
        }

        return result;
    }

    // with DP
    public static final int findCoinCombinations (int k, int denomination, Map<Integer,Integer> comb20,
                                                  Map<Integer,Integer> comb10, Map<Integer,Integer> comb5) {
        int nextDenomination = 0;
        Map<Integer,Integer> denominationMap = null;
        Map<Integer,Integer> nextDenominationMap = null;
        switch (denomination) {
            case 20:
                denominationMap = comb20;
                nextDenomination = 10;
                nextDenominationMap = comb10;
                break;
            case 10:
                denominationMap = comb10;
                nextDenomination = 5;
                nextDenominationMap = comb5;
                break;
            case 5:
                denominationMap = comb5;
                nextDenomination = 1;
                break;
            case 1:
                return 1;
        }

        Integer result = denominationMap.get(k);
        if (result != null)
            return result;

        result = 0;
        for (int i=0; i*denomination<=k; i++) {
            if (nextDenomination == 1)
                result+=1;
            else {
                int nextK = k - i * denomination;
                Integer nextRes = nextDenominationMap.get(nextK);
                if (nextRes == null) {
                    nextRes = findCoinCombinations(nextK, nextDenomination);
                    nextDenominationMap.put(nextK, nextRes);
                }
                result += nextRes;
            }
        }
        denominationMap.put(k, result);
        return result;
    }

    public static void main(String args[]) {
        int value = 2000;
        long startTime = System.nanoTime();
        System.out.println("findCoinCombinations DP " + value + " = " + findCoinCombinations(value, true));
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);

        startTime = System.nanoTime();
        System.out.println("findCoinCombinations no-DP " + value + " = " + findCoinCombinations(value, false));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);


        value = 2000;
        startTime = System.nanoTime();
        System.out.println("findCoinCombinations DP " + value + " = " + findCoinCombinations(value, true));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);

        startTime = System.nanoTime();
        System.out.println("findCoinCombinations no-DP " + value + " = " + findCoinCombinations(value, false));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);

        value = 1000;
        startTime = System.nanoTime();
        System.out.println("findCoinCombinations DP " + value + " = " + findCoinCombinations(value, true));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);

        startTime = System.nanoTime();
        System.out.println("findCoinCombinations no-DP " + value + " = " + findCoinCombinations(value, false));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);

        value = 500;
        startTime = System.nanoTime();
        System.out.println("findCoinCombinations DP " + value + " = " + findCoinCombinations(value, true));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);

        startTime = System.nanoTime();
        System.out.println("findCoinCombinations no-DP " + value + " = " + findCoinCombinations(value, false));
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("estimatedTime (ms) = " + estimatedTime);
    }
}
