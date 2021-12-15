import java.util.*;
public class Day14_1 {
    
    public static char[] applyRule(char[] poly, Map<String, Character> rules) {
        
        char[] out = new char[2*(poly.length-1) + 1];
        for (int i = 0; i < poly.length; i++) 
            out[2*i] = poly[i];
        for (int i = 0; i < poly.length-1; i++) {
            char[] x = new char[2];
            x[0] = poly[i];
            x[1] = poly[i+1];
            
            out[2*i+1] = rules.get(new String(x));
        }
        return out;
    }
    public static void main(String[] args) {
        int steps = 40;
        Scanner sc = new Scanner(System.in);
        char[] poly = sc.nextLine().toCharArray();
        sc.nextLine();
        Map<String,Character> rules = new TreeMap<>();
        
        while(sc.hasNextLine()) {
            String rule = sc.nextLine();
            rules.put(rule.substring(0,2), Character.valueOf(rule.charAt(rule.length()-1)));
        }
        //System.out.println(Arrays.toString(poly));
        for (int i = 0; i < steps; i++) {
            
            poly = applyRule(poly, rules);
            //System.out.println(Arrays.toString(poly));
        }
        Map<Character, Long> freq = new TreeMap<>();
        for (int i = 0; i < poly.length; i++) {
            Long j = freq.putIfAbsent(Character.valueOf(poly[i]), Long.valueOf(0));
            if (j != null) freq.replace(Character.valueOf(poly[i]), j+1);
        }
        Long max = Long.valueOf(0);
        Long min = Long.MAX_VALUE;
        for (Map.Entry<Character, Long> E : freq.entrySet()) {
            max = max.compareTo(E.getValue()) < 0 ? E.getValue() : max;
            min = min.compareTo(E.getValue()) > 0 ? E.getValue() : min;
        }
        System.out.println(max - min);
    }
}
