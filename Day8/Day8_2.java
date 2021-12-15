import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
public class Day8_2 {
    
    public static Set<Character> toCharSet(String s) {
        Set<Character> set = new HashSet<Character>();
        char[] a = s.toCharArray();
        for (int i =0 ; i < a.length; i++) 
            set.add(Character.valueOf(a[i]));
        return set;
    }
    
    // check if a is subset of b
    public static boolean isSubset(Set<Character> a, Set<Character> b) {
        for (Character c : a) {
            if (!b.contains(c)) return false;
        }
        return true;
    }
    // return the size of the symmetric difference of a and b
    public static int symetricDiffSize(Set<Character> a, Set<Character> b) {
        int size = 0;
        for (Character c : a) {
            if (!b.contains(c)) size++;
        }
        for (Character c : b) {
            if (!a.contains(c)) size++;
        }
        return size;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        int answer = 0;
        for (String s : lines) {
            //Set<Set<Character>> izi = new HashSet<>(); // set for 1, 4, 7, 8
            Set<Set<Character>> easy = new HashSet<>(); // set for 2, 3, 5
            Set<Set<Character>> hard = new HashSet<>(); // set for 0, 6, 9
            Set<Character> s1 = null;
            Set<Character> s2 = null;
            Set<Character> s3 = null;
            Set<Character> s4 = null;
            Set<Character> s5 = null;
            Set<Character> s6 = null;
            Set<Character> s7 = null;
            Set<Character> s8 = null;
            Set<Character> s9 = null;
            Set<Character> s0 = null;
            String[] t = s.split("\\| ");
            String[] signal = t[0].split("\\s");
            String[] value = t[1].split("\\s");
            //System.out.println(u[3]);
            for (String display : signal) {
                int size = display.length();
                if (size == 2) {
                    s1 = toCharSet(display);
                } else if (size == 4) {
                    s4 = toCharSet(display);
                } else if (size == 3) {
                    s7 = toCharSet(display);
                } else if (size == 7) {
                    s8 = toCharSet(display);
                } else if (size == 5) {
                    easy.add(toCharSet(display));
                } else if (size == 6) {
                    hard.add(toCharSet(display));
                }              
            }
            // find s3. note that s7 is subset of s3 but not of s2 or s5
            for (Set<Character> set : easy) {
                if (isSubset(s7, set)) {
                    s3 = set;
                }
            }
            easy.remove(s3);
            if (easy.size() != 2) System.out.println("BBBOOOOOOOOOOOOOOOOOOOOO!");
            // now the symmetric diff can be used to find s2 and s5
            for (Set<Character> set : easy) {
                if (symetricDiffSize(set, s4) == 5) s2 = set;
                else                                s5 = set;
            }
            // now the same for s6 and s9
            for (Set<Character> set : hard) {
                if (symetricDiffSize(set, s1) == 6) s6 = set;
                if (symetricDiffSize(set, s3) == 1) s9 = set;
            }
            hard.remove(s6);
            hard.remove(s9);
            if (hard.size() != 1) System.out.println("MMMOOOOOOOOOOOOOOOOOOOOO!");
            for (Set<Character> set : hard) s0 = set;
            // now we decode the display
            Set<Character> v1 = toCharSet(value[0]);
            Set<Character> v2 = toCharSet(value[1]);
            Set<Character> v3 = toCharSet(value[2]);
            Set<Character> v4 = toCharSet(value[3]);
            
            // ugly as hell but work
            // 1000 display
            if (v1.equals(s1)) answer += 1 * 1000;
            if (v1.equals(s2)) answer += 2 * 1000;
            if (v1.equals(s3)) answer += 3 * 1000;
            if (v1.equals(s4)) answer += 4 * 1000;
            if (v1.equals(s5)) answer += 5 * 1000;
            if (v1.equals(s6)) answer += 6 * 1000;
            if (v1.equals(s7)) answer += 7 * 1000;
            if (v1.equals(s8)) answer += 8 * 1000;
            if (v1.equals(s9)) answer += 9 * 1000;
            
            // 100 display
            if (v2.equals(s1)) answer += 1 * 100;
            if (v2.equals(s2)) answer += 2 * 100;
            if (v2.equals(s3)) answer += 3 * 100;
            if (v2.equals(s4)) answer += 4 * 100;
            if (v2.equals(s5)) answer += 5 * 100;
            if (v2.equals(s6)) answer += 6 * 100;
            if (v2.equals(s7)) answer += 7 * 100;
            if (v2.equals(s8)) answer += 8 * 100;
            if (v2.equals(s9)) answer += 9 * 100;
            
            // 10 display
            if (v3.equals(s1)) answer += 1 * 10;
            if (v3.equals(s2)) answer += 2 * 10;
            if (v3.equals(s3)) answer += 3 * 10;
            if (v3.equals(s4)) answer += 4 * 10;
            if (v3.equals(s5)) answer += 5 * 10;
            if (v3.equals(s6)) answer += 6 * 10;
            if (v3.equals(s7)) answer += 7 * 10;
            if (v3.equals(s8)) answer += 8 * 10;
            if (v3.equals(s9)) answer += 9 * 10;
           
            // 1 display
            if (v4.equals(s1)) answer += 1 * 1;
            if (v4.equals(s2)) answer += 2 * 1;
            if (v4.equals(s3)) answer += 3 * 1;
            if (v4.equals(s4)) answer += 4 * 1;
            if (v4.equals(s5)) answer += 5 * 1;
            if (v4.equals(s6)) answer += 6 * 1;
            if (v4.equals(s7)) answer += 7 * 1;
            if (v4.equals(s8)) answer += 8 * 1;
            if (v4.equals(s9)) answer += 9 * 1;
            
        }
        System.out.println(answer);
    }
}