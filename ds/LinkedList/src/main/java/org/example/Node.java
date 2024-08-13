package LingedList.src.main.java.org.example;

public class Node {
    private int value;
//    private Node prev;
    private Node next;

    public Node(int data) {
        value = data;
//        prev = null;
        next = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

//    public void setPrev(Node prev) {
//        this.prev = prev;
//    }
//
//    public Node getPrev() {
//        return prev;
//    }

    public int getValue() {
        return value;
    }
}
