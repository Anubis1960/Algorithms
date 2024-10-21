package DataStructures;

import java.util.ArrayList;

public class DirectedGraph {
    public ArrayList<ArrayList<Integer>> adj;

    public DirectedGraph(int V) {
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public void removeEdge(int u, int v) {
        for (int i = 0; i < adj.get(u).size(); i++) {
            if (adj.get(u).get(i) == v) {
                adj.get(u).remove(i);
            }
        }
    }

    public void printGraph() {
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
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        graph.addEdge(4, 1);

        graph.printGraph();

//        Adjacency list of vertex 0
//        head -> 1 -> 2
//
//        Adjacency list of vertex 1
//        head -> 2
//
//        Adjacency list of vertex 2
//        head -> 0 -> 3
//
//        Adjacency list of vertex 3
//        head -> 3
//
//        Adjacency list of vertex 4
//        head -> 1

    }

}
