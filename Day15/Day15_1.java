import java.util.*;
public class Day15_1 {
    
    public static void relaxEdge(int[][] cave, int[][] dist, 
                                    int xi, int yi, int xf, int yf) {
        if (dist[yi][xi] + cave[yf][xf] < dist[yf][xf])
            dist[yf][xf] = dist[yi][xi] + cave[yf][xf];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int size = line.length();
        int[][] cave = new int[size][size];
        for (int i = 0; i < size; i++) cave[0][i] = Integer.parseInt(line.substring(i,i+1));
//            System.out.println(Arrays.toString(cave[0]));
        for (int j = 1; j < size; j++) {
            line = sc.nextLine();
            for (int i = 0; i < size; i++) cave[j][i] = Integer.parseInt(line.substring(i,i+1));
//            System.out.println(Arrays.toString(cave[j]));
        }

        // now we start the Dijkstra algorithm
        int[][] dist = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                dist[j][i] = 20*size;
        dist[0][0] = 0;

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
                }
        System.out.println(dist[size-1][size-1]);
    }
}
