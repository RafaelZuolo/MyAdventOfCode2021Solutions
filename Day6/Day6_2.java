import java.util.List;
import java.util.LinkedList;
public class Day6_2 {
    
    public static void main(String[] args) { 
        String[] input = StdIn.readLine().split(",");
        long[] pop = new long[9];
        long[] step = new long[9];
        for (String s : input) {
            pop[Integer.parseInt(s)]++;
        }
        
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j<8; j++) {
                step[j] = pop[j+1];
            }
            step[6] += pop[0];
            step[8] += pop[0];
            pop = step;
            step = new long[9];
        }
        long totalPop = 0;
        for (int j = 0; j<9; j++) totalPop += pop[j];
        System.out.println(totalPop);
    }
}