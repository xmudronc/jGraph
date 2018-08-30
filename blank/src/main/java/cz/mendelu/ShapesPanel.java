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
        for (int y = 0; y < nodes.size(); y++) {
            ArrayList<Node> tmp = new ArrayList<>();
            for (int x = 0; x < 3; x++) {
                if (count < nodes.size()) {
                    tmp.add(nodes.get(count));
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
        //draw lines to do
        // draw 2D lines in green and yellow               
        g2d.setPaint(Color.black);                       
        g2d.draw( new Line2D.Double( 395, 30, 320, 150 ) );
    }
}