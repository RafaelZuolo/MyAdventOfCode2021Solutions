import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
public class Day9_2 {
    // strategy implement a DFS
    
    // start a dfs at point i j, set it to -1, and start a dfs on the neighbours
    public static int dfs (int[][] map, int i, int j) {
        if ( i < 0 || j < 0 || i >= map.length || j >= map[0].length)
            return 0;
        if (map[i][j] == 9) return 0;
        if (map[i][j] == -1) return 0;
        map[i][j] = -1; // this is a valid unexplored space. Mark it
        return 1 + dfs(map,i-1,j) + dfs(map,i+1,j) + dfs(map,i,j-1) + dfs(map,i,j+1);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) lines.add(sc.nextLine());
        int x = lines.size();
        int y = lines.get(0).length(); 
        int[][] map = new int[x][y];
        for (int i = 0; i < x; i++) {
            String s = lines.get(i);
            for (int j = 0; j < y; j++) {
                map[i][j] = Integer.parseInt(s.substring(j, j+1));
            }
        }
        
        // get the sum of low points risk level
        ArrayList<Integer> basinSizes = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (map[i][j] == -1 || map[i][j] == 9) continue;
                basinSizes.add(Integer.valueOf(dfs(map,i,j)));
            }
        }
        System.out.println("number of basins: " + basinSizes.size());
        basinSizes.sort(null);
        int s1 = basinSizes.get(basinSizes.size()-1);
        int s2 = basinSizes.get(basinSizes.size()-2);
        int s3 = basinSizes.get(basinSizes.size()-3);
        System.out.println(s1*s2*s3);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (map[i][j] == -1) System.out.print(" ");
                else                 System.out.print("+");
            }
            System.out.println();
        }
    }
}