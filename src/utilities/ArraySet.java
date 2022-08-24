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
	
	protected ArrayList<E> _list;
	
	// miles VVVVVV

	public ArraySet()
	{
		_list = new ArrayList<E>();
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return _list.contains(o);
		
	}


	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
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
		_list.add(index, element);
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
