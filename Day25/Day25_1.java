import java.util.*;
import java.io.*;
public class Day25_1 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] sea;
        boolean printPlz = (args.length > 0);
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine())
            lines.add(sc.nextLine());
        sea = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                sea[i][j] = line.charAt(j);
            }
        }
        
        if (printPlz)
            for (int i = 0; i < sea.length; i++)
                System.out.println(Arrays.toString(sea[i]));
        
        char[][] nextSea = new char[sea.length][sea[0].length];
        int step = 1;
        boolean hasChanged = true;
        

        System.out.println("\n----------------------------------------\n");
        // new strategy
        while(hasChanged) {
            hasChanged = false;
            // east herd first
            for (int i = 0; i < sea.length; i++) {
                for (int j = 0; j < sea[0].length; j++) {
                    if (sea[i][j] == '>') {
                        if (sea[i][(j+1)%sea[0].length] == '.') {
                            hasChanged = true;
                            sea[i][(j+1)%sea[0].length] = 'l'; // sentinel symbol for cucumber
                            sea[i][j] = '$'; // sentinel symbol for space cucumber occupied
                        }
                    } 
                }
                for (int j = 0; j < sea[0].length; j++) {
                    if (sea[i][j] == '$') sea[i][j] = '.';
                    if (sea[i][j] == 'l') sea[i][j] = '>';
                }
            }
            // south heard next
            for (int j = 0; j < sea[0].length; j++) {
                for (int i = 0; i < sea.length; i++) {
                    if (sea[i][j] == 'v') {
                        if (sea[(i+1)%sea.length][j] == '.') {
                            hasChanged = true;
                            sea[(i+1)%sea.length][j] = 'd';
                            sea[i][j] = '$'; // sentinel symbol
                        }
                    } 
                }
                for (int i = 0; i < sea.length; i++) {
                    if (sea[i][j] == '$') sea[i][j] = '.';
                    if (sea[i][j] == 'd') sea[i][j] = 'v';
                }
            }
            if (hasChanged) step++;
        }
        if (printPlz)
            for (int i = 0; i < sea.length; i++)
                System.out.println(Arrays.toString(sea[i]));
        
        System.out.println("\n\n" + step);
    }
}