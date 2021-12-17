import java.util.*;
public class Day16_1{
    
    static int pos = 0;
    static String rawBinary = "";
    static final long literalValue = 4;

    public static long parseSubPack(int subPackNum) {
        long versionSum = 0;
        for (int i = 0; i < subPackNum; i++) {
            versionSum += parsePack();
        }
        return versionSum;
    }

    public static long parseLengthPack(int totalLength) {
        long versionSum = 0;
        int currentPos = pos;
        while (pos < currentPos + totalLength) {
            versionSum += parsePack();
        }
        return versionSum;
    }

    public static long parsePack() {
        int version = Integer.parseInt(rawBinary.substring(pos,pos+3), 2);
        pos += 3;
        int typeID  = Integer.parseInt(rawBinary.substring(pos,pos+3), 2);
        pos += 3;
 
        System.out.println("version "+version);
        System.out.println("ID "+typeID);

        if (typeID == literalValue) {
            String val = "";
            while(rawBinary.charAt(pos) == '1') {
                pos +=1;
                val = val.concat(rawBinary.substring(pos, pos+4));
                //System.out.println(val);
                pos +=4;
            }
            pos +=1;
            val = val.concat(rawBinary.substring(pos, pos+4));
            pos +=4;
            System.out.println("Parsed literal of val "
                                +Long.parseLong(val,2));
            return version;
//            return Long.parseLong(val, 2);
        }
        else {
            if (rawBinary.charAt(pos) == '1') {
                pos++;
                int subPackNum 
                    = Integer.parseInt(rawBinary.substring(pos, pos+11), 2);
                pos += 11;
                System.out.println("Parsing "+subPackNum+" subpacks");
                version += parseSubPack(subPackNum);// get the subpack sum
                System.out.println("finished "+subPackNum+" subpacks");
            } else {
                pos++;
                int totalLength 
                    = Integer.parseInt(rawBinary.substring(pos, pos+15), 2);
                pos += 15;
                System.out.println("Parsing length "+totalLength);
                version += parseLengthPack(totalLength); // get the subpack sum
                System.out.println("finished length "+totalLength);
            }
        }
        return version;
    }

    public static String hexToBin(String h) {
        String b = Long.toBinaryString(Long.parseLong(h, 16));
        String pading = "";
        while (pading.length() + b.length() < 4) {
            pading = pading.concat("0");
        }
        return pading.concat(b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        rawBinary = "";
        for (int i = 0; i < line.length(); i++) {
           rawBinary = rawBinary.concat(hexToBin(line.substring(i,i+1)));
        }
        System.out.println(rawBinary);
        System.out.println("00111000000000000110111101000101001010010001001000000000");
        //System.out.println(rawBinary.equals("110100101111111000101000"));
        System.out.println("sum = " + parsePack());
    }
}
