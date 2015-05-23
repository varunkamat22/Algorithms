package datastructures.test;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructures.CHashMap;

public class CHashMapTest {

	@Test
	public void testInsert() {
		CHashMap<String, String> map = new CHashMap<>(5);
		map.put("name","varun");
		assertEquals("Key correctly inserted into CHashMap",1, map.size());
	}
	
	@Test
	public void testRetrieval() {
		CHashMap<String, String> map = new CHashMap<>(5);
		map.put("name","varun");
		assertEquals("Value correctly retrieved","varun", map.get("name"));
	}
	
	@Test
	public void testDuplicateInsert() {
		CHashMap<String, String> map = new CHashMap<>(5);
		map.put("name","varun");
		map.put("name","varun");
		assertEquals("Key correctly inserted into CHashMap",1, map.size());
	}

	@Test
	public void testCollision() {
		CHashMap<String, String> map = new CHashMap<>(5);
		map.put("name","varun");
		map.put("test3","kamat");
		assertEquals("Value correctly retrieved","varun", map.get("name"));
		assertEquals("Value correctly retrieved","kamat", map.get("test3"));
	}

	
}
