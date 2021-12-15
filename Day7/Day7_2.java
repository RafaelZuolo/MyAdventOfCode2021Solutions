import java.util.Arrays;
public class Day7_2 {
    // This cost is very similar to k-means cost, thus my first try
    // is to get the optimal for 1-means in the real line, then try
    // to wiggle the solution to see if we get a better result.
    public static void main(String[] args) {
        String[] input = StdIn.readLine().split(",");
        int[] pos = new int[input.length];
        for (int i = 0; i < pos.length; i++) 
            pos[i] = Integer.parseInt(input[i]); 
        
        //Arrays.sort(pos);
        //int mid = pos[pos.length/2];
        int mean = 0;
        for (int i = 0; i < pos.length; i++) 
            mean += pos[i];
        //System.out.println(mean);
        mean = (int)Math.round((double)mean/(double)pos.length);
        //System.out.println(mean);
        
        int costI = 0;
        int costF = 0;
        for (int i = 0; i < pos.length; i++) {
            int dist = Math.abs(pos[i] - mean);
            costI += ((dist)*(dist+1))/2;
        } 
        System.out.println("1-means cost:" + costI);
        for (int i = 0; i < pos.length; i++) {
            int dist = Math.abs(pos[i] - mean + 1);
            costF += ((dist)*(dist+1))/2;
        } 
        int wiggle = 2;
        while (costF < costI) {
            costI = costF;
            costF = 0;
            for (int i = 0; i < pos.length; i++) {
                int dist = Math.abs(pos[i] - mean + wiggle);
                costF += ((dist)*(dist+1))/2;
            } 
            wiggle++;
        }
        System.out.println("Wiggle to a higher point: "+ costI);
        int minCost = costI;
        costF = 0;
        for (int i = 0; i < pos.length; i++) {
            int dist = Math.abs(pos[i] - mean - 1);
            costF += ((dist)*(dist+1))/2;
        } 
        wiggle = 2;
        while (costF < costI) {
            costI = costF;
            costF = 0;
            for (int i = 0; i < pos.length; i++) {
                int dist = Math.abs(pos[i] - mean - wiggle);
                costF += ((dist)*(dist+1))/2;
            } 
            wiggle++;
        }
        minCost = costI;
        System.out.println("can we decrease the cost by lowering the point? " + minCost);
    }
}