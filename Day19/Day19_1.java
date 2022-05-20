import java.util.*;
import java.io.*;
public class Day19_1 {
   // try 1 : 261 was too low
   // try 2 : 285 was too low
   // try 3 : 364 was too high
   // try 4 : 303 was right!

    public static void main(String[] args) {
        
        // here we initialize the rotationSet
        int[][][] rotationSet = new int[24][3][3];
        try {
            Scanner rotSc = new Scanner(new File("rotations.txt"));
            int k = 0;
            while (rotSc.hasNext()) {
                rotSc.next(); // always start with a "//"
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        rotationSet[k][i][j] = rotSc.nextInt();
                    }
                }
                k++;
            }
            rotSc.close();
        } catch(IOException e) {  
            e.printStackTrace();  
            System.exit(1);
        }  
        
        Set<Beacon> absoluteScan = new HashSet<>();
        Set<BeaconScan> scanAdded = new HashSet<>();
        Set<BeaconScan> scanNotAdded = new HashSet<>();

        // data structure to "hold" all the beaconScans
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
            scanNotAdded.add(bc);
        }
        sc.close();
        // we initialize our absoluteScan with Scan 0:
        for (Beacon b : scanList.get(0).xOrder) {
            absoluteScan.add(b);
        }
        scanAdded.add(scanList.get(0));
        scanNotAdded.remove(scanList.get(0));
        // For each beaconScan, we create a list with all pairs of distances
        // between its beacons
        // In our instance, we will abuse the fact that all distances are distinct
        // and map the value of the distance to the pair of index of the beacon X list that generated it
        Map<BeaconScan, List<Long>> distanceList = new HashMap<>();
        //Map<BeaconScan, Set<Long>> scanToDistMap = new HashMap<>();
        Map<BeaconScan, Map<Long, Set<Integer>>> ScanToDistToIndex = new HashMap<>();
        Map<BeaconScan, Map<Long, Set<Beacon>>> ScanToDistToBeacon = new HashMap<>();
        
        for (BeaconScan bs : scanList) {
            // put all pair distances of beacons relative to the sensor
            List<Long> distances = new ArrayList<>();
            //Set<Long> distanceSet = new HashSet<>();
            Map<Long, Set<Integer>> distMap = new HashMap<>();
            Map<Long, Set<Beacon>> distBeaconMap = new HashMap<>();
            
            for (Beacon b1 : bs.xOrder) {
                for (Beacon b2 : bs.xOrder) {
                    if (b1.equals(b2)) continue;
                    long dist = b1.sqrDistanceTo(b2);
                    //distanceSet.add(dist);
                    
                    Set<Beacon> duplicate = distBeaconMap.putIfAbsent(dist, Set.of(b1,b2));
                    if (duplicate != null) {
                        assert duplicate.equals(Set.of(b1,b2));
                    }
                }
            }
            //scanToDistMapTo.put(bs, distanceSet);
            for (int i = 0; i < bs.xOrder.size(); i++) {
                Beacon b1 = bs.xOrder.get(i);
                for (int j = i+1; j < bs.xOrder.size(); j++) {
                    Beacon b2 = bs.xOrder.get(j);
                    long dist = b1.sqrDistanceTo(b2);
                    distances.add(dist);
                    distMap.put(dist, Set.of(i,j));
                }
            }
            distances.sort(null);
            distanceList.put(bs, distances);
            ScanToDistToIndex.put(bs, distMap);
            ScanToDistToBeacon.put(bs, distBeaconMap);
            assert (distances.size() == distMap.size());
            assert (distances.size() == distBeaconMap.size());
        }
        
        
        int overCount = 0; // number of scans seen by each scanner
        int intersectCount = 0; // number of scanners in all the intersections
        int correctedCount = 0;
        
        // will redo this part
        
        while (scanNotAdded.size()>0) {
            BeaconScan toAdd = null;
            for (BeaconScan bs1 : scanNotAdded) {
                for (BeaconScan bs2 : scanAdded) {
                    Map<Long, Set<Beacon>> mapB1 = ScanToDistToBeacon.get(bs1);
                    Map<Long, Set<Beacon>> mapB2 = ScanToDistToBeacon.get(bs2);
                    // get the indexes of the intersection
                    Set<Beacon> intersecB1 = new HashSet<>();
                    Set<Beacon> intersecB2 = new HashSet<>();
                    int test = 0;
                    for (Long l : mapB1.keySet()) {
                        if (mapB2.containsKey(l)) {
                            test++;
                            intersecB1.addAll(mapB1.get(l));
                            intersecB2.addAll(mapB2.get(l));
                        }
                    }
                    test = (int)((2 + Math.sqrt(1 + 8*test))/2);                    
                    if (intersecB1.size() == 12) {
                        //System.out.println("test size = " + test+ ". sizes = " + intersecB1.size() + ", " + intersecB2.size());
                        assert intersecB2.size() == test;
                        assert intersecB1.size() == test;
                    
                        // deduce rotation matrix and displacement, then remove from scanNotAdded and add to scanAdded with the correct matrix and displacement
                        Map<Beacon, Beacon> beaconPairing = new HashMap<>(); // map the "equal beacons"
                        for (Beacon b1 : intersecB1) {
                            Beacon c1=null, c2 = null;
                            for (Beacon b2 : intersecB1) {
                                if (b1.equals(b2)) continue;
                                if (c1 == null) c1 = b2;
                                else { c2 = b2; break;}
                            }
                            long d1 = b1.sqrDistanceTo(c1);
                            long d2 = b1.sqrDistanceTo(c2);
                            assert d1 != d2;
                            for (Beacon candidate1 : mapB2.get(d1)) {
                                for (Beacon candidate2 : mapB2.get(d2)) {
                                    if (candidate1.equals(candidate2)) {
                                        beaconPairing.put(b1, candidate1);
                                    }
                                }
                            }
                        }
                        
                        assert beaconPairing.size() == 12; 
                        
                        Beacon pivot = null;
                        for (Beacon b : beaconPairing.keySet()) {
                            pivot = b;
                            break;
                        }
                        Beacon pivotPair = beaconPairing.get(pivot);
                        assert !pivot.equals(pivotPair);
                        // now we test all rotation matrices with the displacement to take pivot to its pair
                        for (int i = 0; i < 24; i++) {
                            int[] displTry = new int[3];
                            Beacon pivotRot = applyRot(pivot, rotationSet[i]);
                            displTry[0] = pivotPair.x - pivotRot.x;
                            displTry[1] = pivotPair.y - pivotRot.y;
                            displTry[2] = pivotPair.z - pivotRot.z;
                            pivotRot = applyRotAndDispl(pivot, rotationSet[i], displTry);
                            assert pivotPair.equals(pivotRot);
                            boolean matrixAndDisplIsCorrect = true;
                            for (Beacon b : beaconPairing.keySet()) {
                                if (!beaconPairing.get(b)
                                    .equals(applyRotAndDispl(b, rotationSet[i], displTry))) {
                                    matrixAndDisplIsCorrect = false;
                                    break;
                                }
                            }
                            if (matrixAndDisplIsCorrect) {
                                //printRotM(rotationSet[i]);
                                //System.out.println("displ = " + Arrays.toString(displTry));
                                //System.out.println("\n");
                                
                                bs1.rotM = matrixMult(bs2.rotM, rotationSet[i]);
                                int[] partialDispl = arrayMult(bs2.rotM, displTry);
                                //bs1.rotM = matrixMult( rotationSet[i],bs2.rotM);
                                bs1.displ = new int[3];
                                bs1.displ[0] = bs2.displ[0] + partialDispl[0];
                                bs1.displ[1] = bs2.displ[1] + partialDispl[1];
                                bs1.displ[2] = bs2.displ[2] + partialDispl[2];
                                toAdd = bs1;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (toAdd != null) break;
            }
            assert toAdd != null;
            for (Beacon b : toAdd.transformedBeacons()) {
                absoluteScan.add(b);
            }
            scanAdded.add(toAdd);
            scanNotAdded.remove(toAdd);
        }
        System.out.println("Part 1 answer: " + absoluteScan.size());
    }
    
    public static int[][] matrixMult(int[][] A, int[][] B) {
        assert A[0].length == B.length;
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[i].length; k++) {
                    C[i][j] += A[i][k]*B[k][j];
                }
            }
        }
        return C;
    }
    
    public static int[] arrayMult(int[][] A, int[] u) {
        assert A[0].length == u.length;
        int[] v = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < u.length; j++) {
                v[i] += A[i][j]*u[j];
            }
        }
        return v;
    }
    
    public static void printRotM(int[][] rotM) {
        System.out.println(Arrays.toString(rotM[0]));
        System.out.println(Arrays.toString(rotM[1]));
        System.out.println(Arrays.toString(rotM[2]));
    }
    
    public static Beacon applyRot(Beacon b, int[][] rotM) {
        return new Beacon(rotM[0][0]*b.x + rotM[0][1]*b.y + rotM[0][2]*b.z,
                          rotM[1][0]*b.x + rotM[1][1]*b.y + rotM[1][2]*b.z,
                          rotM[2][0]*b.x + rotM[2][1]*b.y + rotM[2][2]*b.z);
    }
    
    public static Beacon applyRotAndDispl(Beacon b, int[][] rotM, int[] displ) {
        return new Beacon(rotM[0][0]*b.x + rotM[0][1]*b.y + rotM[0][2]*b.z + displ[0],
                          rotM[1][0]*b.x + rotM[1][1]*b.y + rotM[1][2]*b.z + displ[1],
                          rotM[2][0]*b.x + rotM[2][1]*b.y + rotM[2][2]*b.z + displ[2]);
    }
}

/*
pair: 0, 24
pair: 1, 6
pair: 1, 10
pair: 1, 19
pair: 1, 23
pair: 2, 15
pair: 2, 18
pair: 2, 21
pair: 3, 12
pair: 3, 13
pair: 4, 21
pair: 5, 16
pair: 6, 11
pair: 7, 11
pair: 7, 20
pair: 8, 12
pair: 9, 12
pair: 9, 22
pair: 10, 17
pair: 11, 21
pair: 11, 23
pair: 12, 14
pair: 12, 15
pair: 13, 15
pair: 13, 18
pair: 14, 17
pair: 14, 22
pair: 15, 17
pair: 16, 24
pair: 17, 21
pair: 17, 23
pair: 18, 24
*/