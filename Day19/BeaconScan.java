import java.util.*;
public class BeaconScan {

    List<Beacon> xOrder = null;
    List<Beacon> yOrder = null;
    List<Beacon> zOrder = null;
    int dir = 0;
    int rot = 0;
    
    int[][] rotM = new int[][]{{1,0,0},{0,1,0},{0,0,1}}; // identity matrix
    int[] displ = new int[]{0,0,0};
    
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
    
    public void addBeacon(Beacon b) {
        Beacon bNew = b.clone();
        xOrder.add(bNew);
        yOrder.add(bNew);
        zOrder.add(bNew);
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
    public List<Beacon> transformedBeacons() {
        List<Beacon> l = new ArrayList<>();
        for (Beacon b : xOrder) {
            l.add(new Beacon(rotM[0][0]*b.x + rotM[0][1]*b.y + rotM[0][2]*b.z + displ[0],
                             rotM[1][0]*b.x + rotM[1][1]*b.y + rotM[1][2]*b.z + displ[1],
                             rotM[2][0]*b.x + rotM[2][1]*b.y + rotM[2][2]*b.z + displ[2]));
        }
        return l;
    }
}
