package Graphs;

import java.util.*;

public class Graph{
    private class Node{
        private String label;
        private int data;
        public Node(String label, int data){
            this.label = label;
            this.data = data;
        }
        @Override
        public String toString(){
            return "Node : " + label + " : " + data;
        }
    }
    private HashMap<String, Node>nodes = new HashMap<>();
    private HashMap<Node, ArrayList<Node>>connections = new HashMap<>();
    private int count;

    public void addNode(String label, int data){
        nodes.putIfAbsent(label, new Node(label, data));
        connections.putIfAbsent(nodes.get(label), new ArrayList<>());
        count++;
    }
    public void removeNode(String label){
        var node = nodes.get(label);
        if (node == null)
            return;
        for (var other : nodes.values()){
            connections.get(other).removeIf(connect -> connect.label.equals(label));
        }
        connections.remove(node);
        nodes.remove(label);
        count--;
    }
    public void connect(String from, String to){
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;
        connections.get(fromNode).add(toNode);
    }
    public void disconnect(String from, String to){
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;
        connections.get(fromNode).remove(toNode);
    }
    public void print(){
        for (var node : nodes.values()){
            for (var connection : connections.get(node)){
                System.out.println(node + " connected to " + connection);
            }
        }
    }
    public void traverseDepthFirst(String root){
        traverseDepthFirst(nodes.get(root), new HashSet<>());
    }
    private void traverseDepthFirst(Node node, Set<Node> visited){
        if (node == null)
            return;
        System.out.println(node);
        visited.add(node);
        for (var n : connections.get(node)){
            if (!visited.contains(n))
                traverseDepthFirst(n, visited);
        }
    }
    public void traverseDepthFirstIterate(String root){
        var visited = new HashSet<Node>();
        var node = nodes.get(root);
        if (node == null)
            return;
        var stack = new Stack<Node>();

        stack.push(node);
        while (!stack.isEmpty()){
            var current = stack.pop();
            if (visited.contains(current))
                continue;
            System.out.println(current);
            visited.add(current);
            for (var n : connections.get(current)){
                stack.push(n);
            }
        }
    }
    public void traverseBreadthFirstIterate(String root){
        var visited = new HashSet<Node>();
        var node = nodes.get(root);
        if (node == null)
            return;
        var queue = new ArrayDeque<Node>();
        queue.add(node);
        while (!queue.isEmpty()){
            var current = queue.poll();

            if (visited.contains(current))
                continue;
            System.out.println(current);
            visited.add(current);
            for(var n : connections.get(current)) {
                if (!visited.contains(n))
                    queue.add(n);
            }
        }



    }

}
