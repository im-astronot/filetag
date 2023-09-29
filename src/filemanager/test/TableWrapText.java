/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filemanager.test;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableWrapText extends JFrame {
    private DefaultTableModel tableModel = null;
 
    public TableWrapText() {
        String[][] data = new String[][]{
            new String[]{"TravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelv", "Go to somewhere else from where you live to enjoy spending money and getting lost in the esoterics."},
            new String[]{"HelpTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravel", "Do something to make the person get what he or she wants for pay or for free, it is upto you."},
            new String[]{"SleepTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravelTravel", "A state of temperary unconscious due to the nerves system is busy with something else."}
        };
        tableModel = new DefaultTableModel(data, new String[]{"Name", "Content"});

        JTable table = new JTable(tableModel){
            public TableCellRenderer getCellRenderer(int row, int col){
                    return new MyRenderer();
                    
            }

            public TableCellEditor getCellEditor(int row, int col){
                    return new MyEditor();
            }
        };
        //table.setRowHeight(row, table.getFont().getSize()*3);
//        table.setRowHeight(table.getFont().getSize()*3);
        JScrollPane sp = new JScrollPane(table);
        getContentPane().add(sp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setVisible(true);
    }
 
    private class MyRenderer extends JTextArea implements TableCellRenderer{
        public MyRenderer() {
            setOpaque(true);
            setLineWrap(true);
            setWrapStyleWord(true);
        }
     
        public Component getTableCellRendererComponent(JTable table,Object value,
            boolean isSelected, boolean hasFocus, int row,int column) {
         
            this.setText(value == null ? "" : value.toString());
            return this;
        }

    }

    private class MyEditor extends AbstractCellEditor implements TableCellEditor {
        JTextArea comp = new JTextArea();
        JTable table;
        int row;
     
        public MyEditor() {
            comp.setLineWrap(true);
            comp.setWrapStyleWord(true);
            comp.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    table.setRowHeight(row, (int) (comp.getPreferredSize().getHeight()));
                }
            });
            comp.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    super.keyTyped(e);
                    table.setRowHeight(row, (int) (comp.getPreferredSize().getHeight()));
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;

            comp.setText((String) value);
            comp.setFont(table.getFont());

            return comp;
        }

        public Object getCellEditorValue() {
            return comp.getText();
        }
    }
 
    public static void main(String[] args){
        new TableWrapText();
    }
}
