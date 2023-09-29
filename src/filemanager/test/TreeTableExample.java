/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filemanager.test;

/**
 *
 * @author Kanak RT
 */
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.*;

public class TreeTableExample {
    public static void main(String[] args) {
        // Create the JTree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("A");
        root.add(parent1);
        parent1.add(new DefaultMutableTreeNode("1"));
        parent1.add(new DefaultMutableTreeNode("2"));
        parent1.add(new DefaultMutableTreeNode("3"));
        parent1.add(new DefaultMutableTreeNode("4"));
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("B");
        root.add(parent2);
        parent2.add(new DefaultMutableTreeNode("5"));
        parent2.add(new DefaultMutableTreeNode("6"));
        parent2.add(new DefaultMutableTreeNode("7"));
        parent2.add(new DefaultMutableTreeNode("8"));
        JTree tree = new JTree(root);

        // Create the JTable
        JTable table = new JTable();

        // Add a TreeSelectionListener to the JTree
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    // Get the children of the selected node
                    int childCount = selectedNode.getChildCount();
                    String[][] data = new String[childCount][1];
                    for (int i = 0; i < childCount; i++) {
                        DefaultMutableTreeNode child = (DefaultMutableTreeNode) selectedNode.getChildAt(i);
                        data[i][0] = child.toString();
                    }

                    // Update the JTable with the children of the selected node
                    table.setModel(new javax.swing.table.DefaultTableModel(data, new String[] { "Children" }));
                }
            }
        });

        // Create a JFrame to hold the JTree and JTable
        JFrame frame = new JFrame("TreeTableExample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(tree), BorderLayout.WEST);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}

