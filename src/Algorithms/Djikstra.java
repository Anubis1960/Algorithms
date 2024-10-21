package Algorithms;

import DataStructures.WeightedGraph;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class Djikstra {
    class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public WeightedGraph graph;
    public int V;
    // Priority queue to store nodes that are being preprocessed
    public PriorityQueue<Node> pq;
    // Array to store the shortest distance from source to each node
    int[] dist;
    // Array to store the visited nodes
    boolean[] visited;

    public Djikstra(int V) {
        this.V = V;
        graph = new WeightedGraph(V);
        pq = new PriorityQueue<>(V, (a, b) -> a.distance - b.distance);
        dist = new int[V];
        // Initialize all distances as INFINITE and visited[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[V];
    }

    public void djikstra(int s) {
        // Initialize the distance to the source node as 0
        dist[s] = 0;
        // Add the source node to the priority queue
        pq.add(new Node(s, 0));

        // While the priority queue is not empty
        while (!pq.isEmpty()) {
            // Extract the min distance node from the priority queue
            int u = pq.poll().vertex;

            // If the node is already visited, then skip
            if (visited[u]) {
                continue;
            }

            // Mark the node as visited
            visited[u] = true;

            // Update the distance value of the adjacent nodes of the picked node
            for (Map.Entry<Integer, Integer> entry : graph.adj.get(u).entrySet()) {

                int weight = entry.getValue();

                int v = entry.getKey();

                //Check if the new distance is smaller than the current distance and the node is not visited and the weight is not 0
                if (!visited[entry.getKey()] && weight != 0 && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.println("Shortest distance from " + s + " to " + i + " is " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Djikstra djikstra = new Djikstra(9);
        djikstra.graph.addEdge(0, 1, 4);
        djikstra.graph.addEdge(0, 7, 8);
        djikstra.graph.addEdge(1, 2, 8);
        djikstra.graph.addEdge(1, 7, 11);
        djikstra.graph.addEdge(2, 3, 7);
        djikstra.graph.addEdge(2, 8, 2);
        djikstra.graph.addEdge(2, 5, 4);
        djikstra.graph.addEdge(3, 4, 9);
        djikstra.graph.addEdge(3, 5, 14);
        djikstra.graph.addEdge(4, 5, 10);
        djikstra.graph.addEdge(7, 8, 7);

        djikstra.djikstra(0);

//        Shortest distance from 0 to 0 is 0
//        Shortest distance from 0 to 1 is 4
//        Shortest distance from 0 to 2 is 12
//        Shortest distance from 0 to 3 is 19
//        Shortest distance from 0 to 4 is 26
//        Shortest distance from 0 to 5 is 16
//        Shortest distance from 0 to 6 is 2147483647
//        Shortest distance from 0 to 7 is 8
//        Shortest distance from 0 to 8 is 14
    }
}
