import java.util.*;
public class Beacon {
    final int x, y, z;
    Beacon(int a, int b, int c) {
        x = a;
        y = b;
        z = c;
    }
    
    // return the square of the distance of this Beacon to that Beacon
    public long sqrDistanceTo(Beacon that) {
        return (long)((this.x - that.x)*(this.x - that.x)
                     +(this.y - that.y)*(this.y - that.y)
                     +(this.z - that.z)*(this.z - that.z));
    }
    public String toString() {
        return x+","+y+","+z;
    }
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Beacon))
            return false;
        if (((Beacon)o).x == x &&((Beacon)o).y == y
                &&((Beacon)o).z == z)
            return true;
        else return false;
    }
    public int hashCode() {
        int hash = 31;
        hash = 7*hash + x;
        hash = 7*hash + y;
        hash = 7*hash + z;
        return hash;
    }
    public Beacon clone() {
        return new Beacon(x, y, z);
    }
}
