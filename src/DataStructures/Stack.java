package DataStructures;

public class Stack {
    public Node top;

    public Stack() {
        this.top = null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public Node pop() {
        if (top == null) {
            return null;
        }
        Node temp = top;
        top = top.next;
        return temp;
    }

    public void printStack() {
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.printStack(); // 5 4 3 2 1
        System.out.println();
        stack.pop();
        stack.printStack(); // 4 3 2 1
    }
}
