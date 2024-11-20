package Stacks;

public class StackByLinkedList <T>{
    private class Node<T>{
        private T value;
        private Node next;
        private Node previous;
        public Node(T value){
            this.value = value;
        }
        @Override
        public String toString(){
            return "node: "  + value;
        }
    }


    private Node first;
    private Node last;
    private int count;
    public boolean isEmpty(){
        return count == 0;
    }

    public void push(T item){
        var node = new Node<>(item);
        if(first == null) {
            first = last = node;
            count++;
            return;
        }
        var pre = last;
        last = node;
        last.previous = pre;
        pre.next = last;
        count++;
    }
    public Node pop(){
        var top  = last;
        last = last.previous;
        count--;
        return top;
    }
    public boolean contains(T item){
        if (count == 0)
            throw new IllegalArgumentException("is Empty!");
        var current = first;
        while (current != null) {
            if (current.value == item)
                 return true;
            current = current.next;
        }
        return false;


    }



}
