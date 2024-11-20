package Queues;

public class QueueByArray<T>{
    private T[]array;
    private int front;
    private int rear;
    private int count;
    public QueueByArray(int capacity){
        this.array = (T[]) new Object[capacity];
    }
    public boolean isEmpty(){
        return count == 0;
    }
    public void insert(T item){
        if (count == array.length){
            T[]newArray = (T[])new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
        }
        array[rear] = item;
        rear = (rear + 1) % array.length;
        count++;
    }
    public T remove(){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        var first = array[front];
        front = (front + 1) % array.length;
        count--;
        return first;
    }
    public boolean contains(T item){
        for (int i = 0; i < count; i++) {
            if (array[i] == item)
                return true;
        }
        return false;
    }
}
