import java.util.*;
public class Day23_2 {
   	static class State {
        //int totalCost = -1; // to use in the shortest path algorithm.
        //int adjCost = -1;   // to use in the shortest path algorithm.
		char[][] hole;
		char[] hall;
		public State(char[][] ho, char[] ha) {
			hole = ho;
			hall = ha;
		}

		public State clone() {
			char[][] cloneHole = new char[4][4];
			char[] cloneHall = Arrays.copyOf(hall, hall.length);
			for (int i = 0; i < 4; i++) {
				cloneHole[i] = Arrays.copyOf(hole[i], hole[i].length);
			}
            State clone = new State(cloneHole, cloneHall);
            //clone.cost = this.cost;
			return clone;
		}
		
		public int hashCode() {
			int hash = Arrays.hashCode(hall);
			for (int i = 0; i < hole.length; i++) {
				hash = hash ^ Arrays.hashCode(hole[i]);
			}
			return hash;
		}

		public String toString() {		
			Formatter f = new Formatter();
			String out = f.format("#############\n#%c%c%c%c%c%c%c%c%c%c%c#\n###%c#%c#%c#%c###\n  #%c#%c#%c#%c#\n  #%c#%c#%c#%c#\n  #%c#%c#%c#%c#\n  #########", hall[0],hall[1],hall[2],hall[3],hall[4],hall[5],hall[6],hall[7],hall[8],hall[9],hall[10],hole[0][0],hole[1][0],hole[2][0],hole[3][0],hole[0][1],hole[1][1],hole[2][1],hole[3][1],hole[0][2],hole[1][2],hole[2][2],hole[3][2],hole[0][3],hole[1][3],hole[2][3],hole[3][3]).toString();
			return out;
		}
		
		public boolean equals(Object o) {
            if (o == this)
                return true;
            
            if (!(o instanceof State))
                return false;
            
            State that = (State) o;
			if (!Arrays.equals(this.hall, that.hall))
				return false;
			for (int i = 0; i < hole.length; i++) {
				if (!Arrays.equals(this.hole[i], that.hole[i]))
					return false;
			}
			return true;
		}
		
		public static int lastPositionEmpty(char[] array) {  // return the last address of the array with the char '.'
			for (int i = 0; i < array.length; i++) {
				if (array[i] != '.') return (i-1);
			}
			return (array.length-1);
		}
		
		public static boolean holeIsPure(char amphipod, char[] amphiHole) { // check if hole only have one specific type of amphipode
			for (int i = 0; i < amphiHole.length; i++) {
				if (amphiHole[i] != amphipod && amphiHole[i] != '.')
					return false;
			}
			return true;
		}

		public static boolean hallIsClear(int start, int end, char[] currentHall) {
			int increment = 1;
			if (start < end) increment = -1;
			for (int i = end; i!= start; i += increment) {
				if (currentHall[i] != '.') return false;
			}
			return true;
		}

		//
		// brace yourself for convoluted if-else chained spaghetti
		//
		public Map<State, Integer> adj() { // compute the adjacent states and the cost to reach them
			Map<State, Integer> adj = new HashMap<>();
			// hall to hole movements
			for (int i = 0; i < this.hall.length; i++) {
				if (hall[i] == '.') continue;
				// case A. Address of hole of 'A' is [0], and hall entrance is [2] 
				if (hall[i] == 'A' && holeIsPure('A', hole[0]) && hallIsClear(i, 2, this.hall)) {
					int holePosition = lastPositionEmpty(hole[0]);
					int cost = Math.abs(i - 2) + holePosition + 1;
					State step = this.clone();
					step.hall[i] = '.';
					step.hole[0][holePosition] = 'A';
					adj.put(step, cost);
				} 
				// case B. Address oh hole of 'B' is [1], and hall entrance is [4]
				if (hall[i] == 'B'  && holeIsPure('B' , hole[1]) && hallIsClear(i, 4, this.hall)) {
					int holePosition = lastPositionEmpty(hole[1]);
					int cost = 10*(Math.abs(i - 4) + holePosition + 1);
					State step = this.clone();
					step.hall[i] = '.';
					step.hole[1][holePosition] = 'B';
					adj.put(step, cost);
				}
				// case C. Address oh hole of 'C' is [2], and hall entrance is [6]
				if (hall[i] == 'C' && holeIsPure('C', hole[2]) && hallIsClear(i, 6, this.hall)) {
					int holePosition = lastPositionEmpty(hole[2]);
					int cost = 100*(Math.abs(i - 6) + holePosition + 1);
					State step = this.clone();
					step.hall[i] = '.';
					step.hole[2][holePosition] = 'C';
					adj.put(step, cost);
				}
				// case D. Address oh hole of 'D' is [3], and hall entrance is [8]
				if (hall[i] == 'D' && holeIsPure('D', hole[3]) && hallIsClear(i, 8, this.hall)) {
					int holePosition = lastPositionEmpty(hole[3]);
					int cost = 1000*(Math.abs(i - 8) + holePosition + 1);
					State step = this.clone();
					step.hall[i] = '.';
					step.hole[3][holePosition] = 'D';
					adj.put(step, cost);
				}
			}
            
            // hole to hall movements
            char[] originalHole = new char[]{'A', 'B', 'C', 'D'}; // easy  map of index to hole type
            for (int i = 0; i < hole.length; i++) {
                if (holeIsPure(originalHole[i], hole[i]) || lastPositionEmpty(hole[i]) == hole[i].length-1) // nothing will leave this hole
                    continue;
                int position = lastPositionEmpty(hole[i]) + 1; // 
                assert position < hole[i].length;    
                int hallStartPos = (i+1)*2;   // position in the hall
                for (int j = 0; j < hall.length; j++) {
                    // forbidden hall positions
                    if (j==2 || j==4 || j==6 || j==8) 
                        continue; 
                    
                    int cost = position + 1; // cost of moving out of the hole
                    int multiplicator = 1;
                    char amphipod = hole[i][position];
                    
                    // setting the right cost multiplicator
                    assert amphipod != '.';
                    if      ( amphipod == 'B' ) multiplicator = 10;
                    else if ( amphipod == 'C' ) multiplicator = 100;
                    else if ( amphipod == 'D' ) multiplicator = 1000;
                    
                    
                    if (hallIsClear(hallStartPos, j, this.hall)) {
                        cost += Math.abs(hallStartPos - j);
                        State step = this.clone();
                        assert hall[j] == '.';
                        step.hall[j] = step.hole[i][position];
                        step.hole[i][position] = '.';
                        adj.put(step, cost*multiplicator);
                    }
                }
            }
			return adj;
		}
	}

