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
		
		assertEquals(null, 'M');
	}
	
	@Test
	void testGet_present()
	{
		ParallelArrayDictionary<Character, Integer> arr = new ParallelArrayDictionary<Character, Integer>();
		arr.put('A', 4);
		arr.put('Z', 20);
		
		assertEquals(20, 'Z');
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
		
		arr.remove('Z');
		
		assertEquals(null, arr.remove('Z'));
		assertTrue(arr.containsKey('A'));
		assertFalse(arr.containsKey('Z'));
		assertTrue(arr.containsValue(4));
		assertFalse(arr.containsValue(20));
	}

	@Test
	void testPutAll()
	{
		
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