package Graphs;

import java.util.*;

public class WeightedGraph {
    private class Node {
        private final String label;
        private final int value;
        private final ArrayList<Edge> edges = new ArrayList<>();

        public Node(String label, int value) {
            this.label = label;
            this.value = value;
        }

        @Override
        public String toString() {
            return label + " : " + value;
        }

        private void connect(Node node, int weight) {
            edges.add(new Edge(this, node, weight));
        }

        private void disconnect(Node node) {
            edges.removeIf(edge -> edge.to == node);


        }
    }

    private class Edge {
        private final Node from;
        private final Node to;
        private final int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " --> " + to + " : " + weight;
        }

    }

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private int count;
    private final HashMap<String, Node> nodes = new HashMap<>();


    public void addNode(String label, int value) {
        nodes.put(label, new Node(label, value));
        count++;
    }

    public void removeNode(String label) {
        var node = nodes.get(label);
        if (node == null)
            return;

        for (var other : nodes.values())
            other.edges.removeIf(edge -> edge.to == node);


        nodes.remove(label);
        count--;
    }

    public void connect(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;

        fromNode.connect(toNode, weight);
        toNode.connect(fromNode, weight);

    }

    public void disconnect(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;

        fromNode.disconnect(toNode);
        toNode.disconnect(fromNode);
    }

    public void traverseDepthFirst() {
        var starter = nodes.values().toArray(new Node[0])[0];
        var visited = new HashSet<Node>();
        traverseDepthFirst(starter, visited);
    }

    private void traverseDepthFirst(Node node, Set<Node> visited) {
        if (node == null)
            return;
        if (visited.contains(node))
            return;
        System.out.println(node);
        visited.add(node);
        for (var edge : node.edges) {
            if (visited.contains(edge.to))
                continue;
            traverseDepthFirst(edge.to, visited);
        }
    }

    public void traverseBreadthFirst() {
        var starter = nodes.values().toArray(new Node[0])[0];
        var visited = new HashSet<>();
        var queue = new ArrayDeque<Node>();

        queue.add(starter);
        while (!queue.isEmpty()) {
            var current = queue.pop();
            if (visited.contains(current))
                continue;
            System.out.println(current);
            visited.add(current);
            for (var edge : current.edges) {
                if (visited.contains(edge.to))
                    continue;
                queue.add(edge.to);
            }


        }


    }

    public boolean hasCycle() {
        var visiting = new HashSet<Node>();
        var visited = new HashSet<Node>();

        var all = new HashSet<Node>(nodes.values());
        while (!all.isEmpty()) {
            var current = all.toArray(new Node[0])[0];
            var result = hasCycle(current, all, visiting, visited);
            if (result)
                return true;
        }
        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited) {
        all.remove(node);
        visiting.add(node);
        for (var edge : node.edges) {
            if (visited.contains(edge.to))
                continue;
            if (visiting.contains(edge.to))
                return true;
            var result = hasCycle(edge.to, all, visiting, visited);
            if (result)
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }

    public String shortestWay(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return null;

        var visited = new HashSet<Node>();
        var distances = new HashMap<Node, Integer>();
        var preNode = new HashMap<Node, Node>();
        var queue = new PriorityQueue<NodeEntry>(Comparator.comparing(e -> e.priority));

        for (var node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);
        distances.replace(fromNode, 0);
        queue.add(new NodeEntry(fromNode, 0));

        while (!queue.isEmpty()) {
            var current = queue.remove().node;

            if (visited.contains(current))
                continue;
            visited.add(current);


            for (var edge : current.edges) {
                if (visited.contains(edge.to))
                    continue;
                var newDistance = distances.get(current) + edge.weight;
                if (newDistance < distances.get(edge.to)) {
                    distances.replace(edge.to, newDistance);
                    preNode.put(edge.to, current);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }
        var stack = new Stack<String>();
        stack.add(toNode.label);
        var previous = preNode.get(toNode);
        while (previous != null) {
            stack.add(previous.label);
            previous = preNode.get(previous);
        }
        var path = new ArrayList<String>();
        while (!stack.isEmpty())
            path.add(stack.pop());
        return path.toString();
    }

    public WeightedGraph spanningTree(){
        var starter = nodes.values().toArray(new Node[0])[0];
        var queue = new PriorityQueue<Edge>(Comparator.comparing(e -> e.weight));
        var tree = new WeightedGraph();
        tree.addNode(starter.label, starter.value);

        queue.addAll(starter.edges);

        while (tree.nodes.size() != nodes.size()){
            var minEdge = queue.remove();
            var next = minEdge.to;
            if (tree.nodes.containsKey(next.label))
                continue;
            tree.addNode(next.label, next.value);
            tree.connect(minEdge.from.label, next.label, minEdge.weight);

            for (var edge : next.edges) {
                if (tree.nodes.containsKey(edge.to.label))
                    continue;
                queue.add(edge);
            }
        }
        return tree;
    }

    public void print(){
        var visited = new HashSet<Node>();
        for (var node : nodes.values()){
            visited.add(node);
            for (var edge : node.edges) {
                if (visited.contains(edge.to))
                    continue;
                System.out.println(edge);
            }
        }
    }


}

