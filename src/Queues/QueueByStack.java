package Queues;

import java.util.Stack;

public class QueueByStack <T>{
    private final Stack<T>stack1 = new Stack<>();
    private final Stack<T>stack2 = new Stack<>();
    private int count;

    public void insert(T item){
        stack1.push(item);
        count++;
    }
    public boolean isEmpty(){
        return count== 0;
    }
    public T remove(){
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        count--;
        return stack2.pop();
    }
    public boolean contains(T item){
        if (isEmpty())
            throw new IllegalArgumentException("is Empty!");
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        return stack2.contains(item);
    }
}
