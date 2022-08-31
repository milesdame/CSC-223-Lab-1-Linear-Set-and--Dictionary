package utilities;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

class ParallelArrayDictionaryTest
{
	/*
	 * make it and check that an empty ArraySet and an empty ArrayList are created
	 */
	@Test
	void testParallelArrayDictionary()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		assertTrue(arr._keys instanceof ArraySet);
		assertTrue(arr._values instanceof ArrayList);
		assertEquals(0, arr.size());
	}

	/*
	 * key not present
	 * key is present
	 */
	@Test
	void testGet_notpresent()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		assertEquals(null, arr.get('M'));
	}
	
	@Test
	void testGet_present()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		assertEquals((Integer)20, arr.get('Z'));
	}

	/*
	 * key not yet in
	 * key already in, new value
	 */
	@Test
	void testPut_notin()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		assertTrue(arr.containsKey('A'));
		assertTrue(arr.containsValue(4));
	}
	
	@Test
	void testPut_keyin()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		
		arr.put('A',  100);
		assertTrue(arr.containsKey('A'));
		assertFalse(arr.containsValue(4));
		assertTrue(arr.containsValue(100));
	}

	/*
	 * key not in
	 * key is in
	 * try to remove one thing twice
	 */
	@Test
	void testRemove_notin()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		assertEquals(null, arr.remove('C'));
		assertTrue(arr.containsKey('A'));
		assertTrue(arr.containsKey('Z'));
		assertTrue(arr.containsValue(4));
		assertTrue(arr.containsValue(20));
	}
	
	@Test
	void testRemove_in()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		assertEquals((Integer) 20, arr.remove('Z'));
		assertTrue(arr.containsKey('A'));
		assertFalse(arr.containsKey('Z'));
		assertTrue(arr.containsValue(4));
		assertFalse(arr.containsValue(20));
	}
	
	@Test
	void testRemove_twice()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		for (Character key : arr.keySet()) {
			System.out.println(key);
		}
		for (Integer value : arr.values()) {
			System.out.println(value);
		}
		arr.remove('Z');
		
		assertEquals(null, arr.remove('Z'));
		assertTrue(arr.containsKey('A'));
		assertFalse(arr.containsKey('Z'));
		assertTrue(arr.containsValue(4));
		assertFalse(arr.containsValue(20));
	}

	/**
	 * empty
	 * no overlap
	 * some overlap
	 * full overlap
	 * same keys, different values
	 */
	@Test
	void testPutAll_empty()
	{
		ParallelArrayDictionary<Character, Integer> arr1 = new ParallelArrayDictionary<Character, Integer>();
		arr1.put('A', 1);
		arr1.put('B', 2);
		
		ParallelArrayDictionary<Character, Integer> arr2 = new ParallelArrayDictionary<Character, Integer>();
		
		arr1.putAll(arr2);
		
		assertEquals(arr1.size(), 2);
		assertTrue(arr1.containsKey('A'));
		assertTrue(arr1.containsKey('B'));
		assertTrue(arr1.containsValue(1));
		assertTrue(arr1.containsValue(2));
	}
	
	@Test
	void testPutAll_noOverlap()
	{
		ParallelArrayDictionary<Character, Integer> arr1 = new ParallelArrayDictionary<Character, Integer>();
		arr1.put('A', 1);
		arr1.put('B', 2);
		
		ParallelArrayDictionary<Character, Integer> arr2 = new ParallelArrayDictionary<Character, Integer>();
		arr2.put('C', 3);
		arr2.put('D', 4);
		
		arr1.putAll(arr2);
		
		assertEquals(arr1.size(), 4);
		assertTrue(arr1.containsKey('A'));
		assertTrue(arr1.containsKey('B'));
		assertTrue(arr1.containsKey('C'));
		assertTrue(arr1.containsKey('D'));
		assertTrue(arr1.containsValue(1));
		assertTrue(arr1.containsValue(2));
		assertTrue(arr1.containsValue(3));
		assertTrue(arr1.containsValue(4));
	}
	
	@Test
	void testPutAll_someOverlap()
	{
		ParallelArrayDictionary<Character, Integer> arr1 = new ParallelArrayDictionary<Character, Integer>();
		arr1.put('A', 1);
		arr1.put('B', 2);
		
		ParallelArrayDictionary<Character, Integer> arr2 = new ParallelArrayDictionary<Character, Integer>();
		arr2.put('A', 1);
		arr2.put('D', 4);
		
		arr1.putAll(arr2);
		
		assertEquals(arr1.size(), 3);
		assertTrue(arr1.containsKey('A'));
		assertTrue(arr1.containsKey('B'));
		assertTrue(arr1.containsKey('D'));
		assertTrue(arr1.containsValue(1));
		assertTrue(arr1.containsValue(2));
		assertTrue(arr1.containsValue(4));
	}
	
	@Test 
	void testPutAll_fullOverlap()
	{
		ParallelArrayDictionary<Character, Integer> arr1 = new ParallelArrayDictionary<Character, Integer>();
		arr1.put('A', 1);
		arr1.put('B', 2);
		
		ParallelArrayDictionary<Character, Integer> arr2 = new ParallelArrayDictionary<Character, Integer>();
		arr2.put('A', 1);
		arr2.put('B', 2);
		
		arr1.putAll(arr2);
		
		assertEquals(arr1.size(), 2);
		assertTrue(arr1.containsKey('A'));
		assertTrue(arr1.containsKey('B'));
		assertTrue(arr1.containsValue(1));
		assertTrue(arr1.containsValue(2));
	}
	
	@Test
	void testPutAll_sameKeysDiffValues()
	{
		ParallelArrayDictionary<Character, Integer> arr1 = new ParallelArrayDictionary<Character, Integer>();
		arr1.put('A', 1);
		arr1.put('B', 2);
		
		ParallelArrayDictionary<Character, Integer> arr2 = new ParallelArrayDictionary<Character, Integer>();
		arr2.put('A', 3);
		arr2.put('B', 4);
		
		arr1.putAll(arr2);
		
		assertEquals(arr1.size(), 2);
		assertTrue(arr1.containsKey('A'));
		assertTrue(arr1.containsKey('B'));
		assertTrue(arr1.get('A') == 3);
		assertTrue(arr1.get('A') == 4);
	}

	/*
	 * from empty
	 * one in
	 * multiple in
	 */
	@Test
	void testClear_fromempty()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		
		arr.clear();
		assertEquals(0, arr.size());
	}
	
	@Test
	void testClear_one()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		
		arr.clear();
		assertEquals(0, arr.size());
	}
	
	@Test
	void testClear_multiple()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		arr.clear();
		assertEquals(0, arr.size());
	}
}