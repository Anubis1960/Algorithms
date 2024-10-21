package DataStructures;

class RingBuffer{
    private int[] buffer; // storing buffer
    private int capacity; // maximum capacity
    private int head; // pointer
    private int tail; // pointer

    public RingBuffer(int capacity){
        this.capacity = capacity;
        this.buffer = new int[capacity];
        //head and tail start at the same index
        this.head = capacity/2;
        this.tail = capacity/2;
    }

    public void push_back(int value){
        //check if by inserting an element in the back we overwrite the head
        if((tail+1)%capacity == head){
            System.out.println("Buffer is full");
            resize(capacity*2);
        }
        buffer[tail] = value;
        tail = (tail+1)%capacity;
    }

    public void push_front(int val){
        //check if by inserting an element in the front we overwrite the tail
        if((head-1)%capacity == tail){
            System.out.println("Buffer is full");
            resize(capacity*2);
        }
        head = (head-1)%capacity;
        if (head < 0){
            head = capacity - 1;
        }
        buffer[head] = val;
    }

    public int pop_back(){
        if(tail == head){
            System.out.println("Buffer is empty");
            resize(capacity*2);
        }
        tail = (tail-1)%capacity;
        if (tail < 0){
            tail = capacity - 1;
        }
        return buffer[tail];
    }

    public int pop_front(){
        if(tail == head){
            System.out.println("Buffer is empty");
            resize(capacity*2);
        }
        int val = buffer[head];
        head = (head+1)%capacity;
        return val;
    }

    public void resize(int new_capacity){
        int[] new_buffer = new int[new_capacity];
        int new_head = new_capacity/2;
        int new_tail = new_capacity/2;
        int i = head;
        while(i != tail){
            new_buffer[new_tail] = buffer[i];
            i = (i+1)%capacity;
            new_tail = (new_tail+1)%new_capacity;
        }
        buffer = new_buffer;
        head = new_head;
        tail = new_tail;
        capacity = new_capacity;
    }

    public void print(){
        int i = head;
        while(i != tail){
            System.out.print(buffer[i] + " ");
            i = (i+1)%capacity;
        }
        System.out.println();
    }

    public static void main(String[] args){
        RingBuffer rb = new RingBuffer(6);
        rb.push_back(1);
        rb.push_back(2);
        rb.push_back(3);
        rb.push_back(4);
        rb.push_back(5);
        rb.print(); // 1 2 3 4 5
        rb.push_back(6); // Buffer is full
        rb.print(); // 1 2 3 4 5 6
        rb.pop_front();
        rb.print(); // 2 3 4 5 6
        rb.push_front(7);
        rb.print(); // 7 2 3 4 5 6
        rb.pop_back();
        rb.print(); // 7 2 3 4 5
    }
}
