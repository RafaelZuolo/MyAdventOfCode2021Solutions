import java.util.Scanner;
import java.util.ArrayList;
public class Day8_1 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        int answer = 0;
        for (String s : lines) {
            String[] t = s.split("\\| ");
            //System.out.println(t[1]);
            String[] u = t[1].split("\\s");
            //System.out.println(u[3]);
            for (String display : u) {
                int size = display.length();
                if (size == 2||size==4||size==7||size==3) answer++;
            }
        }
        System.out.println(answer);
    }
}