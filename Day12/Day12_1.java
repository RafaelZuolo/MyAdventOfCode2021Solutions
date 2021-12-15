import java.util.*;
public class Day12_1{
    
    public static long enumDFS(Map<String, Set<String>> graph, String start, 
                            String end, Set<String> lowVisited) {
        //System.out.print(start+" ");
        if (start.equals(end)) {/* System.out.println(); */return 1;}
        if (start.equals(start.toLowerCase())) lowVisited.add(start);
        long numPaths = 0;
        for (String vertex : graph.get(start)) {
            if (lowVisited.contains(vertex)) continue;
            else {
                numPaths += enumDFS(graph, vertex, end, new HashSet<String>(lowVisited));
            }
        }
        return numPaths;
    }
    
    
    
    public static void main(String[] args) {
        Map<String, Set<String>> graph = new HashMap<String, Set<String>>();
        Scanner sc = new Scanner(System.in);
        // build graph
        while (sc.hasNextLine()) {
            String[] adj = sc.nextLine().split("-");
            graph.putIfAbsent(adj[0], new HashSet<String>());
            graph.putIfAbsent(adj[1], new HashSet<String>());
            graph.get(adj[0]).add(adj[1]);
            graph.get(adj[1]).add(adj[0]);
        }
        // check the graph
        /* for (String s : graph.keySet()) {
            System.out.print(s+"->" );
            for (String t : graph.get(s)) 
                System.out.println(t);
        } */
        long numPaths = enumDFS(graph, "start", "end", new HashSet<String>());
        System.out.println(numPaths);
    }
}