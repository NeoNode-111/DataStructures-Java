package Queues;

public class MyPriorityQueue {
    private int[]array;
    private int count;
    private int front;
    public MyPriorityQueue(int capacity){
        this.array = new int[capacity];
    }

    public void insert(int item){
        if (count == array.length){
            int[]newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
        }
        int i;
        for (i = count - 1; i >= 0 ; i--) {
            if (item < array[i]) {
                array[i + 1] = array[i];
            }
            else
                break;
        }
        array[i + 1] = item;
        count++;
    }
    public int remove(){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        var first = array[front];
        array[front++] = 0;
        count--;
        return first;
    }
    public boolean isEmpty(){
        return count == 0;
    }
    public boolean contains(int item){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        for (int i = 0; i < count; i++) {
            if (array[i] == item)
                return true;
        }
        return false;
    }
}
