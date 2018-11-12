import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


/**
 * https://www.hackerrank.com/challenges/luck-balance/problem
    Go through the contests and add all unimportant contests to luck.
    The unimportant contests get added to a maxHeap. 

    Iterate k times through the max heap; each iteartion returns a contest that we should lose

    Go through the rest of the heap and "win". 


    Because the heap is sorted (largest first; ie max heap), we want to maximize our luck but losing the contests
    with the greatest luck. Then, we simply just win the ones with the least luck values. 

    Runtime: 
        We are iterating through the entire array, each time adding up to n contests to a max heap.
        Each addition to a heap is O(log n), so total runtime for this is O(n (log n)). 
 */



public class Solution {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        
        PriorityQueue<Integer> imptCont = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        
        int luck = 0;
        
        //Add all unimpt contests
        // Put important contests into a Prio Queue
        for (int i = 0; i < contests.length; i++){
            if (contests[i][1] == 0)
                luck += contests[i][0];
            else {
                imptCont.add(contests[i][0]);
            }
        }
        
        for (int i = 0; i < k; i++){
            if (imptCont.peek() == null){
                break;
            }
            luck += imptCont.poll();
            System.out.println(" Total: " + luck);
        }
        
        Integer c;
        while ((c = imptCont.poll()) != null){
            luck -= c;
        }
        
        return luck;
        
    
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
