import java.util.*;
public class Day16_1{
    

    static final int literalValue = 4;
    static final int aaa = 0;


    public static String hexToBin(String h) {
        String b = Integer.toBinaryString(Integer.parseInt(h, 16));
        String pading = "";
        while (pading.length() + b.length() < 4) {
            pading = pading.concat("0");
        }
        return pading.concat(b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String rawBinary = "";
        for (int i = 0; i < line.length(); i++) {
           rawBinary = rawBinary.concat(hexToBin(line.substring(i,i+1)));
        }
        System.out.println(rawBinary);
        System.out.println("110100101111111000101000");
        System.out.println(rawBinary.equals("110100101111111000101000"));
        
        int pos = 0;
        int version = Integer.parseInt(rawBinary.substring(pos,pos+3), 2);
        pos += 3;
        int typeID  = Integer.parseInt(rawBinary.substring(pos,pos+3), 2);
        pos += 3;
        System.out.println(version);
        System.out.println(typeID);

        if (typeID == literalValue) {
            String val = "";
            while(rawBinary.charAt(pos) == '1') {
                pos +=1;
                val = val.concat(rawBinary.substring(pos, pos+4));
                System.out.println(val);
                pos +=4;
            }
            pos +=1;
            val = val.concat(rawBinary.substring(pos, pos+4));
            System.out.println(val);
            pos +=4;

            System.out.println(Integer.parseInt(val,2));
        }
    }
}
