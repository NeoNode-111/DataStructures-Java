package Trees;

import java.util.HashMap;

public class Trie {
    private class Node{
        private final char value;
        private boolean isEndOFWord;
        private HashMap<Character,Node> children = new HashMap<>();
        public Node(char value){
            this.value = value;
        }

        private void addChild(char ch){
            children.put(ch, new Node(ch));
        }
        private void removeChild(char ch){
            children.remove(ch);
        }
        private Node getChild(char ch){
            return children.get(ch);
        }
        private Node[]getChildren(){
            return children.values().toArray(new Node[0]);
        }
        private boolean hasThisChild(char ch){
            return children.containsKey(ch);
        }
        private boolean hasChildren(){
            return !children.isEmpty();
        }
        @Override
        public String toString(){
            return "node: " + value + " : " + isEndOFWord;
        }

    }

    private Node root = new Node(' ');
    private int countOfWords;
    private int countOfChars;

    public void insert(String word){
        var current = root;
        for (var ch : word.toCharArray()){
            if (!current.hasThisChild(ch)) {
                current.addChild(ch);
                countOfChars++;
            }
            current = current.getChild(ch);
        }
        current.isEndOFWord = true;
        countOfWords++;
    }
    public boolean contains(String word){
        if (countOfChars == 0)
            throw new IllegalArgumentException("is empty!");
        var current = root;
        for (var ch : word.toCharArray()){
            if (!current.hasThisChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.isEndOFWord;
    }
    public void remove(String word){
        remove(root, word, 0);
    }
    private void remove(Node node, String word, int index){
        if (index == word.length()){
            node.isEndOFWord = false;
            return;
        }
        var ch = word.charAt(index);
        var child = node.getChild(ch);
        if (child == null)
            return;
        remove(child, word, index + 1);
        if (!child.isEndOFWord & !child.hasChildren()){
            node.removeChild(ch);
        }
    }
    public void traversePreOrder(){
        traversePreOrder(root);
    }
    private void traversePreOrder(Node node){
        if (node == null)
            return;
        else if (node != root)
            System.out.println(node);
        for (var child : node.getChildren()){
            traversePreOrder(child);
        }
    }
    public void traversePostOrder(){
        traversePostOrder(root);
    }
    private void traversePostOrder(Node node){
        if (node == null)
            return;
        else if (node != root)
            System.out.println(node);
        for (var child : node.getChildren()){
            traversePreOrder(child);
        }
    }


}
