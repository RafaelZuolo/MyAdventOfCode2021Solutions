import java.util.List;
import java.util.LinkedList;
public class Day6_1 {
    
    public static void main(String[] args) { 
        String[] input = StdIn.readLine().split(",");
        List<Integer> start = new LinkedList<Integer>();
        List<Integer> step = new LinkedList<>();
        
        for(String s : input) start.add(Integer.parseInt(s));    
        for (int i = 0; i < 80; i++) {
            for (Integer in : start) {
                if (in.equals(Integer.valueOf(0))) {
                    step.add(Integer.valueOf(8));
                    step.add(Integer.valueOf(6));
                } else {
                    step.add(Integer.valueOf(in.intValue() - 1));
                }                
            }
            start = step;
            step = new LinkedList<Integer>();
        }
        System.out.println(start.size());
    }
}