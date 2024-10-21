package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeightedGraph {
    public ArrayList<Map<Integer, Integer>> adj;
    public int size;

    public WeightedGraph(int size) {
        this.size = size;
        adj = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            adj.add(new HashMap<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adj.get(source).put(destination, weight);
        adj.get(destination).put(source, weight);
    }

    public void printGraph() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adj.get(i).get(j) != null) {
                    System.out.print(adj.get(i).get(j) + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public int countEdges() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adj.get(i).get(j) != null) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 1);
        graph.printGraph();
        // 0 1 1 0 0
        // 1 0 1 1 0
        // 1 1 0 0 1
        // 0 1 0 0 1
        // 0 0 1 1 0
    }
}
