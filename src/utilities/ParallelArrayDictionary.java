package utilities;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A ParallelArrayDictionary is a dictionary whose keys and values are associated via sharing an index.
 * The key container is an ArraySet, meaning it can contain only unique values but is also indexable.
 * The value container is an ArrayList, so it is also indexable.
 * A key is associated to the value that is at the same index in their respective containers.
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
	 * The value container is an ArrayList.
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
		return _keys.size();
	}

	/**
	 * Return whether the dictionary is empty or not.
	 * @param none
	 * @return true if it's empty, false if it's not
	 */
	@Override
	public boolean isEmpty() {
		return _keys.isEmpty();
	}

	/**
	 * Return whether the dictionary contains a mapping for the specified key.
	 * @param key - the key to find
	 * @return true if it does, false if it doesn't
	 */
	@Override
	public boolean containsKey(Object key) {
		return _keys.contains(key);
	}

	/**
	 * Return whether the dictionary contains a mapping for the specified value.
	 * @param value - the value to find
	 * @return true if it does, false if it doesn't
	 */
	@Override
	public boolean containsValue(Object value) {
		return _values.contains(value);
	}

	/**
	 * Get the value associated with the given key.
	 * @param key - key to find the corresponding value for
	 * @return the value. returns null if there is no mapping associated with this key.
	 */
	@Override
	public Value get(Object key) {
		
		// Check to see if the key exists in the dictionary
		// If it does then get the index of that key and then return the value at that index
		if (this.containsKey(key)) {
			return _values.get(_keys.indexOf(key));
		}
		
		return null;
	}

	/**
	 * Associates the given value to the given key. If the key already exists in the dictionary, replace its old value with the given value
	 * @param key - key to add or to change the value of
	 * @param value - value to add or update with
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
	 * @param key - key to remove
	 * @return the old value associated with the key. if there was no such key in the dictionary, returns null
	 * 
	 */
	@Override
	public Value remove(Object key) {
		Value oldValue = null;
		
		if (this.containsKey(key)) {
			oldValue = this.get(key);
			_values.remove(_keys.indexOf(key));
			_keys.remove(key);
		}
		
		return oldValue;
	}
	
	/**
	 * Adds all elements from a map object to the dictionary
	 * @param a map object to 
	 */
	@Override
	public void putAll(Map<? extends Key, ? extends Value> m) {
		//m.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
		for (Entry<? extends Key, ? extends Value> entry : m.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
		
	}

	/**
	 * Removes all keys and all values
	 */
	@Override
	public void clear() {
		_keys.clear();
		_values.clear();
	}

	/**
	 * Returns the keys in the dictionary as a Set. 
	 * @return set that contains all keys in the dictionary
	 */
	@Override
	public Set<Key> keySet() {
		return _keys;
	}

	/**
	 * Returns the values in the dictionary as a Collection.
	 * @return collection that contains all values in the dictionary
	 */
	@Override
	public Collection<Value> values() {
		return _values;
	}

	/**
	 * Returns a set view of all mappings in the dictionary. 
	 * Each element of the set is a map.Entry containing one key, value pair
	 * @param none
	 * @return - a set which contains all mappings in the dictionary
	 */
	@Override
	public Set<Entry<Key, Value>> entrySet() {
		Set<Entry<Key, Value>> entrySet = new ArraySet<Entry<Key, Value>>();

		for (Key key : _keys) {
			Value value = this.get(key);
 			entrySet.add(new AbstractMap.SimpleEntry<Key, Value>(key, value));
			
		}
		return entrySet;
	}

}
