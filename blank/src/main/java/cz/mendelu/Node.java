package cz.mendelu;

import java.util.ArrayList;

public class Node {
    private ArrayList<Path> paths;
    private Properties properties;

    public Node() {
        this.paths = new ArrayList<>();
        this.properties = new Properties();
    }

    public Node(ArrayList<Path> paths, Properties properties) {
        this.paths = paths;
        this.properties = properties;
    }

    public Node(ArrayList<Path> paths) {
        this.paths = paths;
        this.properties = new Properties();
    }

    public Node(Properties properties) {
        this.paths = new ArrayList<>();
        this.properties = properties;
    }

    public ArrayList<Path> getPaths() {
        return this.paths;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void addPath(Path path) {
        getPaths().add(path);
    }

    public void removePath(Path path) {
        getPaths().remove(path);
    }

    public Path getPathByName(String name) {
        Path out = new Path(null, null);

        for (Path _path : paths) {
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

    public ArrayList<Node> getNeighbors() {
        ArrayList<Node> out = new ArrayList<>();
        out.add(this);

        for (Path _path : paths) {
            if (!out.contains(_path.getFrom())) {
                out.add(_path.getFrom());
            }
            if (!out.contains(_path.getTo())) {
                out.add(_path.getTo());
            }
        }

        out.remove(this);

        return out;
    }

    public void printNeighbors() {
        System.out.println("Neighbors for " + getProperties().getName() + ": ");
        for (Node _node : getNeighbors()) {
            System.out.println(_node.getProperties().getName());
        }
        System.out.println();
    }

    public void printPaths() {
        System.out.println("Paths for " + getProperties().getName() + ": ");
        for (Path _path : paths) {
            System.out.println(_path.getProperties().getName());
        }
        System.out.println();
    }

    public String getNeighborsString() {
        String out = "";

        for (Node _node : getNeighbors()) {
            out+=(_node.getProperties().getName() + " ");
        }

        return out;
    }

    public String getPathsString() {
        String out = "";

        for (Path _path : paths) {
            out+=(_path.getProperties().getName() + " ");
        }

        return out;
    }
}