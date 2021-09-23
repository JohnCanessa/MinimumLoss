import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 
 */
public class MinimumLoss {


    /**
     * Buy the house in one year and sell it in another, 
     * and must be done at a loss.
     * One must minimize the financial loss.
     * Time: O (n log(n)) - Space: O(n)
     */
    public static int minimumLoss(List<Long> prices) {

        // **** sanity check(s) ****
        if (prices.size() < 2) return 0;
        
        if (prices.size() == 2)  {
            if (prices.get(0) < prices.get(1)) return -1;
            else return (int)(prices.get(0) - prices.get(1));
        }

        // **** initialization ****
        long minLoss    = Long.MAX_VALUE;
        long yearLoss   = 0;

        // **** allocate and populate map for prices and indices - O(n) ****
        Map<Long, Integer> priceIndexMap = new HashMap<>();
        for (int i = 0; i < prices.size(); i++)
            priceIndexMap.put(prices.get(i), i);

        // ???? ????
        System.out.println("minimumLoss <<< priceIndexMap: " + priceIndexMap.toString());

        // **** sort the price list - O(n log(n)) ****
        Collections.sort(prices);

        // ???? ????
        System.out.println("minimumLoss <<< prices: " + prices.toString());

        // **** traverse the sorted list of prices from
        //       high to low looking for the minimum loss - O(n) ****
        for (int i = prices.size() - 1; i > 0; i--) {
           
            // **** skip indices out of order (need to BUY before SELL) ****
            if (priceIndexMap.get(prices.get(i)) > priceIndexMap.get(prices.get(i - 1)))
                continue;

            // **** compute year loss ****
            yearLoss = prices.get(i) - prices.get(i - 1);

            // ???? ????
            System.out.println("minimumLoss <<< yearLoss: " + yearLoss);

            // **** update the minimum loss ****
            if (minLoss > yearLoss) minLoss = yearLoss;

            // ???? ????
            System.out.println("minimumLoss <<< minLoss: " + minLoss);
        }

        // **** return the minimum loss ****
        return (int)minLoss;
    }


    /**
     * Test scaffold.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** skip count of prices ****
        br.readLine();

        // **** read price list into a list ****
        List<Long> prices = Stream.of(br.readLine().trim().split(" "))
                                .map(Long::parseLong)
                                .collect(Collectors.toList());

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< prices: " + prices.toString());

        // **** compure result and display it ****
        System.out.println("main <<< minimumLoss: " + minimumLoss(prices));
    }

}