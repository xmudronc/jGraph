package cz.mendelu;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class App 
{
    private static Graph graph = new Graph();

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
                //graph.getPathByName(selected).getProperties().setLength((Integer) lengthPath.getValue());
                String selName = namePath.getText();
                /*graph.getPathByName(selected).getProperties().setName(selName);
                graph.getPathByName(selected).setFrom(graph.getNodeByName((String) fromPath.getSelectedItem()));
                graph.getPathByName(selected).setTo(graph.getNodeByName((String) toPath.getSelectedItem()));*/

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
        panel5.add(printNodes);
        panel5.add(printPaths);
        frame.add(panel5);

        frame.setVisible(true);
    }
}
