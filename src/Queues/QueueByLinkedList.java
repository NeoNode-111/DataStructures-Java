package Queues;

public class QueueByLinkedList<T> {
    private class Node<T>{
        private T value;
        private Node previous;
        private Node next;
        public Node(T value){
            this.value = value;
        }
        @Override
        public String toString(){return "node: " + value;}
    }
    private int count;
    private Node first;
    private Node last;

    public boolean isEmpty(){
        return count == 0;
    }
    public void insert(T item) {
        var node = new Node<>(item);
        if (isEmpty()) {
            first = last = node;
            count++;
            return;
        }
        var pre = last;
        last = node;
        pre.next = last;
        last.previous = pre;
        last.next = null;
        count++;
    }
    public Node remove(){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        count--;
        var firstOne = first;
        first = first.next;
        //first.previous = null;
        return firstOne;
    }
    public boolean contains(T item){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        var current = first;
        while (current != null){
            if (item == current.value)
                return true;
            current = current.next;
        }
        return false;
    }








}


