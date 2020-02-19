package com.volkodav4ik;

import java.util.Arrays;

public class MyHashMap implements MyMap {

    private static class HashMapEntry implements MyMap.Entry {
        private String key;
        private String value;
        private int hashCode;
        private HashMapEntry next;

        private HashMapEntry(String key, String value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private int size = 0;
    private HashMapEntry[] table = new HashMapEntry[16];
    private double loadFactor = 0.75;
    private double threshold = loadFactor * table.length;

    @Override
    public void clear() {
        size = 0;
        table = new HashMapEntry[16];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String put(String key, String value) {
        String result = putInternal(key, value);
        if (result == null) {
            size++;
        }
        if (size > threshold) {
            resize();
        }
        return result;
    }

    private String putInternal(String key, String value) {
        HashMapEntry newEntry = new HashMapEntry(key, value, key.hashCode());
        int position = newEntry.hashCode % table.length;
        if (table[position] != null) {
            HashMapEntry tmp = table[position];
            while (tmp != null) {
                if (tmp.key.equals(key)) {
                    String oldValue = tmp.value;
                    tmp.value = value;
                    return oldValue;
                }
                tmp = tmp.next;
            }
            newEntry.next = table[position];
        }
        table[position] = newEntry;
        return null;
    }

    private void resize() {
        Entry[] arr = toArray();
        table = new HashMapEntry[table.length * 2];
        threshold = loadFactor * table.length;
        for (Entry entry : arr) {
            putInternal(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean containsKey(String key) {
        int position = getHashIndex(key);
        if (table[position] != null) {
            HashMapEntry tmp = table[position];
            while (tmp != null) {
                if (key.equals(tmp.key)) {
                    return true;
                }
                tmp = tmp.next;
            }
        }
        return false;
    }

    private int getHashIndex(String key) {
        return key.hashCode() % table.length;
    }

    @Override
    public String get(String key) {
        if (!containsKey(key)) {
            return null;
        }
        int position = getHashIndex(key);
        HashMapEntry tmp = table[position];
        while (tmp != null) {
            if (key.equals(tmp.key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }
        return null;
    }

    @Override
    public String remove(String key) {
        if (!containsKey(key)) {
            return null;
        }
        int position = getHashIndex(key);
        String oldValue = null;
        HashMapEntry previous = null;
        HashMapEntry currentEntry = table[position];
        while (currentEntry != null) {
            if (key.equals(currentEntry.key)) {
                if (currentEntry.next == null && previous == null) {
                    oldValue = currentEntry.value;
                    table[position] = null;
                } else {
                    if (previous == null) {
                        oldValue = currentEntry.value;
                        table[position] = currentEntry.next;
                    } else {
                        previous.next = currentEntry.next;
                        oldValue = currentEntry.value;
                    }
                }
                break;
            }
            previous = currentEntry;
            currentEntry = currentEntry.next;
        }
        size--;
        return oldValue;
    }

    @Override
    public Entry[] toArray() {
        HashMapEntry[] result = new HashMapEntry[size];
        int index = 0;
        for (HashMapEntry tmp : table) {
            while (tmp != null) {
                result[index] = tmp;
                tmp = tmp.next;
                index++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}