import java.util.*;
public class Day15_2 {
   
    static class Edge implements Comparable<Edge> {
        int xi, yi, xf, yf;
        long  w;
        public Edge(int yi, int xi, int yf, int xf, long w) {
            this.xi = xi;
            this.yi = yi;
            this.xf = xf;
            this.yf = yf;
            this.w = w;
        }

        public int compareTo(Edge that) {
            return (int) (this.w - that.w);
        }
        public boolean equals(Object that) {
            if ((that == null) || !(that instanceof Edge)) {
                return false;
            }
            if (xi == ((Edge) that).xi&&xf == ((Edge) that).xf&&yi == ((Edge) that).yi&&yf == ((Edge) that).yf) 
                return true;
            else                        return false;
        }
        public String toString() {
            return "("+xi+","+yi+")"+"("+xf+","+yf+")"+" "+w;
        }
    }

    public static boolean relaxEdge(int[][] cave, long[][] dist, 
                                    int xi, int yi, int xf, int yf) {
        if (dist[yi][xi] + cave[yf][xf] < dist[yf][xf]) {
            dist[yf][xf] = dist[yi][xi] + cave[yf][xf];
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int size = line.length();
        int[][] cave = new int[5*size][5*size];
        //int[][] cave = new int[size][size];
        for (int i = 0; i < size; i++) {
            int val = Integer.parseInt(line.substring(i,i+1));
            cave[0][i] = val;
            cave[0][1*size + i] = val+1 == 10 ? 1: val+1;
            cave[0][2*size + i] = val+2 > 9 ? (val+2)%10 + 1 : val+2;
            cave[0][3*size + i] = val+3 > 9 ? (val+3)%10 + 1: val+3;
            cave[0][4*size + i] = val+4 > 9 ? (val+4)%10 +1: val+4;
            
            cave[1*size][i]          = val+1 > 9 ? (val+1)%10 + 1 : val+1;
            cave[1*size][1*size + i] = val+2 > 9 ? (val+2)%10 + 1 : val+2;
            cave[1*size][2*size + i] = val+3 > 9 ? (val+3)%10 + 1 : val+3;
            cave[1*size][3*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
            cave[1*size][4*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
            
            cave[2*size][i]          = val+2 > 9 ? (val+2)%10 + 1 : val+2;
            cave[2*size][1*size + i] = val+3 > 9 ? (val+3)%10 + 1 : val+3;
            cave[2*size][2*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
            cave[2*size][3*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
            cave[2*size][4*size + i] = val+6 > 9 ? (val+6)%10 + 1 : val+6;

            cave[3*size][i]          = val+3 > 9 ? (val+3)%10 + 1 : val+3;
            cave[3*size][1*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
            cave[3*size][2*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
            cave[3*size][3*size + i] = val+6 > 9 ? (val+6)%10 + 1 : val+6;
            cave[3*size][4*size + i] = val+7 > 9 ? (val+7)%10 + 1 : val+7;

            cave[4*size][i]          = val+4 > 9 ? (val+4)%10 + 1 : val+4;
            cave[4*size][1*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
            cave[4*size][2*size + i] = val+6 > 9 ? (val+6)%10 + 1 : val+6;
            cave[4*size][3*size + i] = val+7 > 9 ? (val+7)%10 + 1 : val+7;
            cave[4*size][4*size + i] = val+8 > 9 ? (val+8)%10 + 1 : val+8;

        }
        //System.out.println(Arrays.toString(cave[0]));
        for (int j = 1; j < size; j++) {
            line = sc.nextLine();
//            System.out.println(line);
            for (int i = 0; i < size; i++) {
                int val = Integer.parseInt(line.substring(i,i+1));
                cave[j][i]          = val;
                cave[j][1*size + i] = val+1 > 9 ? (val+1)%10 + 1 : val+1;
                cave[j][2*size + i] = val+2 > 9 ? (val+2)%10 + 1 : val+2;
                cave[j][3*size + i] = val+3 > 9 ? (val+3)%10 + 1 : val+3;
                cave[j][4*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
                
                cave[1*size+j][i]          = val+1 > 9 ? (val+1)%10 + 1 : val+1;
                cave[1*size+j][1*size + i] = val+2 > 9 ? (val+2)%10 + 1 : val+2;
                cave[1*size+j][2*size + i] = val+3 > 9 ? (val+3)%10 + 1 : val+3;
                cave[1*size+j][3*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
                cave[1*size+j][4*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
                
                cave[2*size+j][i]          = val+2 > 9 ? (val+2)%10 + 1 : val+2;
                cave[2*size+j][1*size + i] = val+3 > 9 ? (val+3)%10 + 1 : val+3;
                cave[2*size+j][2*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
                cave[2*size+j][3*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
                cave[2*size+j][4*size + i] = val+6 > 9 ? (val+6)%10 + 1 : val+6;

                cave[3*size+j][i]          = val+3 > 9 ? (val+3)%10 + 1 : val+3;
                cave[3*size+j][1*size + i] = val+4 > 9 ? (val+4)%10 + 1 : val+4;
                cave[3*size+j][2*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
                cave[3*size+j][3*size + i] = val+6 > 9 ? (val+6)%10 + 1 : val+6;
                cave[3*size+j][4*size + i] = val+7 > 9 ? (val+7)%10 + 1 : val+7;

                cave[4*size+j][i]          = val+4 > 9 ? (val+4)%10 + 1 : val+4;
                cave[4*size+j][1*size + i] = val+5 > 9 ? (val+5)%10 + 1 : val+5;
                cave[4*size+j][2*size + i] = val+6 > 9 ? (val+6)%10 + 1 : val+6;
                cave[4*size+j][3*size + i] = val+7 > 9 ? (val+7)%10 + 1 : val+7;
                cave[4*size+j][4*size + i] = val+8 > 9 ? (val+8)%10 + 1 : val+8;
            }
        }
       /* for (int i = 0; i < cave.length;i++) {
            System.out.println(i);
            for(int j = 0; j < cave.length; j++) {
                System.out.print(cave[i][j]);
            }
            System.out.println();
        }*/
//        for (int j = 0; j < cave.length; j++) System.out.println(Arrays.toString(cave[j]));
        // now we start the Dijkstra algorithm
        long[][] dist = new long[5*size][5*size];
        boolean[][] visited = new boolean[5*size][5*size];
        for (int i = 0; i < 5*size; i++)
            for (int j = 0; j < 5*size; j++) {
                dist[j][i] = 2000*5*size;
                visited[j][i] = false;
            }
        dist[0][0] = 0;
        visited[0][0] = true; 
        PriorityQueue<Edge> hook = new PriorityQueue<>(200*5*size*5*size);
        Edge t = new Edge(0,0,0,0,0);
        relaxEdge(cave, dist, 0, 0, 1, 0);
        relaxEdge(cave, dist, 0, 0, 0, 1);
        hook.add(new Edge(0,0,0,1,(long)cave[0][1]));
        hook.add(new Edge(0,0,1,0,(long)cave[1][0]));

        do {
            //System.out.println(hook.size());
            t = hook.poll();
            visited[t.yf][t.xf] = true;
            //dist[t.yf][t.xf] = dist[t.yi][t.xi] + cave[t.yf][t.xf];
            //System.out.println(t+"     "+dist[t.yf][t.xf]);
            //up
            if (t.yf > 0 && !visited[t.yf-1][t.xf]) { 
                relaxEdge(cave, dist, t.xf, t.yf, t.xf, t.yf-1);
                hook.remove(new Edge(t.yf,t.xf, t.yf-1, t.xf,dist[t.yf-1][t.xf]));
                hook.add(new Edge(t.yf,t.xf, t.yf-1, t.xf,dist[t.yf-1][t.xf]));// dist[t.yf][t.xf]+(long)cave[t.yf-1][t.xf]));
            }
            //down
            if (t.yf < 5*size-1 && !visited[t.yf+1][t.xf]) { 
                relaxEdge(cave, dist, t.xf, t.yf, t.xf, t.yf+1);
                hook.remove(new Edge(t.yf,t.xf, t.yf+1, t.xf,dist[t.yf+1][t.xf]));
                hook.add(new Edge(t.yf,t.xf, t.yf+1, t.xf, dist[t.yf+1][t.xf]));//dist[t.yf][t.xf]+(long)cave[t.yf+1][t.xf]));
            }
            //left
            if (t.xf > 0 && !visited[t.yf][t.xf-1]) { 
                relaxEdge(cave, dist, t.xf, t.yf, t.xf-1, t.yf);
                hook.remove(new Edge(t.yf,t.xf, t.yf, t.xf-1,dist[t.yf][t.xf-1]));
                hook.add(new Edge(t.yf,t.xf, t.yf, t.xf-1, dist[t.yf][t.xf-1]));//dist[t.yf][t.xf]+(long)cave[t.yf][t.xf-1]));
            }
            //right
            if (t.xf< 5*size - 1 && !visited[t.yf][t.xf+1]) { 
                relaxEdge(cave, dist, t.xf, t.yf, t.xf+1, t.yf);
                hook.remove(new Edge(t.yf,t.xf, t.yf, t.xf+1,dist[t.yf][t.xf+1]));
                hook.add(new Edge(t.yf,t.xf, t.yf, t.xf+1, dist[t.yf][t.xf+1]));//dist[t.yf][t.xf]+(long)cave[t.yf][t.xf+1]));
            }
        } while (!visited[5*size-1][5*size-1]);
/*
        for (int i = 0; i < size*size*2; i++)
            for (int j = 0; j < size-1; j++)
                for (int k = 0; k < size-1; k++) {
                    relaxEdge(cave, dist, j, k, j, k+1);
                    relaxEdge(cave, dist, j, k, j+1, k);
                    relaxEdge(cave, dist, j+1, k, j+1, k+1);
                    relaxEdge(cave, dist, j+1, k, j, k);                   
                    relaxEdge(cave, dist, j, k+1, j+1, k+1);
                    relaxEdge(cave, dist, j, k+1, j, k);
                    relaxEdge(cave, dist, j+1, k+1, j, k+1);
                    relaxEdge(cave, dist, j+1, k+1, j+1, k);
                }*/
        System.out.println(dist[5*size-1][5*size-1]+ " "+cave[5*size-1][5*size-1]);
    }
}
