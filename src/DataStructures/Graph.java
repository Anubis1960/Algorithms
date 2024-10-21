package DataStructures;

import java.util.ArrayList;

public class Graph {

    // Adjacency matrix representation of graph
    public ArrayList<ArrayList<Integer>> adj;


    public Graph(int V) {
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Function to add an edge into the graph
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Function to remove an edge from the graph
    public void removeEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        for (int i = 0; i < adj.get(u).size(); i++) {
            if (adj.get(u).get(i) == v) {
                adj.get(u).remove(i);
            }
        }
        for (int i = 0; i < adj.get(v).size(); i++) {
            if (adj.get(v).get(i) == u) {
                adj.get(v).remove(i);
            }
        }
    }

    public void printGraph(ArrayList<ArrayList<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex " + i);
            System.out.print("head");
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(" -> " + adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);

        graph.printGraph(graph.adj);

//        Adjacency list of vertex 0
//        head -> 1 -> 2
//
//        Adjacency list of vertex 1
//        head -> 0 -> 2 -> 4
//
//        Adjacency list of vertex 2
//        head -> 0 -> 1 -> 3
//
//        Adjacency list of vertex 3
//        head -> 2
//
//        Adjacency list of vertex 4
//        head -> 1
    }
}

