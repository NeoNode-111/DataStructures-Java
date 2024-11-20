package Algorithms;

public class Sorting {
    public static void bubbleSort(int[]array){
        boolean isSorted;
        for (int i = 0; i < array.length; i++) {
            isSorted = true;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    isSorted = false;
                    swap(array, j,j - 1);
                }
            }
            if (isSorted)
                return;
        }
    }

    public static void selectionSort(int[]array){
        for (int i = 0; i < array.length; i++) {
            var minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            swap(array,i,minIndex);
        }
    }

    public static void insertionSort(int[]array){
        for (int i = 1; i < array.length; i++) {
            var current = array[i];
            var j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    public static void mergeSort(int[]array){
        if (array.length < 2)
            return;
        var middle = array.length / 2;
        int[]left = new int[middle];
        for (int i = 0; i < middle; i++)
            left[i] = array[i];
        int[]right = new int[array.length - middle];
        for (int i = middle; i < array.length; i++)
            right[i - middle] = array[i];

        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    public static void quickSort(int[]array){
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[]array, int start, int end){
        if (start > end)
            return;
        var boundary = partitioning(array, start, end);
        quickSort(array, start, boundary - 1);
        quickSort(array, boundary + 1, end);
    }
    private static int partitioning(int[]array, int start, int end){
        var pivot = array[end];
        var boundary = start - 1;
        for (int i = start; i <= end; i++) {
            if (array[i] <= pivot)
                swap(array,++boundary, i);
        }
        return boundary;
    }

    private static void merge(int[]base, int[]left, int[]right){
        int a = 0, b = 0, c = 0;
        while (a < left.length && b < right.length){
            if (left[a] < right[b])
                base[c++] = left[a++];
            else
                base[c++] = right[b++];
        }
        while (a < left.length)
            base[c++] = left[a++];

        while (b < right.length)
            base[c++] = right[b++];
    }

    private static void swap(int[]array, int first, int second){
        var temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}
