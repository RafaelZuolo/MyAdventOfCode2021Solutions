import java.util.*;
public class Day10_2 {
    /*
        Start with a total _score of 0. Then, for each character, 
        multiply the total _score by 5 and then increase the total 
        _score by the _point value given for the character in the 
        following table:

        ): 1 point.
        ]: 2 points.
        }: 3 points.
        >: 4 points.

    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //List<String> lines = new ArrayList<>();
        long points = 0;
        Deque<Character> parser;
        List<Long> scores = new ArrayList<>();
        while (sc.hasNextLine()) {
            //lines.add(sc.nextLine());
            String line = sc.nextLine();
            parser = new ArrayDeque<>();
            for (int i = 0; i < line.length(); i++) {
                points = 0;
                char c = line.charAt(i);
                if (c=='(' || c=='[' || c=='{' || c=='<') 
                    parser.push(c);
                else {
                    char d = parser.pop().charValue();
                    if (c==')' && d!='(') {parser = new ArrayDeque<>(); break;}
                    if (c==']' && d!='[') {parser = new ArrayDeque<>(); break;}
                    if (c=='}' && d!='{') {parser = new ArrayDeque<>(); break;}
                    if (c=='>' && d!='<') {parser = new ArrayDeque<>(); break;}
                }
            }
            while (parser.size() > 0) {
                char c = parser.pop().charValue();
                if (c=='(') {points = 5*points + 1;}
                if (c=='[') {points = 5*points + 2;}
                if (c=='{') {points = 5*points + 3;}
                if (c=='<') {points = 5*points + 4;}
            }
            if (points != 0) scores.add(Long.valueOf(points));
            
        }
        scores.sort(null);
        for (Long c : scores) System.out.println(c);
        System.out.println("size " + scores.size());
        System.out.println(scores.get(scores.size()/2));
    }
}