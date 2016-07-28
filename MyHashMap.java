import java.util.LinkedList;

public class MyHashMap<K, V> {
    public static final float LOAD_FACTOR = 0.75f;
    public static final int INIT_CAPACITY = 16;
    public static final int MAX_CAPACITY = Integer.MAX_VALUE;

    private int size = 0;
    private Object[] table;

    public MyHashMap() {
        table = new Object[INIT_CAPACITY];
    }
    
    public V put(K key, V value) {
        if (key == null) {
            return null;
        }
        int index = key.hashCode() & (table.length - 1);

        if (table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }
        
        // We know the array will only contain a LinkedList of Entry
        @SuppressWarnings("unchecked")
        LinkedList<Entry<K, V>> entries = (LinkedList<Entry<K, V>>) table[index];

        boolean keyExists = false;
        for (Entry<K, V> entry : entries) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                keyExists = true;
                break;
            }
        }
        if (!keyExists) {
            entries.add(new Entry<K, V>(key, value));
        }

        size++;
        if (size > LOAD_FACTOR * table.length) {
            resizeTable(2 * table.length); 
        }

        return value;
    }

    private void resizeTable(int newCapacity) {
        if (table.length == MAX_CAPACITY) {
            return;
        }
        Object[] newTable = new Object[newCapacity]; 

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                // We know the array will only contain a LinkedList of Entry
                @SuppressWarnings("unchecked")
                LinkedList<Entry<K, V>> entries = (LinkedList<Entry<K, V>>) table[i];
                for (Entry<K, V> entry : entries) {
                    int newIndex = entry.getKey().hashCode() & (newCapacity - 1);
                    
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new LinkedList<Entry<K, V>>();
                    }
                    // We know the array will only contain a LinkedList of Entry
                    @SuppressWarnings("unchecked")
                    LinkedList<Entry<K, V>> newEntries = (LinkedList<Entry<K, V>>) newTable[newIndex];
                    newEntries.add(entry);
                }
                entries.clear();
                table[i] = null;
            }
        }
        
        table = newTable;
    }

    public V get(K key) {
        int index = key.hashCode() & (table.length - 1);
        
        // We know the array will only contain a LinkedList of Entry
        @SuppressWarnings("unchecked")
        LinkedList<Entry<K, V>> entries = (LinkedList<Entry<K, V>>) table[index];
        
        V value = null;
        if (entries != null) {
            for (Entry<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    value = entry.getValue();
                    break;
                }
            }
        }

        return value;
    }

    public static void main(String[] args) {
        // Test case 1
        MyHashMap<String, Integer> testMap = new MyHashMap<>();
        testMap.put("t1", 1);
        testMap.put("t2", 2);
        testMap.put("t3", 3);
        testMap.put("t2", 4);

        System.out.println(testMap.get("t2")); // 4
        System.out.println(testMap.get("t3")); // 3
        System.out.println(testMap.get("t1")); // 1
        System.out.println();

        // Test case 2
        MyHashMap<Integer, Integer> testMap2 = new MyHashMap<>();
        for (int i = 0; i < 40; i++) {
            testMap2.put(i, i);
        }

        System.out.println(testMap2.get(18)); // 18
        System.out.println(testMap2.get(0)); // 0
        System.out.println(testMap2.get(5)); // 5
        System.out.println(testMap2.get(38)); // 38
        System.out.println(testMap2.get(39)); // 39
        System.out.println(testMap2.get(40)); // null
        System.out.println();

        // Test case 3
        MyHashMap<String, String> testMap3 = new MyHashMap<>();
        for (int i = 0; i < 40; i++) {
            testMap3.put("str" + i, "str" + i);
        }
        for (int i = -1; i < 41; i++) {
            System.out.println(testMap3.get("str" + i));
        }
    }

    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K k, V v) {
            key = k;
            value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V v) {
            value = v;
        }
    }
}
