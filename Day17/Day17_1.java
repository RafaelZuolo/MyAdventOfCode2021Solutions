import java.util.*;

// we will assume that the y coordinates of the target are less than 0,
// and the x coordinate are greater that 0.
public class Day17_1 {
    static long xMin;
    static long xMax;
    static long yMin;
    static long yMax;

    static double exatMinVX;
    static long minVX, minVY, maxVX, maxVY;
    static long vx, x, vy, y;

    static {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        String[] xArea = tokens[2].split("\\=|\\.\\.|\\,");
        String[] yArea = tokens[3].split("\\=|\\.\\.|\\,");

        //System.out.println(tokens[2]+" "+Arrays.toString(xArea));

        xMin = Long.parseLong(xArea[1]);
        xMax = Long.parseLong(xArea[2]);
        yMin = Long.parseLong(yArea[1]);
        yMax = Long.parseLong(yArea[2]);
        
        // the max value x can be is from a arithmetic series, thus
        // we solve the quadratic equation to know the min value vx
        // must have to achieve xMin
        exatMinVX = (-1 + Math.sqrt(1 + 8*xMin))/2;
        minVY = 0;
        x = 0;
        y = 0;
        minVX = (long) exatMinVX;
        maxVX = xMax;
        maxVY = -yMin;
    } 

    public static void setStartConditions(long vxInit, long vyInit) {
        x = 0;
        y = 0;
        vx = vxInit;
        vy = vyInit;
    }

    public static void step() {
        x += vx;
        y += vy;
        if (vx > 0) vx--;
        vy--;
    }

    // check if it is still possible to get to the target
    public static boolean isViable() {
        if (vx == 0 && (x < xMin || xMax < x)) return false;
        if (y < yMin) return false;
        return true;
    }

    // check if we are in the target
    public static boolean isInTarget() {
        return (xMin <= x && x <= xMax) && (yMin <= y && y <= yMax);
    }

    public static void main(String[] args) {
        int numSteps = 0;
        long vxInit = minVX;
        long vyInit = minVY;
        long currentMaxY = 0;
        long globalMaxY = 0;
        System.out.printf("%d, %d, %d, %d\n", minVX, minVY, maxVX, maxVY) ;
        for (vyInit = minVY; vyInit < maxVY; vyInit++) {
            for (vxInit = minVX; vxInit < maxVX; vxInit++) {
                setStartConditions(vxInit, vyInit);
                numSteps = 0;
                while (isViable()) {
                    step();
                    numSteps++;
                    if (vy == 0) currentMaxY = y; 
                    if (isInTarget()) {
                        globalMaxY = globalMaxY < currentMaxY ? currentMaxY : globalMaxY;
                        //System.out.printf("(%d, %d) got max of %d", vxInit, vyInit, currentMaxY);
                        break;
                    }
                }
            }
        }
        System.out.println("maximum of y = "+globalMaxY);
    }
}
