public class Day5_1 {
    //public static int[][] board = new int[1000][1000];

    public static int getGCD(int a, int b) {
        if (b == 0) return a;
        else return getGCD(b, a%b);
    }
    
    public static class Line {
        int xi;
        int yi;
        int xf;
        int yf;
        int gcd = 0;
        int deltaX;
        int deltaY;
        int signal = 1;

        public Line(String s) {
            String[] temps = s.split(",| -> ");
            xi = Integer.parseInt(temps[0]);
            yi = Integer.parseInt(temps[1]);
            xf = Integer.parseInt(temps[2]);
            yf = Integer.parseInt(temps[3]);
            if (xi > xf) {
                int temp = xi;
                xi = xf;
                xf = temp;
                temp = yi;
                yi = yf;
                yf = temp;
            }
            deltaX = xf - xi;
            deltaY = yf - yi;
            if (deltaY < 0) signal = -1;
        }

        public void populate(int[][] b) {
            if (deltaX == 0) {
                for (int i = yi; i != yf; i += signal) {
                    b[xi][i]++;
                }
                b[xf][yf]++;
            } else if (deltaY == 0) {
                for (int i = xi;  i <= xf; i++) {
                    b[i][yi]++;
                }
            } else {
                gcd = getGCD(deltaX, Math.abs(deltaY));
                int xStep = deltaX/gcd;
                int yStep = deltaY/gcd;
                int i = xi; 
                int j = yi;
                while (i != xf && j != yf) {
                    b[i][j] ++;
                    i += xStep;
                    j += yStep;
                } 
                b[xf][yf]++;
            }
        }
        public String toString() {
            return xi +","+yi+"-"+xf+","+yf+"  "+gcd+" "+deltaX+","+deltaY;
        }
    }
    
    public static void printBoard(int[][] b) {
        for (int i =0; i<b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (b[i][j]==0) System.out.print(" .");
                else            System.out.print(" "+b[i][j]);
            }
            System.out.println();
        }
    }

    public static int countBoard(int[][] b) {
        int points = 0;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j] > 1) points++;
            }
        }
        return points;
    }

    public static void main(String[] args) {
        int[][] board = new int[1000][1000];
//        int[][] board = new int[10][10];
        while (!StdIn.isEmpty()) {
            Line l = new Line(StdIn.readLine());
            l.populate(board);
//            System.out.println(l);
        }
//        printBoard(board);
        System.out.println(countBoard(board));
    }
}
