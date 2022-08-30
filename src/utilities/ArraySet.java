/**
 * A class representing an ArraySet of unique objects.
 * It is functionally the same as an ArrayList, except it can only contain unique elements.
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

	/**
	 * constant variable defining a failed operation (e.g. failed to add)
	 */
	protected static final boolean FAILED_OPERATION_BOOL = false;

	/**
	 * ArrayList instance variable that is used to contain the elements of the ArraySet.
	 */
	protected ArrayList<E> _list;

	/**
	 * Creates a new ArraySet with generic type
	 */
	public ArraySet()
	{
		_list = new ArrayList<E>();

	}

	/**
	 * Creates a new ArraySet with Collection of generic type objects
	 * @param collection - collection to be used as the arrayset
	 */
	public ArraySet(Collection<E> collection)
	{
		this();

		for (E item : collection) {
			_list.add(item);
		}
	}

	/**
	 * returns the size of the set
	 * @return size of the set
	 */
	@Override
	public int size() {
		return _list.size();
	}

	/**
	 * checks whether the set is empty
	 * @return true if it's empty, false if it isn't
	 */
	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	/**
	 * checks whether the set contains the given object
	 * @param o - object to find
	 * @return true if the set contains it, false if it doesn't
	 */
	@Override
	public boolean contains(Object o) {
		return _list.contains(o);

	}

	/**
	 * returns an iterator of the set
	 * @return iterator object
	 */
	@Override
	public Iterator<E> iterator() {
		return _list.iterator();
	}

	/**
	 * Converts the set to an array
	 * @return array that contains all elements in the set in order
	 */
	@Override
	public Object[] toArray() {
		return _list.toArray();
	}

	/**
	 * Converts the set to an array and appends it to the specified array
	 * @param a - array to be used to append to
	 * @return array that contains all elements in the set in order
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return _list.toArray(a);
	}

	/**
	 * Add the specified element to the set if it does not already exist in the set.
	 * @param e - the element to be added
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean add(E e) {
		if (!_list.contains(e)) {
			return _list.add(e);
		}

		return FAILED_OPERATION_BOOL;
	}

	/**
	 * removes the given object from the set
	 * @param o - object to be removed
	 * @return the removed object
	 */
	@Override
	public boolean remove(Object o) {
		return _list.remove(o);
	}

	/**
	 * check if the set contains every element in the given collection
	 * @param c - collection to find in the set
	 * @return true if it does contain everything, false if it doesn't
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return _list.containsAll(c);
	}

	/**
	 * AddAll - appends all elements in the collection at the end of the arrayset, but only if the elements in the collection do not already exist in the set
	 * @param c - collection to append
	 * @return true if set was changed at all, false if it wasn't
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		int failedCount = 0;
		
		for (E element : c) {
			boolean added = _list.add(element); // !!!!!!!!!!!how do i know if this is using this class's add method instead of the ArrayList add method?
			
			if (added == FAILED_OPERATION_BOOL) {
				failedCount++;  // unsuccessful adds are counted
			}
			
		}	
		
		// if there are less failedCount operations than there are items in the collection, then we did change something.
		if (failedCount < c.size()) {
			return true;
		} 
		// if there are as many (or more...) failedCount operations as there are items in the collection, then we did not change anything.
		else {
			return FAILED_OPERATION_BOOL;
		}
	}

	/**
	 * AddAll - inserts all elements in the collection at the given index in the arrayset, but only if the elements in the collection does not already exist in the set
	 * @param index - index at which to insert the collection
	 * @param c - collection to insert
	 * @return true if set was changed at all, false if it wasn't
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int failedCount = 0;
		int currIndex = index;
		
		for (E element : c) {
			_list.add(currIndex, element); // !!!!!!!!!!!how do i know if this is using this class's add method instead of the ArrayList add method?
			
			if (_list.contains(element)) { // check if the add was successful
				currIndex++; // go to the next index in the set to insert at
				
			} else {
				failedCount++; // unsuccessful adds are counted
			}			
		}	
		
		// if there are less failedCount operations than there are items in the collection, then we did change something.
		if (failedCount < c.size()) {
			return true;
		} 
		// if there are as many (or more...) failedCount operations as there are items in the collection, then we did not change anything.
		else {
			return FAILED_OPERATION_BOOL;
		}
	}

	/**
	 *  removes all items in collection c from the set
	 *  @param c - collection to be removed in set
	 *  @return true if the set was changed at all, false if it wasn't
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return _list.removeAll(c);
	}

	/**
	 *  removes all items NOT in collection c from the set
	 *  @param c - collection to be not removed in set
	 *  @return true if the set was changed at all, false if it wasn't
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return _list.retainAll(c);
	}

	/**
	 *  removes all elements from the list
	 */
	@Override
	public void clear() {
		_list.clear();
	}

	/**
	 *  gets the element at the specified index
	 *  @param index 
	 *  @return element at index
	 */
	@Override
	public E get(int index) {
		return _list.get(index);
	}

	/**
	 *  replaces the element at the index. returns the old element (before replacement)
	 *  @param index - index to replace at
	 *  @param element - element to replace with
	 *  @return old element at index
	 */
	@Override
	public E set(int index, E element) {
		return _list.set(index, element);
	}

	/**
	 *  inserts the element at the index (all elements to the right are shifted right by one)
	 *  @param index
	 *  @param element
	 *  
	 */
	@Override
	public void add(int index, E element) {
		if (!_list.contains(element)) {
			_list.add(index, element);
		}
	}

	/**
	 *  removes the element at the index (all elements to the right are shifted left by one)
	 *  @param index
	 *  @return element that was removed
	 */
	@Override
	public E remove(int index) {
		return _list.remove(index);
	}

	/**
	 *  returns the index of the first occurence of an object in the set, else -1
	 *  @param o - object to find the index of
	 *  @return index of the object in the set
	 */
	@Override
	public int indexOf(Object o) {
		return _list.indexOf(o);
	}

	/** returns the index of the last occurence of an object in the set, else -1
	 *  @param o - object to find the index of
	 *  @return index of the object in the set
	 */
	@Override
	public int lastIndexOf(Object o) {
		return _list.lastIndexOf(o);
	}

	/** returns a list iterator over the elements in this list
	 *  @return Iterator object
	 */
	@Override
	public ListIterator<E> listIterator() {
		return _list.listIterator();
	}

	/**
	 *  returns a list iterator over the elements in this list starting at given index
	 *  @return iterator object
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return _list.listIterator(index);
	}

	/**
	 *  returns a list starting from the element at fromIndex and ending with the element at the index before toIndex
	 *  @param fromIndex - starting position (inclusive)
	 *  @param toIndex - ending position (exclusive)
	 *  @return List object containing elements between the given indices
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return _list.subList(fromIndex, toIndex);
	}

	/** returns a spliterator over elements in this list
	 * @return spliterator object
	 */
	@Override
	public Spliterator<E> spliterator() {
		return _list.spliterator();
	}

}
