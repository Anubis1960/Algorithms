package DataStructures;

public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        System.out.println(node.data); // 1
    }

}
