package Trees;

public class AVLTree{
    private class Node{
        private final int value;
        private Node leftChild;
        private Node rightChild;
        private int height;

        public Node(int value){
            this.value = value;
        }
        @Override
        public String toString(){
            return "node: " + value + " : " + height;
        }
    }

    private Node root;
    private int count;

    public void insert(int value){
        root = insert(root, value);
        count++;
    }
    private Node insert(Node node, int value){
        if(node == null) return new Node(value);
        if (value < node.value)
            node.leftChild = insert(node.leftChild, value);
        else node.rightChild = insert(node.rightChild, value);

        setHeight(node);
        return balance(node);
    }
    public boolean contains(int value){
        if (root == null)
            throw new IllegalArgumentException("tree is empty!");
        var current = root;
        while (current != null){
            if (value < current.value)
                current = current.leftChild;
            else if(value > current.value)
                current = current.rightChild;
            else
                return true;
        }
        return false;
    }
    public void remove(int value){
        if (root == null)
            throw new IllegalArgumentException("tree is empty!");
        var current = root;
        while (true){
            if (value < current.value){
                if (current.leftChild.value == value){
                    current.leftChild = null;
                    setHeight(current);
                    break;
                }
                current = current.leftChild;
            }
            else if(value > current.value){
                if (current.rightChild.value == value){
                    current.rightChild = null;
                    setHeight(current);
                    break;
                }
                current = current.rightChild;
            }
            else break;
        }
        setHeight(root);
        count = 0;
        counter(root);
        root = balance(root);
    }
    private void counter(Node node){
        if (node == null)
            return;
        count++;
        counter(node.leftChild);
        counter(node.rightChild);
    }
    private int height(Node node){
        return (node == null) ? -1 : node.height;
    }
    private void setHeight(Node node){
        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }
    private int balanceDifference(Node node){
        return height(node.leftChild) - height(node.rightChild);
    }
    private boolean isLeftHeavy(Node node){
        return balanceDifference(node) > 1;
    }
    private boolean isRightHeavy(Node node){
        return balanceDifference(node) < -1;
    }
    private Node leftRotate(Node node){
        var newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;

        setHeight(node);
        setHeight(newRoot);
        return newRoot;
    }
    private Node rightRotate(Node node){
        var newRoot = node.leftChild;
        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;

        setHeight(node);
        setHeight(newRoot);
        return newRoot;
    }
    private Node balance(Node node){
        if (isLeftHeavy(node)) {
            if (balanceDifference(node.leftChild) < 0)
                node.leftChild = leftRotate(node.leftChild);
            return rightRotate(node);
        }
        else if(isRightHeavy(node)) {
            if (balanceDifference(node.rightChild) > 0)
                node.rightChild = rightRotate(node.rightChild);
            return leftRotate(node);
        }
        else
            return node;
        }
        public void preTraverse(){
            preTraverse(root);
        }
        private void preTraverse(Node node){
            if (node == null)
                return;
            System.out.println(node);
            preTraverse(node.leftChild);
            preTraverse(node.rightChild);
        }
    }















