import java.util.*;
public class Day3_2 {
    public static void main(String[] args) {
        int curBit = 0;
        int curFreq = 0;
        int curStep = 11;
        Set<Integer> setOxygen = new HashSet<Integer>();
        Set<Integer> tempSetOxygen = new HashSet<Integer>();
        Set<Integer> setCarbon = new HashSet<Integer>();
        Set<Integer> tempSetCarbon = new HashSet<Integer>();
        // iitialized the sets
        while (!StdIn.isEmpty()) {
            int read = Integer.parseInt(StdIn.readString(), 2);
            setOxygen.add(read);
            setCarbon.add(read);
        }
        int oxyRead=0;
        int carRead=0;
        while (setOxygen.size() > 1) {
            // get frequency of bits
            for (Integer reading : setOxygen) {
                if (((reading >> curStep) & 1) == 1) curFreq++;
                else                                curFreq--;
            }
            curBit = (curFreq >= 0) ? 1 : 0;
            // delete entries with wrong bit
            for (Integer reading : setOxygen) {
                if (((reading >> curStep) & 1) == curBit) tempSetOxygen.add(reading);
            }
            setOxygen = tempSetOxygen;
            tempSetOxygen = new HashSet<Integer>();
            curStep--;
            curFreq = 0;
        }
        curStep = 11;
        while (setCarbon.size() > 1) {
            // get frequency of bits
            for (Integer reading : setCarbon) {
                if (((reading >> curStep) & 1) == 1) curFreq++;
                else                                curFreq--;
            }
            curBit = (curFreq >= 0) ? 0 : 1;
            // delete entries with wrong bit
            for (Integer reading : setCarbon) {
                if (((reading >> curStep) & 1) == curBit) tempSetCarbon.add(reading);
            }
            curStep--;
            curFreq = 0;
            setCarbon = tempSetCarbon;
            tempSetCarbon = new HashSet<Integer>();
        }
        for (int i:setOxygen) oxyRead = i;
        for (int i : setCarbon) carRead = i;
        System.out.println(oxyRead*carRead);
    }
}
