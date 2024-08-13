package ds.list;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		SingleLinkedList sll = new SingleLinkedList(10);
		sll.addNode(20, 2);
		sll.displayNodes();

		sll.addNode(30, 2);
		sll.displayNodes();

		sll.addNode(40, 2);
		sll.displayNodes();

		sll.addNode(50, 2);
		sll.displayNodes();

		sll.Reverse(sll.getHead());
		sll.displayNodes();

		sll.addNode(30, 4);
		boolean hasCycle = sll.hasCycle(sll.getHead());
		System.out.println("\nCycle value: " + hasCycle);
	}
}
