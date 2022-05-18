import java.util.*;
import java.io.*;
public class Day19_1 {
   // try 1 : 261 was too low
   // try 2 : 285 was too low
   // try 3 : 364 was too high
   /* public static int[] getDisplacement(Beacon a, Beacon b) {
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
    } */

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
        Map<BeaconScan, Map<Long, Set<Integer>>> ScanToDistToIndex = new HashMap<>();
        
        for (BeaconScan bs : scanList) {
            // put all pair distances of beacons relative to the sensor
            List<Long> distances = new ArrayList<>();
            Map<Long, Set<Integer>> distMap = new HashMap<>();
            
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
            assert (distances.size() == distMap.size());
        }
        
        
        int overCount = 0; // number of scans seen by each scanner
        int intersectCount = 0; // number of scanners in all the intersections
        int correctedCount = 0;
        
        // will redo this part
        
        while (scanNotAdded.size()>0) {
            BeaconScan toAdd = null;
            for (BeaconScan bs1 : scanNotAdded) {
                for (BeaconScan bs2 : scanAdded) {
                    int partialCount = overCounter(bs1, bs2, distanceList);
                    partialCount = (int)((2 + Math.sqrt(1 + 8*partialCount))/2);
                    if (partialCount >= 12) {
                        Map<Long, Set<Integer>> map1 = ScanToDistToIndex.get(bs1);
                        Map<Long, Set<Integer>> map2 = ScanToDistToIndex.get(bs2);
                        // get the indexes of the intersection
                        Set<Integer> intersec1 = new HashSet<>();
                        Set<Integer> intersec2 = new HashSet<>();
                        for (Long l : map1.keySet()) {
                            if (map2.containsKey(l)) {
                                intersec1.addAll(map1.get(l));
                                intersec2.addAll(map2.get(l));
                            }
                        }
                        assert intersec1.size() == 12;
                        assert intersec2.size() == 12;
                        // deduce rotation matrix and displacement, then remove from scanNotAdded and add to scanAdded with the correct matrix and displacement
                        Map<Beacon, Beacon> beaconPairing = new HashMap<>(); // map the "equal beacons"
                        for (Integer i : intersec1) {
                            int first = -1;
                            int second = -1;
                            for (Integer j : intersec1) {
                                if (i == j) continue;
                                if (first == -1) first = j;
                                else             second = j;
                            }
                            assert first != second;
                            long d1 = bs1.xOrder.get(i).sqrDistanceTo(bs1.xOrder.get(first));
                            long d2 = bs1.xOrder.get(i).sqrDistanceTo(bs1.xOrder.get(second));
                            assert d1 != d2;
                            int pairedBeaconAdr = -1;
                            for (Integer k : map2.get(d1)) {
                                for (Integer l : map2.get(d2)) {
                                    if (k.equals(l)) pairedBeaconAdr = k;
                                }
                            }
                            assert pairedBeaconAdr != -1;
                            //System.out.println("ok");
                            //System.exit(1);
                            beaconPairing.put(bs1.xOrder.get(i), bs2.xOrder.get(pairedBeaconAdr));
                        }
                        assert beaconPairing.size() == 12;
                        //System.out.println("ok12");
                        //System.exit(1);
                        // we have the beacon "pairing", now we need to deduce the matrix and displacement
                        // first we grab one beacon
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
                                bs1.rotM = matrixMult(bs2.rotM, rotationSet[i]);
                                bs1.displ = new int[3];
                                bs1.displ[0] = bs2.displ[0] + displTry[0];
                                bs1.displ[1] = bs2.displ[1] + displTry[1];
                                bs1.displ[2] = bs2.displ[2] + displTry[2];
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
        System.out.println(absoluteScan.size());
        /*
        for (int i = 0; i < scanList.size(); i++) {
            BeaconScan bs1 = scanList.get(i);
            //overCount += bs1.xOrder.size();
            for (int j = i+1; j < scanList.size(); j++) {
                //duplas++;
                BeaconScan bs2 = scanList.get(j);
                int partialCount = overCounter(bs1, bs2, distanceList);
                //System.out.println("partialCount no sqrt = " + partialCount);
                partialCount = (int)((2 + Math.sqrt(1 + 8*partialCount))/2);
                System.out.println("partialCount sqrt of " + i + " and " + j + " = " + partialCount);
                if (partialCount >= 12) {
                    intersectCount += partialCount;  
                    System.out.println("pair: "+i + ", " + j);
                }
            }                    
        }

        for (int i = 0; i < scanList.size(); i++) {
            BeaconScan bs1 = scanList.get(i);
            Set<Integer> beaconIndexesToCount = new HashSet<>();
            for (int j = 0; j < bs1.xOrder.size(); j++) {
                beaconIndexesToCount.add(j);
            }
            System.out.println(beaconIndexesToCount.size() + " is the initial size" );
            System.out.println("Inserted indexes:");
            for (Integer in : beaconIndexesToCount) {
                System.out.print(in + ", ");
            }
            System.out.println();
            for (int j = 0; j < i; j++) {
                BeaconScan bs2 = scanList.get(j);
                int partialCount = overCounter(bs1, bs2, distanceList);
                partialCount = (int)((2 + Math.sqrt(1 + 8*partialCount))/2);
                // remove the beacons that are in the intersection, since the have been counted already
                if (partialCount >= 12) { 
                    System.out.println("Got overlapping pairs: " + i + ", " +j);
                    duplicateIndexSetRemover(ScanToDistToIndex, distanceList, bs1, bs2, beaconIndexesToCount);   
                }
                System.out.println("pair " + i + ", " + j + " changed to "+ beaconIndexesToCount.size());
            }
            correctedCount += beaconIndexesToCount.size();
        }
        //System.out.println(overCount+ " " + intersectCount);
        //System.out.println(overCount - intersectCount);
        //System.out.println(intersectionSize(scanList.get(0), scanList.get(1)));
        System.out.println(correctedCount);
        */
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
    
    public static int overCounter(BeaconScan bs1, 
                                  BeaconScan bs2, 
                                  Map<BeaconScan, List<Long>> distanceList) {
        int count = 0;
        List<Long> l1 = distanceList.get(bs1);
        // check if all distances are distincts
        for (int i = 0; i < l1.size()-1; i++) {
            assert (!l1.get(i).equals(l1.get(i+1)));
        }
        List<Long> l2 = distanceList.get(bs2);
        int i = 0, j = 0;
        l1.sort(null);
        l2.sort(null);
        while (i <l1.size() && j < l2.size()) {
            if (l1.get(i).equals(l2.get(j))) {
                i++; j++; count++;
                
            } else if (l1.get(i).compareTo(l2.get(j)) < 0) {
                i++;
            } else {
                j++;
            }
        }
        return count;
    }
    
    public static void duplicateIndexSetRemover(
                            Map<BeaconScan, Map<Long, Set<Integer>>> ScanToDistToIndex,
                            Map<BeaconScan, List<Long>> distanceList,
                            BeaconScan bs1, BeaconScan bs2, 
                            Set<Integer> beaconIndexesToCount) {
        Set<Integer> duplicatedIndexes = new HashSet<>();
        Map<Long, Set<Integer>> m1 = ScanToDistToIndex.get(bs1);
        List<Long> l1 = distanceList.get(bs1);
        List<Long> l2 = distanceList.get(bs2);
        // check if all distances are distincts
        for (int i = 0; i < l1.size()-1; i++) {
            assert (!l1.get(i).equals(l1.get(i+1)));
        }
        int i = 0, j = 0;
        //l1.sort(null);  // they are already sorted
        //l2.sort(null);
        while (i <l1.size() && j < l2.size()) {
            if (l1.get(i).equals(l2.get(j))) {
                // for each pair of indexes that have dist l1.get(i), remove them from beaconIndexesToCount
                for (Integer index : m1.get(l1.get(i))) {
                    //System.out.print(index + "---");
                    beaconIndexesToCount.remove(index);
                    duplicatedIndexes.add(index);
                }
                // System.out.println();
                i++; 
                j++; 
            } else if (l1.get(i).compareTo(l2.get(j)) < 0) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println("Duplicated indexes:");
        for (Integer in : duplicatedIndexes) {
            System.out.print(in + ", ");
        }
        System.out.println();
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