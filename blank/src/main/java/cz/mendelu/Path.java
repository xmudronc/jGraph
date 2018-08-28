package cz.mendelu;

public class Path {
    private Properties properties;
    private Node from;
    private Node to;

    public Path(Node from, Node to) {
        this.from = from;
        this.to = to;
        this.properties = new Properties();
    }

    public Path(Node from, Node to, Properties properties) {
        this.from = from;
        this.to = to;
        this.properties = properties;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public void setTo(Node to) {
        this.to = to;
    }
}