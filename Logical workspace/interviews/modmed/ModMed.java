package interviews.modmed;

class Node {
    int data;
    Node next;

    Node() {

    }

    Node(int data) {
        this.data = data;
        next = null;
    }
}

class MyList {
    Node head;
    Node tail;

    MyList()
    {
        head = null;
        tail = null;
    }

    public void add(int data) {
        Node curNode = new Node(data);
        if (head == null) {
            head = curNode;
            tail = curNode;
        }
        else {
            tail.next = curNode;
            tail = curNode;
        }
    }

    public void printList() {
        Node node = head;

        while(node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

}

public class ModMed {

    public static void main(String[] args) {
        MyList list1 = new MyList();
        list1.add(9);
        list1.add(6);

        MyList reverse1 = new MyList();
        reverse(list1.head, reverse1);

        MyList list2 = new MyList();
        list2.add(1);
        list2.add(7);
        list2.add(6);

        MyList reverse2 = new MyList();
        reverse(list2.head, reverse2);

        MyList list3 = new MyList();

        addition(reverse1.head, reverse2.head, list3, false);

        MyList result = new MyList();
        reverse(list3.head, result);

        result.printList();
    }

    private static void reverse(Node node, MyList reverse) {
        if(node == null) {
            return;
        }

        reverse(node.next, reverse);
        reverse.add(node.data);

    }

    private static void addition(Node node1, Node node2, MyList resultList, boolean bCarry) {

        if (node1 == null && node2 == null) {
            if (bCarry) {
                resultList.add(1);
            }
            return;
        }

        int sum = 0;
        if(node1 != null) {
            sum = node1.data;
        }

        if(node2 != null) {
            sum += node2.data;
        }

        if (bCarry) {
            sum++;
            bCarry = false;
        }

        if (sum > 10) {
            bCarry = true;
            sum -= 10;
        }

        resultList.add(sum);

        addition(node1 == null ? node1 : node1.next,
                node2 == null ? node2 : node2.next,
                resultList, bCarry);

    }
}
