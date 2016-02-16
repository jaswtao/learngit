package com.tl.jdktest.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeParams {

	public static void main(String[] args) {
		Map<String, List<String>> myMap = newMap();
		myMap.put("1", new ArrayList<String>());
	}
	
	static<K, V> Map<K, V> newMap() {
		return new HashMap<K, V>();
	}

}
