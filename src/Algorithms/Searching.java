package Algorithms;

public class Searching {
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target)
                return i;
        }
        return -1;
    }

    public static int binarySearch(int[]array, int target){
        return binarySearch(array, 0, array.length - 1, target);
    }
    private static int binarySearch(int[]array, int start, int end, int target){
        if (start > end)
            return -1;
        var middle = (start + end) / 2;

        if (target == array[middle])
            return middle;

        if (target < array[middle])
            return binarySearch(array, start, middle - 1, target);

        return binarySearch(array, middle + 1, end, target);
    }
    public static int ternarySearch(int[] array, int target){
        return ternarySearch(array, target, 0, array.length - 1);
    }
    private static int ternarySearch(int[] array,int target, int left,int right){
        if (left > right)
            return -1;
        var partSize = (right - left) / 3;
        var mid1 = left + partSize;
        var mid2 = right - partSize;
        if (target == array[mid1])
            return mid1;
        if (target == array[mid2])
            return mid2;
        if (target < array[mid1])
            return ternarySearch(array, target, left, mid1 - 1);
        if (target > array[mid2])
            return ternarySearch(array, target, mid2 + 1, right);
        return ternarySearch(array, target, mid1 + 1, mid2 - 1);
    }
}
