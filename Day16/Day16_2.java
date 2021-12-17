import java.util.*;
public class Day16_2{
    
    static int pos = 0;
    static String rawBinary = "";
    static final long literalValue = 4;

    public static long parseSubPack(int subPackNum, int typeID) {
        long versionSum = 0;
        if (typeID == 0) versionSum = 0;
        if (typeID == 1) versionSum = 1;
        if (typeID == 2) versionSum = Integer.MAX_VALUE;
        if (typeID == 3) versionSum = -1*Integer.MAX_VALUE;
        if (typeID == 5) {//greater than
            versionSum = parsePack() >  parsePack() ? 1 : 0;
            return versionSum;
        }
        if (typeID == 6) {// less than
            versionSum = parsePack() <  parsePack() ? 1 : 0;
            return versionSum;
        }
        if (typeID == 7) {// equal to
            versionSum = parsePack() == parsePack() ? 1 : 0;
            return versionSum;
        }
        for (int i = 0; i < subPackNum; i++) {
            if (typeID == 0) versionSum += parsePack();
            if (typeID == 1) versionSum *= parsePack();
            if (typeID == 2) {// minimum
                long val = parsePack();
                versionSum = versionSum > val ? val : versionSum;
            }
            if (typeID == 3) {// maximum
                long val = parsePack();
                versionSum = versionSum < val ? val : versionSum;
            }
        }
        return versionSum;
    }

    public static long parseLengthPack(int totalLength, int typeID) {
        long versionSum = 0;
        int currentPos = pos;
        if (typeID == 0) versionSum = 0;
        if (typeID == 1) versionSum = 1;
        if (typeID == 2) versionSum = Long.MAX_VALUE;
        if (typeID == 3) versionSum = 0;
        if (typeID == 5) {//greater than
            versionSum = parsePack() >  parsePack() ? 1 : 0;
            return versionSum;
        }
        if (typeID == 6) {// less than
            versionSum = parsePack() <  parsePack() ? 1 : 0;
            return versionSum;
        }
        if (typeID == 7) {// equal to
            versionSum = parsePack() == parsePack() ? 1 : 0;
            return versionSum;
        }
        while (pos < currentPos + totalLength) {
            if (typeID == 0) versionSum += parsePack();
            if (typeID == 1) versionSum *= parsePack();
            if (typeID == 2) {// minimum
                long val = parsePack();
                versionSum = versionSum > val ? val : versionSum;
            }
            if (typeID == 3) {// maximum
                long val = parsePack();
                versionSum = versionSum < val ? val : versionSum;
            }
        }
        return versionSum;
    }

    public static long parsePack() {
        int version = Integer.parseInt(rawBinary.substring(pos,pos+3), 2);
        pos += 3;
        int typeID  = Integer.parseInt(rawBinary.substring(pos,pos+3), 2);
        pos += 3;
        long _return = 0; 
        //System.out.println("version "+version);
        //System.out.println("ID "+typeID);

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
            //System.out.println("Parsed literal of val "
            //                    +Long.parseUnsignedLong(val,2));
//            return version;
            return Long.parseUnsignedLong(val, 2);
        }
        else {
            if (rawBinary.charAt(pos) == '1') {
                pos++;
                int subPackNum 
                    = Integer.parseInt(rawBinary.substring(pos, pos+11), 2);
                pos += 11;
                System.out.println("Parsing "+subPackNum+" subpacks");
                _return += parseSubPack(subPackNum, typeID);// get the subpack sum
                System.out.println("finished "+subPackNum+" subpacks");
            } else {
                pos++;
                int totalLength 
                    = Integer.parseInt(rawBinary.substring(pos, pos+15), 2);
                pos += 15;
                System.out.println("Parsing length "+totalLength);
                _return += parseLengthPack(totalLength, typeID); // get the subpack sum
                System.out.println("finished length "+totalLength);
            }
        }
        return _return;
    }

    public static String hexToBin(String h) {
        String b = Long.toBinaryString(Long.parseUnsignedLong(h, 16));
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
//        System.out.println(rawBinary);
//        System.out.println("00111000000000000110111101000101001010010001001000000000");
        //System.out.println(rawBinary.equals("110100101111111000101000"));
        System.out.println("evaluation = " + parsePack());
    }
}
