package Algorithms;

import DataStructures.Graph;
import DataStructures.Queue;

import java.util.ArrayList;
import java.util.Arrays;

public class BFS {

    public Graph graph;
    public int V;

    public BFS(int V) {
        this.V = V;
        graph = new Graph(V);
    }

    // Function to print BFS traversal from a given source s
    public void bfs(int s) {
        // Mark all the nodes as not visited
        boolean[] visited = new boolean[V];

        Queue queue = new Queue();
        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.enqueue(s);

        while (queue.head != null) {
            // Dequeue a node from queue
            s = queue.deque().data;
            System.out.print(s + " ");

            // Get all adjacent nodes of the dequeued node s
            for (int i = 0; i < graph.adj.get(s).size(); i++) {
                int n = graph.adj.get(s).get(i);
                if (!visited[n]) {
                    visited[n] = true;
                    queue.enqueue(n);
                }
            }
        }
    }


    // Function to find the shortest path between two nodes of a graph using BFS
    public ArrayList<Integer> bfsSolve(int start, int end){

        // Mark all the nodes as not visited
        boolean[] visited = new boolean[V];

        // Array to store the parent of each node
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Queue queue = new Queue();
        visited[start] = true;
        queue.enqueue(start);

        // If we reached the end node, we build the path from the parent array
        ArrayList<Integer> path = new ArrayList<>();

        while (queue.head != null) {
            int s = queue.deque().data;
            if (s == end) {
                break;
            }
            // Get all adjacent nodes of the dequeued node s
            for (int i = 0; i < graph.adj.get(s).size(); i++) {
                int n = graph.adj.get(s).get(i);
                if (!visited[n]) {
                    visited[n] = true;
                    parent[n] = s;
                    queue.enqueue(n);
                }
            }
        }


        for (int at = end; at != -1; at = parent[at]) {
            path.add(at);
        }
        if (path.get(path.size() - 1) == start) {
            return path;
        }
        path.clear();
        return path;
    }

    public static void main(String[] args) {
        BFS bfs = new BFS(5);
        bfs.graph.addEdge(0, 1);
        bfs.graph.addEdge(0, 2);
        bfs.graph.addEdge(1, 2);
        bfs.graph.addEdge(2, 0);
        bfs.graph.addEdge(2, 3);
        bfs.graph.addEdge(3, 3);
        bfs.graph.addEdge(4, 1);

        System.out.println("Following is Breadth First Traversal " + "(starting from node 2)");
        bfs.bfs(2); // 2 0 3 1 4

        System.out.println();
        System.out.println("Shortest path from 0 to 3: " + bfs.bfsSolve(0, 3)); // [3, 2, 0]
    }

}
