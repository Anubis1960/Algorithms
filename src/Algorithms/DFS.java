package Algorithms;
import DataStructures.Graph;

import java.util.ArrayList;

public class DFS {

    public Graph graph;
    public int V;

    public DFS(int V) {
        this.V = V;
        graph = new Graph(V);
    }

    public void dfs(int s) {
        boolean[] visited = new boolean[V];
        dfsUtil(s, visited);
    }

    // Function to print DFS traversal from a given source s
    public void dfsUtil(int s, boolean[] visited) {
        visited[s] = true;
        System.out.print(s + " ");
        // Recur for all the nodes adjacent to this node
        for (int i = 0; i < graph.adj.get(s).size(); i++) {
            // Get all adjacent nodes of the dequeued node s
            int n = graph.adj.get(s).get(i);
            if (!visited[n]) {
                dfsUtil(n, visited);
            }
        }
    }

    public ArrayList<Integer> dfsSolve(int start, int end){
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        ArrayList<Integer> path = new ArrayList<>();
        return dfsUtilSolve(start, end, visited, parent, path);
    }

    public ArrayList<Integer> dfsUtilSolve(int s, int end, boolean[] visited, int[] parent, ArrayList<Integer> path) {
        visited[s] = true;
        path.add(s);
        if (s == end) {
            return path;
        }
        for (int i = 0; i < graph.adj.get(s).size(); i++) {
            int n = graph.adj.get(s).get(i);
            if (!visited[n]) {
                parent[n] = s;
                ArrayList<Integer> result = dfsUtilSolve(n, end, visited, parent, path);
                if (result != null) {
                    return result;
                }
            }
        }
        path.remove(path.size() - 1);
        return null;
    }

    public static void main(String[] args) {
        DFS dfs = new DFS(5);
        dfs.graph.addEdge(0, 1);
        dfs.graph.addEdge(0, 2);
        dfs.graph.addEdge(2, 0);
        dfs.graph.addEdge(2, 3);
        dfs.graph.addEdge(3, 3);
        dfs.graph.addEdge(4, 1);

        System.out.println("Following is Depth First Traversal " + "(starting from node 2)");
        dfs.dfs(2); // 2 0 1 3 4

        System.out.println();
        ArrayList<Integer> path = dfs.dfsSolve(2, 3);
        if (path != null) {
            System.out.println("Path from 2 to 3: " + path); // [2, 3]
        } else {
            System.out.println("Path from 2 to 3 does not exist");
        }
    }
}
