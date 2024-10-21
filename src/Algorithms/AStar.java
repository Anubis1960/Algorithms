package Algorithms;

import java.util.*;

// Node class representing each position in the grid
class Node {
    public int row, col;
    public double gCost, hCost;
    public Node parent;
    public boolean isObstacle;

    public Node(int row, int col, boolean isObstacle) {
        this.row = row;
        this.col = col;
        this.gCost = Double.MAX_VALUE;
        this.hCost = Double.MAX_VALUE;
        this.parent = null;
        this.isObstacle = isObstacle;
    }

    public double getFCost() {
        return gCost + hCost;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    public boolean equals(Node other) {
        return this.row == other.row && this.col == other.col;
    }

    public int hashCode() {
        return Objects.hash(row, col);
    }
}

public class AStar {


    private static final int[] ROW_DIRECTIONS = {-1, 0, 1, 0};  // up, right, down, left
    private static final int[] COL_DIRECTIONS = {0, 1, 0, -1};

    // Function to check if a given position is valid
    public static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static ArrayList<Node> aStar(int[][] grid, int startRow, int startCol, int endRow, int endCol) {
        int rows = grid.length;
        int cols = grid[0].length;
        Node[][] nodes = new Node[rows][cols];

        // Create nodes for each position in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nodes[i][j] = new Node(i, j, grid[i][j] == -1);
            }
        }

        Node startNode = nodes[startRow][startCol];
        Node endNode = nodes[endRow][endCol];

        startNode.gCost = 0;
        // Calculate the heuristic cost using Manhattan distance
        startNode.hCost = Math.abs(startNode.row - endNode.row) + Math.abs(startNode.col - endNode.col);
        startNode.parent = null;

        // Priority queue to store nodes based on their fCost
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(Node::getFCost));
        // Set to store visited nodes
        Set<Node> closedSet = new HashSet<>();
        openSet.add(startNode);

        // A* algorithm
        while (!openSet.isEmpty()) {
            // Get the node with the lowest fCost
            Node currentNode = openSet.poll();
            closedSet.add(currentNode);

            // If we reached the end node, we build the path from the parent pointers
            if (currentNode == endNode) {
                ArrayList<Node> path = new ArrayList<>();
                Node current = currentNode;
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                return path;
            }

            // Check all neighbors of the current node
            for (int i = 0; i < 4; i++) {
                int newRow = currentNode.row + ROW_DIRECTIONS[i];
                int newCol = currentNode.col + COL_DIRECTIONS[i];

                // Skip if the neighbor is invalid or an obstacle
                if (!isValid(newRow, newCol, rows, cols) || nodes[newRow][newCol].isObstacle) {
                    continue;
                }

                Node neighbor = nodes[newRow][newCol];
                if (closedSet.contains(neighbor)) {
                    continue;
                }


                double newGCost = currentNode.gCost + 1; // Cost to move to the neighbor is always 1 in a matrix

                // If the new path to the neighbor is shorter or the neighbor is not in the open set
                if (newGCost < neighbor.gCost || !openSet.contains(neighbor)) {
                    neighbor.gCost = newGCost;
                    neighbor.hCost = Math.abs(neighbor.row - endNode.row) + Math.abs(neighbor.col - endNode.col);
                    neighbor.parent = currentNode;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    public static void drawPath(int[][] grid, ArrayList<Node> path) {
        for (Node node : path) {
            grid[node.row][node.col] = 33;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 2, 3, 3},
                {5, 10, 1},
                {10, 30, 5}
        };
        int startRow = 0, startCol = 0;
        int endRow = 2, endCol = 2;
        ArrayList<Node> path = aStar(grid, startRow, startCol, endRow, endCol);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        // (0, 0) (1, 0) (2, 0) (2, 1) (2, 2)

        System.out.println();

        drawPath(grid, path);
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
//        33 33 3
//        5 33 1
//        10 33 33
    }
}
