import java.util.*;
public class BeaconScan {

    class Beacon {
        final int x, y, z;
        Beacon(int a, int b, int c) {
            x = a;
            y = b;
            z = c;
        }
        public String toString() {
            return x+","+y+","+z;
        }
    }

    List<Beacon> beaconList = null;
    int dir = 0;
    int rot = 0;
    
    BeaconScan() {
        beaconList = new ArrayList<Beacon>();
    }
    
    public void addBeacon(int x, int y, int z) {
        beaconList.add(new Beacon(x,y,z));
    }
    public void addBeacon(String s) {
        String[] vals = s.split(",");
        beaconList.add(new Beacon(Integer.parseInt(vals[0]),Integer.parseInt(vals[1]),Integer.parseInt(vals[2])));
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Beacon b : beaconList) {
            s.append(b.toString()+"\n");
        }
        return s.toString();
    }

}
