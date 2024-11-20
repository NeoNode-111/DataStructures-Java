import Algorithms.Searching;
import Algorithms.Sorting;
import Arrays.DynamicArray;
import Graphs.Graph;
import LinkedLists.MyLinkedList;
import Trees.AVLTree;
import Trees.Trie;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var tree = new AVLTree();
        tree.insert(100);
        tree.insert(300);
        tree.insert(400);
        tree.insert(50);
        tree.insert(55);
        tree.preTraverse();
        System.out.println();
        System.out.println();
        tree.remove(400);
        tree.insert(1000);
        tree.preTraverse();

    }
}