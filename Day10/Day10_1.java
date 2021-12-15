import java.util.*;
public class Day10_1 {
    /*
        ): 3 points.
        ]: 57 points.
        }: 1197 points.
        >: 25137 points.
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //List<String> lines = new ArrayList<>();
        int points = 0;
        Deque<Character> parser;
        while (sc.hasNextLine()) {
            //lines.add(sc.nextLine());
            String line = sc.nextLine();
            parser = new ArrayDeque<>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c=='(' || c=='[' || c=='{' || c=='<') 
                    parser.push(c);
                else {
                    char d = parser.pop().charValue();
                    if (c==')' && d!='(') points += 3;
                    if (c==']' && d!='[') points += 57;
                    if (c=='}' && d!='{') points += 1197;
                    if (c=='>' && d!='<') points += 25137;
                }
            }
        }
        System.out.println(points);
    }
}