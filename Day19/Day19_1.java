import java.util.*;
public class Day19_1 {
   
    

    public static void main(String[] args) {

        List<BeaconScan> scanList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            BeaconScan bc = new BeaconScan();
            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.equals("")) break;
                if (s.charAt(1) == '-') continue;
                bc.addBeacon(s);
            }
            scanList.add(bc);
        }
        for (BeaconScan bc : scanList) {
            bc.sortBeacon();
            System.out.println(bc);
        }
    }
}

