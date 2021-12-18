public class Day18_1 {

    static class SnailNumber {
        long a = -1;
        long b = -1;
        SnailNumber sa = null;
        SnailNumber sb = null;
        int nestNum = 0;
        
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
        public increaseNest() {
            nestNum++;
            if (sa != null) sa.increaseNest();
            if (sb != null) sb.increaseNest();
        }
        public void reduce() {
            if (nestNum == 4) { // explode
                
            } else if (a >= 10) {
            
            } else if (b >= 10) {
            
            }
            
        }
    }

    static SnailNumber add(SnailNumber sa, SnailNumber sb) {
        SnailNumber sum = new SnailNumber(sa, sb);
        return sum.reduce();
    }
}
