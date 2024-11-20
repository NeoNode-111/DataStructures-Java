package Trees;

public class Tree {
    private class Node{
        private int value;
        private Node leftChild;
        private Node rightChild;
        public Node(int value){
            this.value = value;
        }
        @Override
        public String toString(){
            return "node: " + value;
        }
    }
    private Node root;
    private int count;

    public void insert(int value){
        var node = new Node(value);
        if (root == null){
            count++;
            root = node;
            return;
        }
        var current = root;
        while (true){
            if (value < current.value){
                if (current.leftChild == null){
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            }
            else {
                if (current.rightChild == null){
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
        count++;
    }

    public boolean contains(int target){
        if (root == null)
            throw new IllegalArgumentException("is empty!");
        var current = root;
        while (current != null){
            if (target < current.value)
                current = current.leftChild;
            else if (target > current.value)
                current = current.rightChild;
            else
                return true;
        }
        return false;
    }

    public void remove(int target){
        if (root == null)
            throw new IllegalArgumentException("is empty!");
        var current = root;
        while (true){
            if (target < current.value){
                if (current.leftChild.value == target){
                    current.leftChild = null;
                    break;
                }
                current = current.leftChild;
            } else if (target > current.value) {
                if (current.rightChild.value == target){
                    current.rightChild = null;
                    break;
                }
                current = current.rightChild;
            }
            else return;
        }
        count = 0;
        counter(root);
    }

    private void counter(Node node){
        if (node == null)
            return;
        count++;
        counter(node.leftChild);
        counter(node.rightChild);
    }

    public void traversePreOrder(){
        traversePreOrder(root);
    }
    private void traversePreOrder(Node node){
        if (node == null)
            return;
        System.out.println(node);
        traversePreOrder(node.leftChild);
        traversePreOrder(node.rightChild);
    }

    public int height(){
        return height(root);
    }
    private int height(Node node){
        if (node == null)
            return 0;
        if (node.leftChild == null && node.rightChild == null)
            return 0;

        return Math.max(height(node.leftChild), height(node.rightChild))  + 1;
    }
    public void printAtDistance(int distance){
        printAtDistance(root, distance);
    }
    private void printAtDistance(Node node,int distance){
        if (node == null)
            return;
        if (distance == 0)
            System.out.println(node);
        printAtDistance(node.leftChild, distance - 1);
        printAtDistance(node.rightChild, distance - 1);
    }
    public Node min(){
        var current = root;
        var b = current;
        while (current != null){
            b = current;
            current = current.leftChild;
        }
        return b;
    }
    public void traverseBreadthFirst(){
        for (int i = 0; i <= height() ; i++) {
            printAtDistance(i);
        }
    }

}

