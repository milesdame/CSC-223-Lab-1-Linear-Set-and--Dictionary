package utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A ParallelArrayDictionary is a dictionary whose keys and values are associated via sharing an index.
 * The key container is an ArraySet, meaning it can contain only unique values but is also indexable.
 * The value container is an ArrayList, so it is also indexable.
 * A key is associated to the value who is at the same index in their respective containers.
 * 
 * @author Sophie, Hanna, Miles
 *
 * @param <Key>
 * @param <Value>
 */
public class ParallelArrayDictionary<Key, Value> implements Map<Key, Value> {
	
	protected ArraySet<Key> _keys;
	protected ArrayList<Value> _values;

	/**
	 * Constructor - initialize the dictionary's keys and values.
	 * The key container is an ArraySet.
	 * The value containter is an ArrayList.
	 */
	public ParallelArrayDictionary()
	{
		_keys = new ArraySet<Key>();
		_values = new ArrayList<Value>();
	}

	/**
	 * Return the size of the ParallelArrayDictionary.
	 * @param none
	 * @return int size - the number of elements present in the dictionary 
	 */
	@Override
	public int size() {
		return this.size();
	}

	/**
	 * Return whether the dictionary is empty or not.
	 * @param none
	 * @return true if it's empty, false if it's not
	 */
	@Override
	public boolean isEmpty() {
		return this.isEmpty();
	}

	/**
	 * Return whether the dictionary contains a mapping for the specified key.
	 * @return true if it does, false if it doesn't
	 */
	@Override
	public boolean containsKey(Object key) {
		return this.containsKey(key);
	}

	/**
	 * Return whether the dictionary contains a mapping for the specified value.
	 * @return true if it does, false if it doesn't
	 */
	@Override
	public boolean containsValue(Object value) {
		return this.containsValue(value);
	}

	/**
	 * Get the value associated with the given key.
	 * @return the value. returns null if there is no mapping associated with this key.
	 */
	@Override
	public Value get(Object key) {
		int index = -1;
		
		// Check to see if the key exists in the dictionary
		// If it does then get the index of that key and then return the value at that index
		if (this.containsKey(key)) {
			index = _keys.indexOf(key);
			return _values.get(index);
		}
		
		return null;
	}

	/**
	 * Associates the given value to the given key. If the key already exists in the dictionary, replace its old value with the given value
	 * @param key
	 * @param value
	 * @return the old value of the specified key. if the key did not already exist, returns null
	 */
	
	@Override
	public Value put(Key key, Value value) {
		Value oldValue = null;
		
		if (this.containsKey(key)) {
			oldValue = this.get(key);
			
			_values.set(_keys.indexOf(key), value);
		}
		else {
			_keys.add(key);
			_values.add(value);
		}
		
		return oldValue;
	}

	/**
	 * Removes the mapping of the specified key.
	 * @param key
	 * @return the old value associated with the key. if there was no such key in the dictionary, returns null
	 * 
	 */
	@Override
	public Value remove(Object key) {
		Value oldValue = null;
		
		if (this.containsKey(key)) {
			_values.remove(_keys.indexOf(key));
			_keys.remove(key);
		}
		
		return oldValue;
	}

	@Override
	public void putAll(Map<? extends Key, ? extends Value> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Key> keySet() {
		return this.keySet();
	}

	@Override
	public Collection<Value> values() {
		return this.values();
	}

	@Override
	public Set<Entry<Key, Value>> entrySet() {
		// edit
		return null;
	}

}
