import java.util.*;
public class Day22_1 {
    
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            boolean[][][] cubes = new boolean[101][101][101];
            assert (cubes[0][0][0] == false);
            
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\sx=|\\.\\.|\\,y=|\\,z=");
                //System.out.println(Arrays.toString(line));
                assert (line.length == 7);
                boolean state = false;
                if (line[0].equals("on")) state = true;
                int xIni = Integer.parseInt(line[1]);
                int xEnd = Integer.parseInt(line[2]);
                int yIni = Integer.parseInt(line[3]);
                int yEnd = Integer.parseInt(line[4]);
                int zIni = Integer.parseInt(line[5]);
                int zEnd = Integer.parseInt(line[6]);
                
                for (int x = Math.max(xIni, -51); x <= Math.min(xEnd, 51); x++) {
                    for (int y = Math.max(yIni, -51); y <= Math.min(yEnd, 51); y++) {
                        for (int z = Math.max(zIni, -51); z <= Math.min(zEnd, 51); z++) {
                            if (x+50<0|y+50<0|z+50<0|x+50>100|y+50>100|z+50>100)
                                continue;
                            cubes[x+50][y+50][z+50] = state;
                        }
                    }
                }
            }    
            int numOn = 0;
            for (int x = 0; x < 101; x++) {
                for (int y = 0; y < 101; y++) {
                    for (int z = 0; z < 101; z++) {
                        if (cubes[x][y][z] == true) numOn++;
                    }
                }
            }
            System.out.println(numOn);
        }
        
}