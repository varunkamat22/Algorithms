package datastructures.test;

public class CLinkedList<E>{
	private LLNode<E> head;
	private LLNode<E> tail;
	private int currentSize;
	
	public int size(){
		return this.currentSize;
	}
	
	public void add(E e){
		if(head == null){
			LLNode<E> newNode = new LLNode<E>(e, null, null);
			head = newNode;
			tail = newNode;
		}else{
			LLNode<E> newNode = new LLNode<E>(e, tail, null);
			tail.setNext(newNode);
			tail = newNode;
		}
		currentSize++;
	}
	
	public E search(E e){
		if(head == null){
			return null;
		}else{
			LLNode<E> currentNode = head;
			while(currentNode != null){
				if(currentNode.e.equals(e)){
					return currentNode.e;
				}
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}
	
	public boolean delete(E e){
		if(head == null){
			return false;
		}else{
			LLNode<E> currentNode = head;
			while(currentNode != null){
				if(currentNode.e.equals(e)){
					if(currentNode.getPrev() != null){
						currentNode.getPrev().setNext(currentNode.getNext());
					}
					if(currentNode.getNext() != null){
						currentNode.getNext().setPrev(currentNode.getPrev());
					}
					if(currentNode == head){
						head = currentNode.getNext();
					}
					if(currentNode == tail){
						tail = currentNode.getPrev();
					}
					currentSize--;
					return true;
				}
				currentNode = currentNode.getNext();
			}
		}
		return false;
	}
	
	private class LLNode<E>{
		private LLNode<E> prev;
		private LLNode<E> next;
		private E e;
		
		public LLNode(E e,LLNode<E> prev,LLNode<E> next){
			this.next = next;
			this.prev = prev;
			this.e = e;
		}

		public LLNode<E> getPrev() {
			return prev;
		}

		public void setPrev(LLNode<E> prev) {
			this.prev = prev;
		}

		public LLNode<E> getNext() {
			return next;
		}

		public void setNext(LLNode<E> next) {
			this.next = next;
		}

		public E getE() {
			return e;
		}

		public void setE(E e) {
			this.e = e;
		}
		
	}
}