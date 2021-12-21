import java.util.*;
public class Day19_1 {
    
    public static void main(String[] args) {
        BeaconScan bc = new BeaconScan();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("")) break;
            if (s.charAt(1) == '-') continue;
            bc.addBeacon(s);
        }
        System.out.println(bc);
    }
}

