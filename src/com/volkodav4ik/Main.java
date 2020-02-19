package com.volkodav4ik;

public class Main {

    public static void main(String[] args) {

        MyMap myMap = new MyHashMap();
        System.out.println(myMap.isEmpty());
        myMap.put("one", "Pasha");
        myMap.put("two", "Olya");
        myMap.put("tree", "Biba");
        myMap.put("four", "Bobo");
        myMap.put("five", "Kuzya");
        myMap.put("six", "Bayron");
        System.out.println(myMap.toString());
        System.out.println(myMap.containsKey("one"));
        System.out.println(myMap.get("six"));
        myMap.remove("five");
        System.out.println(myMap.toString());
        myMap.remove("one");
        System.out.println(myMap.toString());
        System.out.println(myMap.size());
        myMap.clear();
        System.out.println(myMap.isEmpty());

        MySet mySet = new MyHashSet();
        mySet.add("1");
        mySet.add("2");
        mySet.add("3");
        mySet.add("4");
        mySet.add("5");
        mySet.add("6");
        mySet.add("7");
        mySet.add("8");
        mySet.add("9");
        mySet.add("10");
        System.out.println(mySet.toString());
        System.out.println(mySet.contains("5"));
        mySet.remove("1");
        mySet.remove("3");
        mySet.remove("5");
        mySet.remove("7");
        mySet.remove("9");
        System.out.println(mySet.toString());
        System.out.println(mySet.size());
        mySet.clear();
        System.out.println(mySet.isEmpty());
    }
}
