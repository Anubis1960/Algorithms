package DataStructures;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Get the index of the parent of a node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Get the index of the left child of a node
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Get the index of the right child of a node
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Ensure that the heap property is maintained after insertion (heapify up)
    private void heapifyUp(int i) {
        while (i != 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Ensure that the heap property is maintained after removal (heapify down)
    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // Insert a new element into the heap
    public void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }

        heap[size] = key;
        size++;
        heapifyUp(size - 1);
    }

    // Extract the minimum element (root) from the heap
    public int extractMin() {
        if (size <= 0) {
            System.out.println("Heap is empty");
            return Integer.MAX_VALUE;
        }
        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    // Utility function to print the heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Driver code to test the Min Heap implementation
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(15);
        minHeap.insert(5);
        minHeap.insert(4);
        minHeap.insert(45);

        System.out.println("Heap array:");
        minHeap.printHeap(); // 2 4 3 5 15 45


        System.out.println("Extracted min: " + minHeap.extractMin()); // 2
        System.out.println("Heap array after extraction:");
        minHeap.printHeap(); // 3 4 45 5 15
    }
}
