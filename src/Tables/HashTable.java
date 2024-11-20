package Tables;

import java.util.LinkedList;

public class HashTable<T> {
    private class Node<T>{
        private int key;
        private T value;
        public Node(int key, T value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return key + " : " + value;
        }
    }
    private LinkedList<Node<T>>[] array;
    private int count;
    public HashTable(int capacity){
        this.array = new LinkedList[capacity];
    }
    private int hashFunc(int key){
        return key % array.length;
    }
    public void insert(int key,T value){
        var node = new Node<T>(key, value);
        var index = hashFunc(key);
        if (array[index] == null){
            array[index] = new LinkedList<Node<T>>();
        }
        array[index].addLast(node);
        count++;
    }

    public boolean contains(int key){
        for (var list : array){
            if (list != null)
                for (var node : list)
                    if (node.key == key)
                        return true;
        }
        return false;
    }

    public T get(int key){
        for (var list : array) {
            if (list != null)
                for (var node : list) {
                    if (node.key == key)
                        return node.value;
                }
        }

        return null;
    }
    public void remove(int key){
        if (count == 0)
            throw new IllegalArgumentException("is empty!");
        for (var list : array)
            if (list != null)
                list.removeIf(node -> node.key == key);
    }
}
