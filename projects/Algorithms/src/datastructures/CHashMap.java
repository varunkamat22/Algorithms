package datastructures;

import java.util.LinkedList;
import java.util.List;

public class CHashMap<K,V> {
	private Object[] in_Array = new Object[11];
	private int loadFactor;
	private int currentSize = 0;
	
	public CHashMap(int loadFactor) {
		this.loadFactor = loadFactor;
	}
	
	private class Entry<K,V>{
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		
	}
	
	private int getIndex(K k){
		return k.hashCode() % in_Array.length;
	}
	
	public void put(K k,V v){
		int pos = getIndex(k);
		if(in_Array[pos] == null){
			List<Entry<K, V>> list = new LinkedList<Entry<K, V>>();
			list.add(new Entry<K, V>(k,v));
			in_Array[pos] = list;
			currentSize++;
		}else{
			checckIfExistsElseAdd(k,v,pos);
		}
	}

	public V get(K k){
		int pos = getIndex(k);
		if(in_Array[pos] == null){
			return null;
		}else{
			@SuppressWarnings("unchecked")
			List<Entry<K, V>> list = (List<Entry<K, V>>) in_Array[pos];
			for(Entry<K,V> entry:list){
				if(entry.getKey().equals(k))
					return entry.getValue();
			}
		}
		return null;
	}
	
	private void checckIfExistsElseAdd(K k, V v,int pos) {
		@SuppressWarnings("unchecked")
		List<Entry<K, V>> list = (List<Entry<K, V>>) in_Array[pos];
		for(Entry<K,V> entry:list){
			if(entry.getKey().equals(k))
				return;
		}
		list.add(new Entry<K, V>(k,v));
		currentSize++;
	}
	
	public int size(){
		return currentSize;
	}
}
