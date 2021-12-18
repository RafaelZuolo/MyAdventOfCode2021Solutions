import java.util.*;
public class Day18_1 {

    static class SnailNumber {
        long a = -1;
        long b = -1;
        SnailNumber sa = null;
        SnailNumber sb = null;
        int nestNum = 0;
        
        public SnailNumber() {}

        public SnailNumber(long a, long b) {
            this.a = a;
            this.b = b;
        }
        public SnailNumber(SnailNumber sa, SnailNumber sb) {
            this.sa = sa;
            this.sb = sb;
            sa.increaseNest();
            sa.increaseNest();
        }
        public SnailNumber(SnailNumber sa, long b) {
            this.sa = sa;
            this.b = b;
            sa.increaseNest();
        }
        public SnailNumber(long a, SnailNumber sb) {
            this.a = a;
            this.sb = sb;
            sb.increaseNest();
        }
        public void increaseNest() {
            nestNum++;
            if (sa != null) sa.increaseNest();
            if (sb != null) sb.increaseNest();
        }
        public void decreaseNest() {
            nestNum--;
            if (sa != null) sa.decreaseNest();
            if (sb != null) sb.decreaseNest();
        }

        public String toString() {// inOrderTraversal
            String s = "[";
            if (sa == null) s = s.concat(a+"");
            else s = s.concat(sa.toString());
            s = s.concat(",");
            if (sb== null) s = s.concat(b+"");
            else s = s.concat(sb.toString());
            s = s.concat("]");
            return s;
        }
/*        public void reduce() {
            if (sa != null) {  
                sa.reduce();
            } else if (sb != null) {
                sb.reduce();
            } else if (nestNum == 4) { // explode
                
            } else if (a >= 10) {
            
            } else if (b >= 10) {
            
            }
            
        }
        */
    }
    
    static SnailNumber parseSnail(String s) {
        SnailNumber tempR = null;
        SnailNumber tempL = null;
        SnailNumber _return = new SnailNumber();
        int commaIndex = 2;
        if (s.charAt(1) != '[') _return.a = Integer.parseInt(s.substring(1,2));
        else {
            for (int i=1; i != 0; commaIndex++) {
                if (s.charAt(commaIndex)=='[') i++;
                if (s.charAt(commaIndex)==']') i--;
            }
            _return.sa = parseSnail(s.substring(1, commaIndex));
        }
        assert (s.charAt(commaIndex)==',');
        if (s.charAt(commaIndex +1) != '[') _return.b = Integer.parseInt(s.substring(commaIndex+1, commaIndex+2));
        else {
            _return.sb = parseSnail(s.substring(commaIndex+1, s.length()));
        }
        return _return;
    }
    static SnailNumber add(SnailNumber sa, SnailNumber sb) {
        SnailNumber sum = new SnailNumber(sa, sb);
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.nextLine();
        sc.nextLine();
        sc.nextLine();
        sc.nextLine();
        String s = sc.nextLine();
        System.out.println(s);
        System.out.println(parseSnail(s));
    }
}
