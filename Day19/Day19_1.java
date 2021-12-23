import java.util.*;
public class Day19_1 {
   
    public static int[] getDisplacement(Beacon a, Beacon b) {
        int[] disp = new int[3];
        disp[0] = a.x - b.x;
        disp[1] = a.y - b.y;
        disp[2] = a.z - b.z;
        return disp;
    }

    public static int intersectionSize(BeaconScan a, BeaconScan b) {
        int intersec = 0;
        Beacon aFirst = a.xOrder.get(0);
        Beacon bLast = b.xOrder.get(b.xOrder.size()-1);
        int maxInter = 0;
        for (int i = 0; i < a.xOrder.size(); i++) {
            Beacon aTest = a.xOrder.get(i);
            int dispX = aTest.x - bLast.x;
            int dispY = aTest.y - bLast.y;
            int dispZ = aTest.z - bLast.z;
            int xMax = aFirst.x; 
            int yMax = aFirst.y; 
            int zMax = aFirst.z; 
            Beacon bCurrent = bLast;
            for (int j = b.xOrder.size()-1; bCurrent.x + dispX <= xMax 
            && j >= 0; j--) {
                bCurrent = b.xOrder.get(j);
                if (!a.xOrder.contains(new Beacon(bCurrent.x + dispX, 
                                bCurrent.y + dispY, 
                                bCurrent.z + dispZ))) { 
                    intersec = 0;
                    break;
                }
                intersec++;
            }
            maxInter = Math.max(intersec, maxInter);
        }
        return maxInter;
    }

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
        System.out.println(intersectionSize(scanList.get(0), scanList.get(1)));
    }
}

