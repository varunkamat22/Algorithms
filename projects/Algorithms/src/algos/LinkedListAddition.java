package algos;

public class LinkedListAddition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListNode n1 = new LinkedListNode(1);
		n1.next = new LinkedListNode(0);
		n1.next.next = new LinkedListNode(9);

		LinkedListNode n2 = new LinkedListNode(9);
		n2.next = new LinkedListNode(7);
		n2.next.next = new LinkedListNode(3);
		
		LinkedListNode result = addLists(n1, n2);
		while(result != null){
			System.out.println(result.data);
			result = result.next;
		}
	}
	
	
	private static int add(LinkedListNode n1, LinkedListNode n2, LinkedListNode current){
		if(n1 == null && n2 == null)
			return 0;
		current.next = new LinkedListNode();
		int addData = n1.data+n2.data+add(n1.next,n2.next,current.next);
		current.next.data = addData%10;
		return addData > 9 ? 1 : 0;
	}
	
	private static LinkedListNode addLists(LinkedListNode n1, LinkedListNode n2){
		LinkedListNode head = new LinkedListNode();
		int lastCarry = add(n1,n2,head);
		head.data = lastCarry;
		return head;
	}
}
