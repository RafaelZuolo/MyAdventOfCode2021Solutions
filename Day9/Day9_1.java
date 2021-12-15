import java.util.ArrayList;
import java.util.Scanner;
public class Day9_1 {
    
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
        int risk = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int cur = map[i][j];
                int up = 99;
                int down = 99;
                int left = 99;
                int right = 99;
                if (i > 0)      up = map[i-1][j];
                if (i < x-1)    down = map[i+1][j];
                if (j > 0)      left = map[i][j-1];
                if (j < y-1)    right = map[i][j+1];
                if (cur < up && cur < down && cur < left && cur < right) 
                    risk += cur+1;
            }
        }
        System.out.println(risk);
    }
}