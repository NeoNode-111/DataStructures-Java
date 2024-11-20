package Arrays;

import java.util.Arrays;

public class DynamicArray <T>{
    private T[]array = (T[]) new Object[10];
    private int count;



    public void insert(T item){
        if (count == array.length){
            T[]newArray = (T[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[count++] = item;
    }
    public void remove(int index){
        for (int i = index; i < count; i++) {
            array[i] = array[i + 1];
        }
        count--;
    }
    public int indexOf(T item){
        for (int i = 0; i < count; i++) {
            if (array[i] == item)
                return i;
        }
        return -1;
    }
    public T itemOf(int index){
        return array[index];
    }
    public void iterate(){
        Arrays.stream(array).forEach(System.out::println);
    }
    public void print(){
        for (var i=0;i < count;i++) {
            System.out.println(array[i]);
        }
    }
    @Override
    public String toString(){
        var content = Arrays.copyOfRange(array,0, count);
        return Arrays.toString(content);
    }





}
