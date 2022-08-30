/**
 * A class representing an ArraySet of unique objects
 *
 * @author Miles Dame, Hanna King, Sophie Ngo
 */

package utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Spliterator;


public class ArraySet<E> implements List<E>, Set<E>
{

	protected static final boolean FAILED_OPERATION_BOOL = false;

	protected ArrayList<E> _list;

	// miles VVVVVV
	/**
	 * Creates a new ArraySet with generic type
	 */
	public ArraySet()
	{
		_list = new ArrayList<E>();

	}

	/**
	 * Creates a new ArraySet with Collection of generic type objects
	 * @param collection
	 */
	public ArraySet(Collection<E> collection) {
		this();

		for (E item : collection) {
			add(item);
		}
	}

	@Override
	public int size() {
		return _list.size();
	}

	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return _list.contains(o);

	}

	@Override
	public Iterator<E> iterator() {
		return _list.iterator();
	}

	@Override
	public Object[] toArray() {
		return _list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return _list.toArray(a);
	}

	@Override
	public boolean add(E e) {
		if (_list.contains(e)) {
			return _list.add(e);
		}

		return FAILED_OPERATION_BOOL;
	}

	@Override
	public boolean remove(Object o) {
		return _list.remove(o);
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		return _list.containsAll(c);
	}

	/**
	 * AddAll - appends all elements in the collection at the end of the arrayset, but only if the elements in the collection do not already exist in the set
	 * @param collection c
	 * @return true if set was changed at all, false if it wasn't
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		int count = 0;
		
		for (E element : c) {
			boolean added = _list.add(element); // !!!!!!!!!!!how do i know if this is using this class's add method instead of the ArrayList add method?
			
			if (added == FAILED_OPERATION_BOOL) {
				count++;
			}
			
		}	
		
		// if there are as many (or more...) failed operations as there are items in the collection, then we did not change anything.
		if (count >= c.size()) {
			return FAILED_OPERATION_BOOL;
		} 
		// if there are less failed operations than there are items in the collection, then we did change something.
		else (count < c.size()) {
			return true;
		}
	}

	/**
	 * AddAll - inserts all elements in the collection at the given index in the arrayset, but only if the elements in the collection does not already exist in the set
	 * @param int index, collection c
	 * @return true if set was changed at all, false if it wasn't
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int count = 0;
		int currIndex = index;
		
		for (E element : c) {
			boolean added = _list.add(currIndex, element); // !!!!!!!!!!!how do i know if this is using this class's add method instead of the ArrayList add method?
			currIndex++; // go to the next index in the set to insert at
			
			if (added == FAILED_OPERATION_BOOL) {
				count++;
			}
			
		}	
		
		// if there are as many (or more...) failed operations as there are items in the collection, then we did not change anything.
		if (count >= c.size()) {
			return FAILED_OPERATION_BOOL;
		} 
		// if there are less failed operations than there are items in the collection, then we did change something.
		else {
			return true;
		}
	}

	//  sophie VVVV


	@Override
	// removes all items in collection c from _list
	// returns true if _list changed at all
	public boolean removeAll(Collection<?> c) {
		return _list.removeAll(c);
	}

	@Override
	// removes all items NOT in collection c from _list
	// returns true if _list changed at all
	public boolean retainAll(Collection<?> c) {
		return _list.retainAll(c);
	}

	@Override
	// removes all elements from the list
	public void clear() {
		_list.clear();
	}

	@Override
	// gets the element at the specified index
	public E get(int index) {
		return _list.get(index);
	}

	@Override
	// replaces the element at the index. returns the old element (before replacement)
	public E set(int index, E element) {
		return _list.set(index, element);
	}

	@Override
	// inserts the element at the index (all elements to the right are shifted right by one)
	public void add(int index, E element) {
		if (_list.contains(e)) {
			return _list.add(index, e);
		}

		return FAILED_OPERATION_BOOL;
	}

	@Override
	// removes the element at the index (all elements to the right are shifted left by one)
	public E remove(int index) {
		return _list.remove(index);
	}

	@Override
	// returns the index of the first occurence of object o in _list, else -1
	public int indexOf(Object o) {
		return _list.indexOf(o);
	}

	@Override
	// returns the index of the last occurence of object o in _list, else -1
	public int lastIndexOf(Object o) {
		return _list.lastIndexOf(o);
	}

	@Override
	// returns a list iterator over the elements in this list
	public ListIterator<E> listIterator() {
		return _list.listIterator();
	}

	@Override
	// returns a list iterator over the elements in this list starting at given index
	public ListIterator<E> listIterator(int index) {
		return _list.listIterator(index);
	}

	@Override
	// returns a list starting from the element at fromIndex and ending with the element at the index before toIndex
	public List<E> subList(int fromIndex, int toIndex) {
		return _list.subList(fromIndex, toIndex);
	}

	@Override
	// returns a spliterator over elements in this list
	public Spliterator<E> spliterator() {
		return _list.spliterator();
	}

}
