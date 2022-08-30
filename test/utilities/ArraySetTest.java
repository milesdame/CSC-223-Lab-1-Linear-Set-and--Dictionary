package utilities;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

class ArraySetTest
{
	@Test
	void testArraySetCollectionOfE()
	{
		
	}

	@Test
	void testAddE()
	{
		
	}

	@Test
	void testAddAllCollectionOfQextendsE()
	{
		
	}

	@Test
	void testRetainAll()
	{
	}

	@Test
	void testRemoveAll()
	{
		ArraySet<Integer> list = new ArraySet<Integer>(1,2,3);
		ArrayList<Integer> collection = new ArrayList<Integer>(1,1);
		
		System.out.println(list.removeAll(collection));
		
	}

	@Test
	void testAddAllIntCollectionOfQextendsE()
	{
	}
}
