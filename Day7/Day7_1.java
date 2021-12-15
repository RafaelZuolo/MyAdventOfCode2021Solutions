import java.util.Arrays;
public class Day7_1 {
    
    public static void main(String[] args) {
        String[] input = StdIn.readLine().split(",");
        int[] pos = new int[input.length];
        for (int i = 0; i < pos.length; i++) 
            pos[i] = Integer.parseInt(input[i]); 
        
        Arrays.sort(pos);
        int mid = pos[pos.length/2];
        int cost = 0;
        for (int i = 0; i < pos.length; i++) {
            cost += Math.abs(pos[i] - mid);
        }
        System.out.println(cost);
    }
}