package cz.mendelu;

import java.util.ArrayList;

public class Graph
{
    private ArrayList<Node> allNodes;
    private ArrayList<Path> allPaths;

    public Graph() {
        this.allNodes = new ArrayList<>();
        this.allPaths = new ArrayList<>();
    }

    public ArrayList<Node> getNodes() {
        return this.allNodes;
    }

    public ArrayList<Path> getPaths() {
        return this.allPaths;
    }

    public void setNodes (ArrayList<Node> nodes) {
        this.allNodes = nodes;
    }

    public void setPaths (ArrayList<Path> paths) {
        this.allPaths = paths;
    }

    public void addNode(Node node) {
        allNodes.add(node);
    }

    public void removeNode(Node node) {
        allNodes.remove(node);
        ArrayList<Path> tmp = new ArrayList<>();
        for (Path _path : node.getPaths()) {
            tmp.add(_path);
        }
        for (Path _path : tmp) { 
            removePath(_path);      
        }
    }

    public void addPath(Path path) {
        allPaths.add(path);
        path.getFrom().addPath(path); 
        path.getTo().addPath(path); 
    }

    public void removePath(Path path) {
        path.getFrom().removePath(path);
        path.getTo().removePath(path);
        allPaths.remove(path);
    }

    public ArrayList<Path> getPathsByNode(Node node) {
        ArrayList<Path> out = new ArrayList<>();

        for (Path _path : allPaths) {
            if (_path.getFrom().equals(node) || _path.getTo().equals(node)) {
                out.add(_path);
            }
        }

        return out;
    }

    public Path getPathByNodes(Node from, Node to) {
        Path out = new Path(null, null);
        
        for (Path _path : allPaths) {
            if (_path.getFrom().equals(from) && _path.getTo().equals(to)) {
                out = _path;
            }
        }

        if (out.getFrom() == null || out.getTo() == null) {
            out = null;
        }

        return out;
    }

    public ArrayList<Node> getNodesByPath(Path path) {
        ArrayList<Node> out = new ArrayList<>();

        for (Node _node : allNodes) {
            for (Path _path : _node.getPaths()) {
                if (_path.equals(path)) {
                    out.add(_node);
                    break;
                }
            }
        }

        return out;
    }

    public Node getNodeByName(String name) {
        Node out = new Node();

        for (Node _node : allNodes) {
            if (_node.getProperties().getName().equals(name)) {
                out = _node;
                break;
            }
        }

        if (out.getProperties().getName().equals("")) {
            out = null;
        }

        return out;
    }

    public Path getPathByName(String name) {
        Path out = new Path(null, null);

        for (Path _path : allPaths) {
            if (_path.getProperties().getName().equals(name)) {
                out = _path;
                break;
            }
        }

        if (out.getFrom() == null || out.getTo() == null) {
            out = null;
        }

        return out;
    }

    public void printAllNodes() {
        System.out.println("All nodes: ");
        for (Node _node : allNodes) {
            System.out.println(_node.getProperties().getName() + " | volume: " + _node.getProperties().getVolume() + " | neighbors: " + _node.getNeighborsString() + "| paths: " + _node.getPathsString());
        }
        System.out.println();
    }

    public void printAllPaths() {
        System.out.println("All paths: ");
        for (Path _path : allPaths) {
            System.out.println(_path.getProperties().getName() + " | length: " + _path.getProperties().getLength() + " | from: " + _path.getFrom().getProperties().getName() + ", to: " + _path.getTo().getProperties().getName());
        }
        System.out.println();
    }
}