import java.util.*;
public class Day11_1 {
    
    
    public static void blink(int[][] map, int i, int j) {
        if (map[i][j] < 10) return;
        else {
            map[i][j] = 0;
            if (i > 0) {
                propagateBlink(map, i-1, j);
                if (j > 0) propagateBlink(map, i-1, j-1);
                if (j < map[0].length-1) propagateBlink(map, i-1, j+1);
            }
            if (i < map.length-1) {
                propagateBlink(map, i+1, j);
                if (j > 0) propagateBlink(map, i+1, j-1);
                if (j < map[0].length-1) propagateBlink(map, i+1, j+1);
            }
            if (j > 0) propagateBlink(map, i, j-1);
            if (j < map[0].length-1) propagateBlink(map, i, j+1);
        }
    }
    
    public static void propagateBlink(int[][] map, int i, int j) {
        if (map[i][j] == 0) return;
        map[i][j]++;
        if (map[i][j] < 10) return;
        else {
            map[i][j] = 0;
            if (i > 0) {
                propagateBlink(map, i-1, j);
                if (j > 0) propagateBlink(map, i-1, j-1);
                if (j < map[0].length-1) propagateBlink(map, i-1, j+1);
            }
            if (i < map.length-1) {
                propagateBlink(map, i+1, j);
                if (j > 0) propagateBlink(map, i+1, j-1);
                if (j < map[0].length-1) propagateBlink(map, i+1, j+1);
            }
            if (j > 0) propagateBlink(map, i, j-1);
            if (j < map[0].length-1) propagateBlink(map, i, j+1);
        }
    }
    
    public static int zeroCounter(int[][] map) {
        int zeros = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) zeros++;
            }
        }       
        return zeros;
    }
    
    public static boolean isAllFlashing(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0) return false;
            }
        }       
        return true;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] map = new int[10][10];
        
        for (int i = 0; i < map.length; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = Integer.parseInt(line.substring(j, j+1));
            }       
        }
        int numBlink = 0;
        for (int steps = 0; steps < 1000; steps++) {
            
            // increment
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j]++;
                }
            }
            //blink and propagate
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    blink(map, i,j);
                }
            }
            if (isAllFlashing(map)) {System.out.println("Sync time: " +(steps+1));break;}
            numBlink += zeroCounter(map);
            //print
            /* for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }       
            System.out.println();System.out.println("Step" + steps);
            System.out.println(); */
        }
        System.out.println(numBlink);
        
    }
}