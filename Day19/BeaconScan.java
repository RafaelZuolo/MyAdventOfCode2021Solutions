import java.util.*;
public class BeaconScan {

    List<Beacon> xOrder = null;
    List<Beacon> yOrder = null;
    List<Beacon> zOrder = null;
    int dir = 0;
    int rot = 0;
    
    BeaconScan() {
        xOrder = new ArrayList<Beacon>();
        yOrder = new ArrayList<Beacon>();
        zOrder = new ArrayList<Beacon>();
    }
    
    public void sortBeacon() {
        xOrder.sort((Beacon a, Beacon b)->a.x - b.x);
        yOrder.sort((Beacon a, Beacon b)->a.y - b.y);
        zOrder.sort((Beacon a, Beacon b)->a.z - b.z);
    }

    public void addBeacon(int x, int y, int z) {
        Beacon b = new Beacon(x,y,z);
        xOrder.add(b);
        yOrder.add(b);
        zOrder.add(b);
    }
    public void addBeacon(String s) {
        String[] vals = s.split(",");
        addBeacon(Integer.parseInt(vals[0]),
                  Integer.parseInt(vals[1]),
                  Integer.parseInt(vals[2])
                  );
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Beacon b : xOrder) {
            s.append(b.toString()+"\n");
        }
        return s.toString();
    }

}
