package Algorithms;
import DataStructures.WeightedGraph;

import java.util.ArrayList;
import java.util.Map;

public class UCS {
    public WeightedGraph graph;
    public int V;

    public UCS(int V) {
        this.V = V;
        graph = new WeightedGraph(V);
    }

    public void ucs(int s) {
        boolean[] visited = new boolean[V];

        // Array to store the shortest distance from source to i
        int[] dist = new int[V];

        // Initialize all distances as INFINITE and visited[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        // Find shortest path for all nodes
        for (int i = 0; i < V; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;
            // Update dist value of the adjacent nodes of the picked node
            for (Map.Entry<Integer, Integer> entry : graph.adj.get(u).entrySet()) {
                int v = entry.getKey();
                int weight = entry.getValue();
                if (!visited[v] && weight != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        printSolution(dist);
    }

    public ArrayList<Integer> ucsSolve(int start, int end){
        boolean[] visited = new boolean[V];
        int[] dist = new int[V];
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        for (int i = 0; i < V; i++) {

            int u = minDistance(dist, visited);
            visited[u] = true;

            // Update dist value of the adjacent nodes of the picked node
            for (Map.Entry<Integer, Integer> entry : graph.adj.get(u).entrySet()) {
                int v = entry.getKey();
                int weight = entry.getValue();
                if (!visited[v] && weight != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                }
            }
        }
        ArrayList<Integer> path = new ArrayList<>();
        int at = end;
        while (at != start) {
            path.add(at);
            at = parent[at];
        }
        path.add(start);
        return path;
    }

    public int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        // Find the node with minimum distance value
        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printSolution(int[] dist) {
        for (int i = 0; i < V; i++) {
            System.out.println(i + " " + dist[i]);
        }
    }

    public static void main(String[] args) {
        UCS ucs = new UCS(9);
        ucs.graph.addEdge(0, 1, 4);
        ucs.graph.addEdge(0, 7, 8);
        ucs.graph.addEdge(1, 2, 8);
        ucs.graph.addEdge(1, 7, 11);
        ucs.graph.addEdge(2, 3, 7);
        ucs.graph.addEdge(2, 8, 2);
        ucs.graph.addEdge(2, 5, 4);
        ucs.graph.addEdge(3, 4, 9);
        ucs.graph.addEdge(3, 5, 14);
        ucs.graph.addEdge(4, 5, 10);

        ucs.ucs(0);

//        0 0
//        1 4
//        2 12
//        3 19
//        4 26
//        5 16
//        6 2147483647
//        7 8
//        8 14

        ArrayList<Integer> path = ucs.ucsSolve(0, 4);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        // 0 1 2 5 4
    }

}
