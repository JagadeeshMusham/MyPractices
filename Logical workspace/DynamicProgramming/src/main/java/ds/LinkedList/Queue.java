package DynamicProgramming.src.main.java.ds.LinkedList;

public class Queue {
    private Node first;
    private Node last;

    public Queue() {
        first = null;
        last = null;
    }

    public void addNode(int data) {
        Node node = new Node(data);
        node.setNext(null);

        if (first == null) {
            first = last = node;
//            node.setPrev(null);
        }
        else {
//            node.setPrev(last);
            last.setNext(node);
        }

        last = node;
    }

    public void deleteNode() {
        if (first == null) {
            System.out.println("The queue is underflow");
        } else {
            Node deleteNode = first;
            first = first.getNext();
            deleteNode.setNext(null);

            System.out.println("The deleted node value is: " + deleteNode.getValue());
        }
    }

    public void displayQueue(boolean bReverse) {
        Node current = first;
        System.out.println("The queue values are: ");
        display(current, bReverse);
        System.out.println(" ");
    }

    private void display(Node current, boolean bReverse) {
        if (current == null) {
            return;
        }

        if (!bReverse) {
            System.out.print(current.getValue() + "\t");
        }

        display(current.getNext(), bReverse);

        if (bReverse) {
            System.out.print(current.getValue() + "\t");
        }
    }
}
