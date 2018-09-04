package cz.mendelu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ShapesPanel extends JPanel
{
    private ArrayList<Node> nodes;
    private ArrayList<Path> paths;
    private ArrayList<ArrayList<Node>> map = new ArrayList<>();

    public ShapesPanel(ArrayList<Node> nodes, ArrayList<Path> paths) {
        this.nodes = nodes;
        this.paths = paths;

        int count = 0;
        for (int y = 0; y < this.nodes.size(); y++) {
            ArrayList<Node> tmp = new ArrayList<>();
            for (int x = 0; x < 3; x++) {
                if (count < this.nodes.size()) {
                    tmp.add(this.nodes.get(count));
                    count++;
                } else {
                    tmp.add(null);
                }
            }
            map.add(tmp);
        } 
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call superclass's paintComponent
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.black);                     
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(0).size(); x++) {
                if (map.get(y).get(x) != null) {
                    g2d.fill(new Ellipse2D.Double( x*100, y*100, 50, 50 ) );
                }
            }
        }
             
        g2d.setPaint(Color.red);
        for (Path _path : paths) {
            int x1 = 0;
            int y1 = 0;
            int x2 = 0;
            int y2 = 0;
            for (int y = 0; y < map.size(); y++) {
                for (int x = 0; x < map.get(0).size(); x++) { 
                    if (map.get(y).get(x) != null) {
                        if (map.get(y).get(x).equals(_path.getFrom())) {
                            x1 = x;
                            y1 = y;
                            break;
                        }
                    }
                }
            } 
            for (int y = 0; y < map.size(); y++) {
                for (int x = 0; x < map.get(0).size(); x++) {
                    if (map.get(y).get(x) != null) {
                        if (map.get(y).get(x).equals(_path.getTo())) {
                            x2 = x;
                            y2 = y;
                            break;
                        }
                    }
                }
            }
            g2d.draw( new Line2D.Double( x1*100 + 25, y1*100 + 25, x2*100 + 25, y2*100 + 25 ) );
        }
    }
}