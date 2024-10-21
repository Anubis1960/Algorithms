package DataStructures;

public class DoubleLinkedList {

    public Double_Node<Integer> head;
    public Double_Node<Integer> tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insert(int data) {
        if (head == null) {
            head = new Double_Node(data);
            tail = head;
        } else {
            Double_Node<Integer> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Double_Node(data);
            temp.next.prev = temp;
        }
    }

    public Double_Node delete(int data) {
        if (head == null) {
            return null;
        }
        if (head.data == data) {
            head = head.next;
            head.prev = null;
            return head;
        }
        Double_Node<Integer> temp = head;
        while (temp.next != null) {
            if (temp.next.data == data) {
                Double_Node deleted = temp.next;
                temp.next = temp.next.next;
                if (temp.next != null) {
                    temp.next.prev = temp;
                }
                deleted.next = null;
                deleted.prev = null;
                return deleted;
            }
            temp = temp.next;
        }
        return null;
    }

    public void printList() {
        Double_Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
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
