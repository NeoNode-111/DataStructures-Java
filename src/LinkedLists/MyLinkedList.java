package LinkedLists;

public class MyLinkedList<T> {
    private class Node<T>{
        private final T value;
        private Node next;
        private Node previous;
        public Node(T value){
            this.value = value;
        }
        @Override
        public String toString(){
            return "node: " + value;
        }
    }

    private Node first;
    private Node last;
    private int count;

    public boolean isEmpty(){
        return count == 0;
    }

    public void addFirst(T item){
        var node = new Node<>(item);
        if (isEmpty()) {
            first = last = node;
            count++;
            return;
        }
        var next = first;
        first = node;
        first.next = next;
        next.previous = first;
        count++;
    }
    public void addLast(T item){
        var node = new Node<>(item);
        if (isEmpty()) {
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

    public void removeFirst(){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        first = first.next;
        first.previous = null;
        count--;
    }
    public void removeLast(){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        last = last.previous;
        last.next = null;
        count--;
    }
    public Node[] toArray(){
        var index = 0;
        var current = first;
        Node[]array = new Node[count];
        while (current != null){
            array[index++] = current;
            current = current.next;
        }
        return array;
    }

    public void reverse(){
        var a = first;
        var b = a.next;
        last = first;
        last.next = null;
        while (b != null){
            var c = b.next;
            b.next = a;
            a = b;
            b = c;
        }
        first = a;
    }

    public Node getKthFromEnd(int k){
        var a = first;
        var b = first;
        for (int i = 0; i < k - 1; i++)
            b = b.next;
        while (b != last){
            a = a.next;
            b = b.next;
        }
        return a;
    }
    public boolean contains(T item){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        var current = first;
        while (current != null){
            if (current.value == item)
                return true;
            current = current.next;
        }
        return false;
    }

    public void print(){
        var current = first;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }








}