    public static void main(String[] args) {
        int leastEnergyOfTest = 44169;
	    Scanner sc = new Scanner(System.in);
		char[][] startHole = new char[4][4];
		char[]	 startHall = new char[11];
		sc.nextLine();
		String firstLine = sc.nextLine(); 		// first two lines dont contain Amphipod
		for (int i = 0; i < 11; i++) 
			startHall[i] = firstLine.charAt(i+1);
		for (int i = 0; i < 4; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < 4; j++) {
				startHole[j][i] = line.charAt(2*j+3);
			}
		}
		State start = new State(startHole, startHall);
        State cloneStart = start.clone();
		System.out.println(start);
        
        char[][] endHole = new char[4][4];
		char[]	 endHall = new char[11];
        char[] originalHole = new char[]{'A', 'B', 'C', 'D'}; // easy  map of index to hole type
		for (int i = 0; i < 11; i++) 
			endHall[i] = '.';
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				endHole[i][j] = originalHole[i];
			}
		}
		State end = new State(endHole, endHall);
        System.out.println(end);

		Map<State, Integer> visited = new HashMap<>(); // States with minimum cost to get to them come here
		Map<State, Integer> hook = new HashMap<>(); // Current visit cost of frontier unvisited states 
        //Map<State, State>   hookOrigin = new HashMap<>(); // "frontier"
		hook.put(start, 0);
        
        
        for (int i = 0; i >=0  ; i++) {
            Map.Entry<State, Integer> minCostState = selectMin(hook);
            visited.put(minCostState.getKey(), minCostState.getValue());
            hook.remove(minCostState.getKey());
            if (visited.containsKey(end)) {
                System.out.println(visited.get(end));
                break;
            }
            if (i%1000 == 0) System.out.println(hook.size() + " " + visited.size() + " " + i);
            //System.out.println(start.hashCode() == cloneStart.hashCode());
            //System.out.println("equals test "+start.equals(cloneStart) + " " + cloneStart.equals(start));
            //System.out.println("visited.containsKey() test "+visited.containsKey(cloneStart) + " " + visited.containsKey(start));
            //System.out.println(hook.size());
            
            for (Map.Entry<State, Integer> frontierState : minCostState.getKey().adj().entrySet()) {
                if (frontierState.getKey().equals(end)) {
                    System.out.println(minCostState.getKey() + "\n" + minCostState.getValue()+"\n");
                }
                    
                if (visited.containsKey(frontierState.getKey())) {
                    continue;
                }
                int newCost = minCostState.getValue() + frontierState.getValue();
                Integer oldCost = hook.putIfAbsent(frontierState.getKey(), newCost);
                if (oldCost != null && oldCost.compareTo(newCost) > 0) {
                    hook.replace(frontierState.getKey(), newCost);
                }
            }   
            //System.out.println(hook.size());
        }
        /* for (State s : visited.keySet()) {
            System.out.println(s);
        }
        System.out.println("\n");
        for (State s : hook.keySet()) {
            System.out.println(s);
        } */
	}
    
    public static Map.Entry<State, Integer> selectMin(Map<State, Integer> map) {
        Map.Entry<State, Integer> min = null;
        for (Map.Entry<State, Integer> e : map.entrySet()) {
            if (min == null) {
                min = e;
            } else if (e.getValue().compareTo(min.getValue()) < 0){
                min = e;
            }
        }
        return min;
    }
}
