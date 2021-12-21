import java.util.*;
public class Day18_2 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Day18_1.Snail> numbers = new ArrayList<>();
        while (sc.hasNextLine()) {
            numbers.add(new Day18_1.Snail(sc.nextLine()));
        }
        long maxNorm = 0;
        for (int i = 0; i <  numbers.size(); i++) {
            for (int j = i+1; j < numbers.size(); j++) {
                Day18_1.Snail sum = Day18_1.add(numbers.get(i), numbers.get(j));
                long norm = sum.norm();
                maxNorm = norm > maxNorm ? norm : maxNorm;
                sum = Day18_1.add(numbers.get(j), numbers.get(i));
                norm = sum.norm();
                maxNorm = Math.max(maxNorm, norm);
            }
        }
        System.out.println(maxNorm);
    }
}
