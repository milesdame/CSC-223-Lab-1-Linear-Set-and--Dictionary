/**
 * Unit testing for ArraySet class.
 * 
 * @author: Hanna King
 * @date: 8/29
 */

package utilities;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class ArraySetTest
{
	/*
	 * empty collection
	 * stuff in collection
	 * wrong type 
	 */
	@Test
	void testArraySetCollectionOfE_empty()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArraySet<Integer> list = new ArraySet<Integer>(collection);
		
		assertEquals(list.size(), 0);
	}
	
	@Test
	void testArraySetCollectionofE_notempty()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);

		ArraySet<Integer> list = new ArraySet<Integer>(collection);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0), (Integer) 3);
	}
	
	@Test
	void testArraySetCollectionofE_difftypes()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		assertThrows(Exception.class, () -> {ArraySet<Integer> list = new ArraySet<String>(collection);});
	}

	/*
	 * Add to empty
	 * add non-redundant to not empty
	 * add redundant to not empty
	 * add wrong type
	 */
	@Test
	void testAddE_empty()
	{
		ArraySet<Integer> list = new ArraySet<Integer>();
		
		assertTrue(list.add(7));
		assertEquals(list.size(), 1);
		assertEquals(list.get(0), (Integer) 7);		
	}
	
	@Test
	void testAddE_nonredundant()
	{
		ArraySet<Integer> list = new ArraySet<Integer>();
		
		list.add(7);
		
		assertTrue(list.add(4));
		assertEquals(list.size(), 2);
		assertEquals(list.get(1), (Integer) 4);
	}
	
	@Test
	void testAddE_redundant()
	{
		ArraySet<Integer> list = new ArraySet<Integer>();
		
		list.add(7);
		
		list.add(4);
		
		assertTrue(list.add(7));
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), (Integer) 7);
		assertEquals(list.get(1), (Integer) 4);
	}
	
	@Test
	void testAddE_wrongtype()
	{
		ArraySet<Integer> list = new ArraySet<Integer>();
		
		assertThrows(Exception.class, () -> {list.add("string");});
	}

	/*
	 * empty list, empty collection
	 * empty list, non empty collection
	 * non empty both (no overlap)
	 * non empty list, empty collection
	 * non empty list, collection with redundant items
	 * non empty list, collection with overlap
	 */
	@Test
	void testAddAllCollectionOfQextendsE_emptylistemptycollection()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArraySet<Integer> list = new ArraySet<Integer>();
		
		assertFalse(list.addAll(collection));
	}
	
	@Test
	void testAddAllCollectionOfQextendsE_emptylistnoncollection()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArraySet<Integer> list = new ArraySet<Integer>();
		
		assertTrue(list.addAll(collection));
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
	}
	
	@Test
	void testAddAllCollectionOfQextendsE_nonlistemptycollection()
	{
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(5);

		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		ArrayList<Integer> collection = new ArrayList<Integer>();
		
		assertFalse(list.addAll(collection));
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
	}
	
	@Test
	void testAddAllCollectionOfQextendsE_nooverlap()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(0);
		toAdd.add(1);
		toAdd.add(2);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(collection));
		assertEquals(list.size(), 5);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
		assertEquals(list.get(2), (Integer) 0);
		assertEquals(list.get(3), (Integer) 1);
		assertEquals(list.get(4), (Integer) 2);
	}
	
	@Test
	void testAddAllCollectionOfQextendsE_doubleincollection()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(0);
		toAdd.add(1);
		toAdd.add(1);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(collection));
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
		assertEquals(list.get(2), (Integer) 0);
		assertEquals(list.get(3), (Integer) 1);
	}
	
	@Test
	void testAddAllCollectionOfQextendsE_overlap()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 9);
		assertEquals(list.get(2), (Integer) 5);
	}
	
	/* to a non-empty list: overlap, no-overlap, double in, empty collections at
	 * 	  start index
	 * 	  end index
	 *    any middle (normal) index
	 * index out of bounds
	 */
	@Test
	void testAddAllIntCollectionOfQextendsE_overlapstart()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(0, collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), (Integer) 5);
		assertEquals(list.get(1), (Integer) 3);
		assertEquals(list.get(2), (Integer) 9);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_nooverlapstart()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(0);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(0, collection));
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), (Integer) 0);
		assertEquals(list.get(1), (Integer) 5);
		assertEquals(list.get(2), (Integer) 3);
		assertEquals(list.get(3), (Integer) 9);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_doubleinstart()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(5);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(0, collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), (Integer) 5);
		assertEquals(list.get(1), (Integer) 3);
		assertEquals(list.get(2), (Integer) 9);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_emptystart()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertFalse(list.addAll(0, collection));
		assertEquals(list.size(), 2);
		assertEquals(list.get(1), (Integer) 3);
		assertEquals(list.get(2), (Integer) 9);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_overlapend()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(1, collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 9);
		assertEquals(list.get(2), (Integer) 5);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_nooverlapend()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(0);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(1, collection));
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 9);
		assertEquals(list.get(2), (Integer) 0);
		assertEquals(list.get(3), (Integer) 5);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_doubleinend()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(5);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(1, collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 9);
		assertEquals(list.get(2), (Integer) 5);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_emptyend()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertFalse(list.addAll(1, collection));
		assertEquals(list.size(), 2);
		assertEquals(list.get(1), (Integer) 3);
		assertEquals(list.get(2), (Integer) 9);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_overlapmiddle()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		toAdd.add(38);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(1, collection));
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
		assertEquals(list.get(2), (Integer) 9);
		assertEquals(list.get(3), (Integer) 38);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_nooverlapmiddle()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(0);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		toAdd.add(38);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(1, collection));
		assertEquals(list.size(), 5);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 0);
		assertEquals(list.get(2), (Integer) 5);
		assertEquals(list.get(3), (Integer) 9);
		assertEquals(list.get(4), (Integer) 38);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_doubleinmiddle()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(5);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		toAdd.add(38);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.addAll(1, collection));
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
		assertEquals(list.get(2), (Integer) 9);
		assertEquals(list.get(3), (Integer) 38);
	}
	
	@Test
	void testAddAllIntCollectionOfQextendsE_emptymiddle()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		toAdd.add(38);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertFalse(list.addAll(1, collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(1), (Integer) 3);
		assertEquals(list.get(2), (Integer) 9);
		assertEquals(list.get(3), (Integer) 38);
	}
	
	@Test
	void testAddAllIntCollectionOfQExtendsE_indexoutofbounds()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(5);
		collection.add(5);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(9);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertThrows(Exception.class, () -> {list.addAll(400, collection);});
	}
	
	/*
	 * empty list, empty collection
	 * empty list, stuff in collection
	 * empty collection, stuff in list
	 * stuff in list, stuff in collection, no overlap
	 * normal (stuff in list, stuff in collection, some overlap)
	 * full overlap
	 */
	@Test
	void testRetainAll_emptylistemptycollection()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArraySet<Integer> list = new ArraySet<Integer>();
		
		assertFalse(list.retainAll(collection));
	}
	
	@Test
	void testRetainAll_emptylistnoncollection()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(9);

		ArraySet<Integer> list = new ArraySet<Integer>();
		
		assertFalse(list.retainAll(collection));
	}
	
	@Test
	void testRetainAll_nonlistemptycollection()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(0);
		toAdd.add(1);
		toAdd.add(2);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.retainAll(collection));
		assertEquals(list.size(), 0);
	}
	
	@Test
	void testRetainAll_nooverlap()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(9);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(0);
		toAdd.add(1);
		toAdd.add(2);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.retainAll(collection));
		assertEquals(list.size(), 0);
	}
	
	@Test
	void testRetainAll_someoverlap()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(2);
		collection.add(9);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(0);
		toAdd.add(1);
		toAdd.add(2);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertTrue(list.retainAll(collection));
		assertEquals(list.size(), 1);
		assertEquals(list.get(0), (Integer) 2);
	}
	
	@Test
	void testRetainAll_fulloverlap()
	{
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(0);
		collection.add(1);

		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(0);
		toAdd.add(1);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		
		assertFalse(list.retainAll(collection));
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), (Integer) 0);
		assertEquals(list.get(1), (Integer) 1);
	}

	/*
	 * both empty
	 * list empty, collection not
	 * list has stuff, collection does not
	 * both full, with full overlap
	 * both full, with some overlap
	 * both full, with no overlap
	 * different types?
	 */
	@Test
	void testRemoveAll_bothempty()
	{
		ArraySet<Integer> list = new ArraySet<Integer>();
		ArrayList<Integer> collection = new ArrayList<Integer>();
		
		assertFalse(list.removeAll(collection));
	}
	
	@Test
	void testRemoveAll_listemptycollectionnot()
	{
		ArraySet<Integer> list = new ArraySet<Integer>();
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(3);
		
		assertFalse(list.removeAll(collection));
		assertEquals(list.size(), 0);
	}
	@Test
	void testRemoveAll_listnotcollectionempty()
	{
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(4);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		ArrayList<Integer> collection = new ArrayList<Integer>();
		
		assertFalse(list.removeAll(collection));
		assertEquals(list.size(), 2);
	}
	@Test
	void testRemoveAll_fulloverlap()
	{
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(4);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		
		assertTrue(list.removeAll(collection));
		assertEquals(list.size(), 1);
		assertEquals(list.get(0), (Integer) 3);
	}
	@Test
	void testRemoveAll_someoverlap()
	{
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(4);
		toAdd.add(5);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(8);
		
		assertTrue(list.removeAll(collection));
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 5);
	}
	@Test
	void testRemoveAll_nooverlap()
	{
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(4);
		toAdd.add(5);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(7);
		collection.add(8);
		
		assertFalse(list.removeAll(collection));
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), (Integer) 3);
		assertEquals(list.get(1), (Integer) 4);
		assertEquals(list.get(2), (Integer) 5);
	}
	
	@Test
	void testRemoveAll_difftypes()
	{
		ArrayList<Integer> toAdd = new ArrayList<Integer>();
		toAdd.add(3);
		toAdd.add(4);
		
		ArraySet<Integer> list = new ArraySet<Integer>(toAdd);
		ArrayList<String> collection = new ArrayList<String>();
		
		assertThrows(Exception.class, () -> {list.removeAll(collection);});
	}
}