package ex2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Result {

    /*
     * Complete the 'stockPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY stocksProfit
     *  2. LONG_INTEGER target
     */
    public static int stockPairs(List<Integer> stocksProfit, long target) {
        var stockCounters = new HashMap<Integer, Integer>();
        var pairs = 0;
        for (var stock : stocksProfit) {
            var pair = Math.toIntExact(target - stock);
            if (stockCounters.containsKey(pair)) {
                if ((pair == stock && stockCounters.get(stock) == 1)
                || (pair != stock && !stockCounters.containsKey(stock))) {
                    pairs++;
                }
            }
            stockCounters.put(stock, 1 + stockCounters.getOrDefault(stock, 0));
        }
        return pairs;
    }

    public static void main(String[] args) {
//        System.out.println(stockPairs(List.of(1, 2), 3));
        System.out.println(stockPairs(List.of(5, 7, 9, 13, 11, 6 ,6 ,3 ,3), 12));
    }

}
