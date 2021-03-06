package cz.mendelu;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class App 
{
    private static Graph graph = new Graph();

    public static boolean containsPath(ArrayList<Path> paths, String name) {
        boolean out = false;
        
        for (Path _path : paths) {
            if (_path.getProperties().getName().equals(name)) {
                out = true;
                break;
            }
        }
        
        return out;
    }

    public static ArrayList<Path> removePathFromArray(ArrayList<Path> paths, String name) {
        ArrayList<Path> out = new ArrayList<>();

        for (Path _path : paths) {
            if (!_path.getProperties().getName().equals(name)) {
                out.add(_path);
            }
        }

        return out;
    }

    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        frame.addWindowListener(new WindowListener(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

			@Override
			public void windowOpened(WindowEvent e) {}
			@Override
			public void windowClosed(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowActivated(WindowEvent e) {}
			@Override
			public void windowDeactivated(WindowEvent e) {}
        });
        frame.setTitle("jGraph");
        frame.setSize(604, 480);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        final JComboBox nodesToEdit = new JComboBox<>();
        for (Node _node : graph.getNodes()) {
            nodesToEdit.addItem(_node.getProperties().getName());
        }
        final JComboBox fromNewPath = new JComboBox<>();
        for (Node _node : graph.getNodes()) {
            fromNewPath.addItem(_node.getProperties().getName());
        }
        final JComboBox toNewPath = new JComboBox<>();
        for (Node _node : graph.getNodes()) {
            toNewPath.addItem(_node.getProperties().getName());
        }
        final JComboBox pathsToEdit = new JComboBox<>();
        for (Path _path : graph.getPaths()) {
            pathsToEdit.addItem(_path.getProperties().getName());
        }
        final JComboBox fromPath = new JComboBox<>();
        for (Node _node : graph.getNodes()) {
            fromPath.addItem(_node.getProperties().getName());
        }
        final JComboBox toPath = new JComboBox<>();
        for (Node _node : graph.getNodes()) {
            toPath.addItem(_node.getProperties().getName());
        }
        final TextField name = new TextField();
        name.setEnabled(true);
        name.setEditable(true);
        final JSpinner volume = new JSpinner();
        volume.setEnabled(true);

        nodesToEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText(graph.getNodeByName((String) nodesToEdit.getSelectedItem()).getProperties().getName());
                volume.setValue(graph.getNodeByName((String) nodesToEdit.getSelectedItem()).getProperties().getVolume());
            }
        });
        Button saveEdit = new Button("save edit");
        saveEdit.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) nodesToEdit.getSelectedItem();               
                try {
                    volume.commitEdit();
                } catch ( java.text.ParseException exc ) {}
                graph.getNodeByName(selected).getProperties().setVolume((Integer) volume.getValue());
                String selName = name.getText();
                graph.getNodeByName(selected).getProperties().setName(selName);
                int index = nodesToEdit.getSelectedIndex();
                nodesToEdit.removeItemAt(index);
                nodesToEdit.addItem(selName);
                fromNewPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    fromNewPath.addItem(_node.getProperties().getName());
                }
                toNewPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    toNewPath.addItem(_node.getProperties().getName());
                }
                fromPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    fromPath.addItem(_node.getProperties().getName());
                }
                toPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    toPath.addItem(_node.getProperties().getName());
                }
            }
        });
        Button removeNode = new Button("remove node");
        removeNode.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) nodesToEdit.getSelectedItem();   
                graph.removeNode(graph.getNodeByName(selected));  
                int index = nodesToEdit.getSelectedIndex();
                nodesToEdit.removeItemAt(index);
                fromNewPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    fromNewPath.addItem(_node.getProperties().getName());
                }
                toNewPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    toNewPath.addItem(_node.getProperties().getName());
                }
                fromPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    fromPath.addItem(_node.getProperties().getName());
                }
                toPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    toPath.addItem(_node.getProperties().getName());
                }
            }
        });
        JPanel panel1 = new JPanel();
        panel1.add(nodesToEdit);
        panel1.add(name);
        panel1.add(volume);
        panel1.add(saveEdit);
        panel1.add(removeNode);

        frame.add(panel1);
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setVisible(true);
        frame.add(separator1);



        final TextField nameNewNode = new TextField();
        nameNewNode.setEnabled(true);
        nameNewNode.setEditable(true);
        nameNewNode.setText("nd" + (graph.getNodes().size() + 1));
        final JSpinner volumeNewNode = new JSpinner();
        volumeNewNode.setEnabled(true);
        final Button addNewNodeButton = new Button("add node");
        addNewNodeButton.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    volumeNewNode.commitEdit();
                } catch ( java.text.ParseException exc ) {}
                graph.addNode(new Node(new Properties(nameNewNode.getText(), 0, (Integer) volumeNewNode.getValue())));
                nodesToEdit.addItem(nameNewNode.getText());
                nameNewNode.setText("nd" + (graph.getNodes().size() + 1));
                fromNewPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    fromNewPath.addItem(_node.getProperties().getName());
                }
                toNewPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    toNewPath.addItem(_node.getProperties().getName());
                }
                fromPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    fromPath.addItem(_node.getProperties().getName());
                }
                toPath.removeAllItems();
                for (Node _node : graph.getNodes()) {
                    toPath.addItem(_node.getProperties().getName());
                }
            }
        });
        JPanel panel2 = new JPanel();
        panel2.add(nameNewNode);
        panel2.add(volumeNewNode);
        panel2.add(addNewNodeButton);

        frame.add(panel2);
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setVisible(true);
        frame.add(separator2);
        
        
        final TextField namePath = new TextField();
        namePath.setEnabled(true);
        namePath.setEditable(true);
        final JSpinner lengthPath = new JSpinner();
        lengthPath.setEnabled(true);
        

        pathsToEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                namePath.setText(graph.getPathByName((String) pathsToEdit.getSelectedItem()).getProperties().getName());
                lengthPath.setValue(graph.getPathByName((String) pathsToEdit.getSelectedItem()).getProperties().getVolume());
                fromPath.setSelectedItem(graph.getPathByName((String) pathsToEdit.getSelectedItem()).getFrom().getProperties().getName());
                toPath.setSelectedItem(graph.getPathByName((String) pathsToEdit.getSelectedItem()).getTo().getProperties().getName());
            }
        });
        Button removePath = new Button("remove path");
        removePath.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) pathsToEdit.getSelectedItem();   
                graph.removePath(graph.getPathByName(selected));  
                int index = pathsToEdit.getSelectedIndex();
                pathsToEdit.removeItemAt(index);
            }
        });
        Button saveEditPath = new Button("save edit");
        saveEditPath.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) pathsToEdit.getSelectedItem();               
                try {
                    lengthPath.commitEdit();
                } catch ( java.text.ParseException exc ) {}
                String selName = namePath.getText();

                graph.removePath(graph.getPathByName(selected));
                graph.addPath(new Path(graph.getNodeByName((String) fromPath.getSelectedItem()), graph.getNodeByName((String) toPath.getSelectedItem()), new Properties(selName, (Integer) lengthPath.getValue(), 0)));

                int index = pathsToEdit.getSelectedIndex();
                pathsToEdit.removeItemAt(index);
                pathsToEdit.addItem(selName);
            }
        });
        JPanel panel3 = new JPanel();
        panel3.add(pathsToEdit);
        panel3.add(namePath);
        panel3.add(lengthPath);
        panel3.add(fromPath);
        panel3.add(toPath);
        panel3.add(removePath);
        panel3.add(saveEditPath);

        frame.add(panel3);
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setVisible(true);
        frame.add(separator3);

        final TextField nameNewPath = new TextField();
        nameNewPath.setEnabled(true);
        nameNewPath.setEditable(true);
        nameNewPath.setText("pt" + (graph.getPaths().size() + 1));
        final JSpinner lengthNewPath = new JSpinner();
        volumeNewNode.setEnabled(true);
        
        final Button addNewPathButton = new Button("add path");
        addNewPathButton.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lengthNewPath.commitEdit();
                } catch ( java.text.ParseException exc ) {}
                graph.addPath(new Path(graph.getNodeByName((String) fromNewPath.getSelectedItem()), graph.getNodeByName((String) toNewPath.getSelectedItem()), new Properties(nameNewPath.getText(), (Integer) lengthNewPath.getValue(), 0)));
                pathsToEdit.addItem(nameNewPath.getText());
                nameNewPath.setText("pt" + (graph.getPaths().size() + 1));
            }
        });
        JPanel panel4 = new JPanel();
        panel4.add(nameNewPath);
        panel4.add(lengthNewPath);
        panel4.add(fromNewPath);
        panel4.add(toNewPath);
        panel4.add(addNewPathButton);

        frame.add(panel4);
        JSeparator separator4 = new JSeparator(SwingConstants.HORIZONTAL);
        separator4.setVisible(true);
        frame.add(separator4);

        JPanel panel5 = new JPanel();
        Button printNodes = new Button("print nodes");
        printNodes.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.printAllNodes();
            }
        });
        Button printPaths = new Button("print paths");
        printPaths.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.printAllPaths();
            }
        });
        Button drawGraph = new Button("draw graph");
        drawGraph.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame draw = new JFrame();
                draw.setSize(640, 480);
                draw.add(new ShapesPanel(graph.getNodes(), graph.getPaths()));
                draw.setVisible(true);
            }
        });
        Button loadCsv = new Button("load csv");
        loadCsv.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = null;;
                if (chooser == null) {
                    chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    chooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter(){
                        @Override
                        public boolean accept(File f) {
                            return f.isDirectory() || f.getName().toLowerCase().endsWith(".csv");
                        }

                        @Override
                        public String getDescription() {
                            return "CSV Files (*.csv)";
                        }
                    });
                }
                switch (chooser.showOpenDialog(null)) {
                    case JFileChooser.APPROVE_OPTION:
                        try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                            String text = null;
                            ArrayList<Path> tmpPaths = new ArrayList<>();                            
                            while ((text = br.readLine()) != null) {
                                java.util.List<String> items = null;
                                items = Arrays.asList(text.split("\\s*,\\s*"));
                                if (graph.getNodeByName(items.get(0)) == null) {
                                    graph.addNode(new Node(new Properties(items.get(0), 0, Integer.parseInt(items.get(1))))); 
                                } else {
                                    graph.removeNode(graph.getNodeByName(items.get(0)));
                                    graph.addNode(new Node(new Properties(items.get(0), 0, Integer.parseInt(items.get(1))))); 
                                }                           
                                if (!items.get(2).equals("-")) {
                                    if (graph.getNodeByName(items.get(2)) == null) {
                                        graph.addNode(new Node(new Properties(items.get(2), 0, Integer.parseInt(items.get(3))))); 
                                    }  else {
                                        graph.removeNode(graph.getNodeByName(items.get(2)));
                                        graph.addNode(new Node(new Properties(items.get(2), 0, Integer.parseInt(items.get(3))))); 
                                    }
                                }
                                if (!items.get(4).equals("-")) {
                                    if (!containsPath(tmpPaths, items.get(4))) {
                                        tmpPaths.add(new Path(graph.getNodeByName(items.get(0)), graph.getNodeByName(items.get(2)), new Properties(items.get(4), Integer.parseInt(items.get(5)), 0)));
                                    } else {
                                        tmpPaths = removePathFromArray(tmpPaths, items.get(4));
                                        tmpPaths.add(new Path(graph.getNodeByName(items.get(0)), graph.getNodeByName(items.get(2)), new Properties(items.get(4), Integer.parseInt(items.get(5)), 0)));
                                    }
                                }
                            }
                            for (Path _path : tmpPaths) {
                                graph.addPath(_path);
                            }

                            nodesToEdit.removeAll();
                            for (Node _node : graph.getNodes()) {
                                nodesToEdit.addItem(_node.getProperties().getName());
                            }
                            fromNewPath.removeAll();
                            for (Node _node : graph.getNodes()) {
                                fromNewPath.addItem(_node.getProperties().getName());
                            }
                            toNewPath.removeAll();
                            for (Node _node : graph.getNodes()) {
                                toNewPath.addItem(_node.getProperties().getName());
                            }
                            pathsToEdit.removeAll();
                            for (Path _path : graph.getPaths()) {
                                pathsToEdit.addItem(_path.getProperties().getName());
                            }
                            fromPath.removeAll();
                            for (Node _node : graph.getNodes()) {
                                fromPath.addItem(_node.getProperties().getName());
                            }
                            toPath.removeAll();
                            for (Node _node : graph.getNodes()) {
                                toPath.addItem(_node.getProperties().getName());
                            }
                            nameNewNode.setText("nd" + (graph.getNodes().size() + 1));
                            nameNewPath.setText("pt" + (graph.getPaths().size() + 1));

                            if (nodesToEdit.getComponentCount() > 0) {
                                nodesToEdit.setSelectedIndex(0);
                            }
                            if (pathsToEdit.getComponentCount() > 0) {
                                pathsToEdit.setSelectedIndex(0);
                            }
                        } catch (IOException exp) {
                            exp.printStackTrace();
                        }
                        break;
                }
            
            }
        });
        panel5.add(loadCsv);
        panel5.add(printNodes);
        panel5.add(printPaths);
        panel5.add(drawGraph);
        frame.add(panel5);

        frame.setVisible(true);
    }
}