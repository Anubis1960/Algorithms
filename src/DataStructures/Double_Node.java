package DataStructures;

public class Double_Node<T> {
    public T data;
    public Double_Node<T> next;
    public Double_Node<T> prev;

    public Double_Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
