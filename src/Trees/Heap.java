package Trees;

public class Heap{
    private int[]source;
    private int count;

    public Heap(int capacity){
        this.source = new int[capacity];
    }

    private boolean isFull(){
        return source.length == count;
    }
    private void resize(){
        int[]newSource = new int[source.length*2];
        System.arraycopy(source, 0, newSource, 0, source.length);
        source = newSource;
    }
    public void insert(int value){
        if (isFull())
            resize();
        source[count++] = value;
        bubbleUp();
    }
    public int remove(){
        if (count == 0)
            throw new IllegalArgumentException("is Empty");
        var root = source[0];
        source[0] = source[count - 1];
        source[--count] = 0;
        bubbleDown();
        return root;


    }
    private int parentIndex(int index){
        return (index - 1) / 2;
    }
    private int leftChildIndex(int index){
        return (2 * index) + 1;
    }
    private int rightChildIndex(int index){
        return (2 * index) + 2;
    }
    private int bigChildIndex(int index){
        var left = leftChildIndex(index);
        var right = rightChildIndex(index);
        if (left > count)
            return index;
        if (right > count)
            return left;
        return (source[left] > source[right]) ? left : right;
    }
    private void bubbleUp(){
        var index = count - 1;
        while (index > 0 && source[index] > source[parentIndex(index)]){
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }
    private void bubbleDown(){
        var index = 0;
        while (index < count && source[index] < source[bigChildIndex(index)]){
            swap(index, bigChildIndex(index));
            index = bigChildIndex(index);
        }
    }
    private void swap(int first, int second){
        var temp = source[first];
        source[first] = source[second];
        source[second] = temp;
    }
    public void children(int item){
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (source[i] == item)
                index = i;
        }
        if (index == -1)
            return;
        var left = leftChildIndex(index);
        var right = rightChildIndex(index);
        if (left < count)
            System.out.println(source[left]);
        if (right < count)
            System.out.println(source[right]);
    }
}
