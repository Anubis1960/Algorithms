package Algorithms;
import DataStructures.Graph;

import java.util.ArrayList;

public class DLS {
    public Graph graph;
    public int V;
    public int limit;

    public DLS(int V, int limit) {
        this.V = V;
        this.limit = limit;
        graph = new Graph(V);
    }

    public void dls(int s) {
        boolean[] visited = new boolean[V];
        dlsUtil(s, visited, limit);
    }

    public void dlsUtil(int s, boolean[] visited, int limit) {
        if (limit <= 0) {
            return;
        }
        visited[s] = true;
        System.out.print(s + " ");
        for (int i = 0; i < graph.adj.get(s).size(); i++) {
            int n = graph.adj.get(s).get(i);
            if (!visited[n]) {
                dlsUtil(n, visited, limit - 1);
            }
        }
    }

    public ArrayList<Integer> dlsSolve(int start, int end){
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        ArrayList<Integer> path = new ArrayList<>();
        return dlsUtilSolve(start, end, visited, parent, path, limit);
    }

    public ArrayList<Integer> dlsUtilSolve(int s, int end, boolean[] visited, int[] parent, ArrayList<Integer> path, int limit) {
        if (limit <= 0) {
            return null;
        }
        visited[s] = true;
        path.add(s);
        if (s == end) {
            return path;
        }
        for (int i = 0; i < graph.adj.get(s).size(); i++) {
            int n = graph.adj.get(s).get(i);
            if (!visited[n]) {
                parent[n] = s;
                ArrayList<Integer> result = dlsUtilSolve(n, end, visited, parent, path, limit - 1);
                if (result != null) {
                    return result;
                }
            }
        }
        path.remove(path.size() - 1);
        return null;
    }

    public static void main(String[] args) {
        DLS dls = new DLS(7, 3);
        dls.graph.addEdge(0, 1);
        dls.graph.addEdge(0, 2);
        dls.graph.addEdge(1, 3);
        dls.graph.addEdge(1, 4);
        dls.graph.addEdge(2, 5);
        dls.graph.addEdge(2, 6);
        dls.dls(0); // 0 1 3 4 2 5 6

        System.out.println();

        ArrayList<Integer> path = dls.dlsSolve(0, 6);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        // 6 2 0
    }

}
