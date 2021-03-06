/*Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.*/

// My Code
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length == 0) return 0;
        int max = 0;
        int min = prices[0];
        for(int i =0; i < prices.length; i++){
            min = Math.min(prices[i],min);
            max = Math.max(max, prices[i]-min);
        
        }
        return max;
    }
}

// NineChapter
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;  //just remember the smallest price
        int profit = 0;
        for (int i : prices) {
            min = i < min ? i : min;
            profit = (i - min) > profit ? i - min : profit;
        }

        return profit;
    }
}

