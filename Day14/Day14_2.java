import java.util.*;
public class Day14_2 {
    
    public static Map<String,Long> applyRule(Map<String, Long> pairs, Map<String, String> rules, Map<String, Long> freq) {
        Map<String, Long> out = new TreeMap<String, Long>();
        for (Map.Entry<String, Long> E : pairs.entrySet()) {
            String p1 = E.getKey().substring(0,1);
            String p2 = E.getKey().substring(1,2);
            String elem = rules.get(E.getKey());
            //System.out.println(E.getKey()+" "+p1+" "+p2+ " "+elem);
            Long l0 = freq.putIfAbsent(elem, Long.valueOf(1));
            if (l0 != null) freq.replace(elem, l0+E.getValue());
            
            //out.replace(E.getKey(), out.get(E.getKey()) - E.getValue());

            p1 = p1.concat(elem);
            p2 = elem.concat(p2);

            Long l1 = out.putIfAbsent(p1, E.getValue());
            if (l1 != null) out.replace(p1, l1 + E.getValue());

            Long l2 = out.putIfAbsent(p2, E.getValue());
            if (l2 != null) out.replace(p2, l2 + E.getValue());
        }
        return out;
    }
    public static void main(String[] args) {
        int steps = 40;
        Scanner sc = new Scanner(System.in);
        String init = sc.nextLine();
        sc.nextLine();
        Map<String, String> rules = new TreeMap<>();
        Map<String, Long> pairs = new TreeMap<>();
        Map<String, Long> freq = new TreeMap<>();
        
        for (int i = 0; i < init.length()-1; i++) {
            pairs.put(init.substring(i, i+2), Long.valueOf(1));
        }
        for (int i = 0; i < init.length(); i++) {    
            Long l = freq.putIfAbsent(init.substring(i, i+1), Long.valueOf(1));
            if (l != null) freq.replace(init.substring(i, i+1), l+1);
        }
        //for (Map.Entry<String, Long> E: freq.entrySet()) System.out.println(E.getKey() + " " + E.getValue());
        
        while(sc.hasNextLine()) {
            String rule = sc.nextLine();
            rules.put(rule.substring(0,2), rule.substring(rule.length()-1, rule.length()));
        }
        //System.out.println(Arrays.toString(poly));
        for (int i = 0; i < steps; i++) {
            pairs = applyRule(pairs, rules, freq);
            //System.out.println("step " + (i+1));
            //for (Map.Entry<String, Long> E: freq.entrySet()) System.out.println(E.getKey() + " " + E.getValue());
        }
        
        Long max = Long.valueOf(0);
        Long min = Long.MAX_VALUE;
        for (Map.Entry<String, Long> E : freq.entrySet()) {
            //System.out.println(E.getKey() + " " + E.getValue());
            max = max.compareTo(E.getValue()) < 0 ? E.getValue() : max;
            min = min.compareTo(E.getValue()) > 0 ? E.getValue() : min;
        }
        System.out.println(max - min);
    }
}