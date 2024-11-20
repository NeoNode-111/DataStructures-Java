package Stacks;

public class StackByArray <T>{
    private final T[]array = (T[]) new Object[10];
    private int count;



    public boolean isEmpty(){
        return count == 0;
    }
    public void push(T item){
        if (count == array.length){
            T[]newArray = (T[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
        }
        array[count++] = item;
    }
    public T pop(){
        if (isEmpty())
            throw new IllegalArgumentException("is empty!");
        var top = array[count - 1];
        array[--count] = null;
        return top;
    }
    public boolean contains(T item){
        for (int i = 0; i < count; i++) {
            if (array[i] == item)
                return true;
        }
        return false;
    }
    private boolean contains(T item,int start, int end){
        for (int i = 0; i < count; i++) {
            if (array[i] == item)
                return true;
        }
        return false;
    }











}
