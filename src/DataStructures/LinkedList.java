package DataStructures;

public class LinkedList {
    public Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insert(int data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    public Node delete(int data) {
        if (head == null) {
            return null;
        }
        if (head.data == data) {
            head = head.next;
            return null;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                Node deleted = temp.next;
                temp.next = temp.next.next;
                return deleted;
            }
            temp = temp.next;
        }
        return null;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.printList(); // 1 2 3 4 5
        System.out.println();
        list.delete(3);
        list.printList(); // 1 2 4 5
    }
}
