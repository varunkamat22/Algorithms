package datastructures.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClinkedListTest {

	@Test
	public void testAdd() {
		CLinkedList<String> cList = new CLinkedList<>();
		cList.add("Hi");
		assertEquals(1, cList.size());
	}

	@Test
	public void testDelete() {
		CLinkedList<String> cList = new CLinkedList<>();
		cList.add("Hello");
		cList.add("Hi");
		assertEquals(2, cList.size());
		assertEquals(true, cList.delete("Hi"));
	}
	
	@Test
	public void testSearch() {
		CLinkedList<String> cList = new CLinkedList<>();
		cList.add("Hi");
		cList.add("Hello");
		cList.add("How");
		cList.add("Are");
		cList.add("You");
		assertEquals("You", cList.search("You"));
	}
	
}
