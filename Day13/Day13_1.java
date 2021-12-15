import java.util.*;
public class Day13_1 {


    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "("+x+ "," + y+")";
        }

        public boolean equals(Object that) {
            if ((that == null) || !(that instanceof Point)) {
                return false;
            }
            if (x == ((Point) that).x && y == ((Point) that).y) return true;
            else                            return false;
        }
        @Override
        public int hashCode() {
            int hash = 11;
            hash = 31 * hash + x;
            hash = 31 * hash + y;
            return hash; 
        }
    }

    public static HashSet<Point> xFold(HashSet<Point> points, int xFold) {
        HashSet<Point> newPoints = new HashSet<Point>();
        for (Point p : points) {
            int newX = p.x <= xFold ? p.x : xFold - (p.x - xFold);
            Point q = new Point(newX, p.y);
            newPoints.add(q);
        }
        return newPoints;
    }



    public static HashSet<Point> yFold(HashSet<Point> points, int yFold) {
        HashSet<Point> newPoints = new HashSet<Point>();
        for (Point p : points) {
            int newY = p.y <= yFold ? p.y : yFold - (p.y - yFold);
            Point q = new Point(p.x, newY);
            newPoints.add(q);
        }
        return newPoints;
    }
    
    public static void printPoints(HashSet<Point> set, int xMax, int yMax) {
        int X = 0;
        int Y = 0;
        for (Point p : set) {
            X = X < p.x ? p.x : X;
            Y = Y < p.y ? p.y : Y;
        }

        char[][] dots = new char[Y+1][X+1];
        for (int i = 0; i <= Y; i++)
            for (int j = 0; j <= X; j++)
                dots[i][j] = '.';
        for (Point p : set) {
            //System.out.println(p);
            dots[p.y][p.x] = '#';
        }
        for (int i = 0; i <= Y; i++) {
            for (int j = 0; j <= X; j++) {
                System.out.print(dots[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Point> points = new HashSet<>();
        String[] line;
        line = sc.nextLine().split(",");
        int yMax = 0;
        int xMax = 0;
        do {
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            xMax = xMax < x ? x : xMax;
            yMax = yMax < y ? y : yMax;
            points.add(new Point(x,y));
//            System.out.println(line[0]);
            line = sc.nextLine().split(",");
        } while (!line[0].equals("$")); 
        //printPoints(points, xMax, yMax);
        while (sc.hasNextLine()) {
            line = sc.nextLine().split(" ");
            String[] vals = line[2].split("=");
            if (vals[0].equals("y")) {
                points = yFold(points, Integer.parseInt(vals[1]));
            } else {
                points = xFold(points, Integer.parseInt(vals[1]));
            }
        }
        System.out.println(points.size());
        printPoints(points, xMax, yMax);
    }
}

