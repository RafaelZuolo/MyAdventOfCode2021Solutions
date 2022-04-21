import java.util.*;
import java.io.*;
public class Day24_1 {
        
        
    // we will build a ALU program interpreter
    /*The ALU is a four-dimensional processing unit: 
    it has integer variables w, x, y, and z. These variables all start 
    with the value 0. The ALU also supports six instructions:

    inp a - Read an input value and write it to variable a.
    add a b - Add the value of a to the value of b, then store the result in variable a.
    mul a b - Multiply the value of a by the value of b, then store the result in variable a.
    div a b - Divide the value of a by the value of b, truncate the result to an integer, then store the result in variable a. (Here, "truncate" means to round the value toward zero.)
    mod a b - Divide the value of a by the value of b, then store the remainder in variable a. (This is also called the modulo operation.)
    eql a b - If the value of a and b are equal, then store the value 1 in variable a. Otherwise, store the value 0 in variable a.

    In all of these instructions, a and b are placeholders; a will always 
    be the variable where the result of the operation is 
    stored (one of w, x, y, or z), while b can be either a variable or a 
    number. Numbers can be positive or negative, but will always be integers.
    */
    
    
    static Map<String, Integer> var = new HashMap<>();
    static Deque<Integer> deque = new LinkedList<>();
    
    public static int extractValue(String param) {
        // if param is either variable w, x, y, z, returns their value,
        // else it must be an integer, then return the integer value.
        if (param.equals("w")||param.equals("x")||param.equals("y")||param.equals("z")) {
            return var.get(param);
        } else {
            return Integer.parseInt(param);
        }
    }
        
    public static void main(String[] args) {
        File f;
        Scanner model;
        try {
            f = new File(args[0]);
            model = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(e); 
            return;
        }
        Scanner sc = new Scanner(System.in);
        var.put("w", 0);
        var.put("x", 0);
        var.put("y", 0);
        var.put("z", 0); 
        
        List<String> program = new ArrayList<>();
        while (sc.hasNextLine()) {
            program.add(sc.nextLine());
        }
        while (model.hasNextInt()) {
            deque.add(model.nextInt());
        }
        
        for (int i = 0; i < program.size(); i++) {
            String[] line = program.get(i).split("\\s");
            String op = line[0];
            
            if (op.equals("inp")) {
                var.put(line[1], deque.poll());
                for (Map.Entry e : var.entrySet()) {
                    System.out.printf("(%s, %d)", e.getKey(), e.getValue());
                }
                System.out.println();
            } else if (op.equals("add")) {
                var.put(line[1], var.get(line[1]) + extractValue(line[2]));
            } else if (op.equals("mul")) {
                var.put(line[1], var.get(line[1]) * extractValue(line[2]));
            } else if (op.equals("div")) {
                if (line[2].equals("26")) System.out.println("div by 26 here------------------------------------------------");
                var.put(line[1], var.get(line[1]) / extractValue(line[2]));
            } else if (op.equals("mod")) {
                var.put(line[1], var.get(line[1]) % extractValue(line[2]));
            } else if (op.equals("eql")) {
                System.out.print(" before eql " + line[1] + " " + line[2]+ " " + (i+1) + "      ");
                for (Map.Entry e : var.entrySet()) {
                    System.out.printf("(%s, %d)", e.getKey(), e.getValue());
                }
                System.out.println();
                var.put(line[1], var.get(line[1]).intValue() == extractValue(line[2]) ? 1 : 0);
            }
        }
        for (Map.Entry e : var.entrySet()) {
            System.out.printf("(%s, %d)", e.getKey(), e.getValue());
        }
        System.out.println();
    }
}