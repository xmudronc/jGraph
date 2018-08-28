package cz.mendelu;

public class App 
{
    private static Graph graph = new Graph();

    public static void main( String[] args )
    {
        Node nd1 = new Node(new Properties("nd1", 0, 0));
        Node nd2 = new Node(new Properties("nd2", 0, 0));
        Path pt1 = new Path(nd1, nd2, new Properties("pt1", 0, 0));
        graph.addNode(nd1);
        graph.addNode(nd2);
        graph.addPath(pt1);

        Node nd3 = new Node(new Properties("nd3", 0, 0));
        Path pt2 = new Path(nd2, nd3, new Properties("pt2", 0, 0));
        Path pt3 = new Path(nd3, nd1, new Properties("pt3", 0, 0));
        Node nd4 = new Node(new Properties("nd4", 0, 0));
        graph.addNode(nd3);
        graph.addPath(pt2);
        graph.addPath(pt3);
        graph.addNode(nd4);

        Path pt4 = new Path(nd4, nd1, new Properties("pt4", 0, 0));
        graph.addPath(pt4);

        //graph.removeNode(graph.getNodeByName("nd2"));

        graph.printAllNodes();
        graph.printAllPaths();
        //graph.getNodeByName("nd3").printNeighbors();
        //graph.getNodeByName("nd3").printPaths();
    }
}
