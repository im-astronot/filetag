package filemanager.test;
/*
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FileInfoJTable {
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\Kanak RT\\Desktop\\Tag Folder";

        File directory = new File(directoryPath);

        File[] files = directory.listFiles();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Filename");
        model.addColumn("Path");
        model.addColumn("Type");
        model.addColumn("Created");
        model.addColumn("Modified");
        model.addColumn("Size (MB)");

        for (File file : files) {
            Path filePath = file.toPath();

            BasicFileAttributes attr = null;
            try {
                attr = java.nio.file.Files.readAttributes(filePath, BasicFileAttributes.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            double fileSizeMB = (double) file.length() / (1024 * 1024);

            String fileType = "";
            if (file.isDirectory()) {
                fileType = "Directory";
            } else {
                int i = file.getName().lastIndexOf('.');
                if (i >= 0) {
                    fileType = file.getName().substring(i + 1);
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            String createdDate = dateFormat.format(attr.creationTime().toMillis());
            String modifiedDate = dateFormat.format(attr.lastModifiedTime().toMillis());

            model.addRow(new Object[] { file.getName(), filePath, fileType, createdDate, modifiedDate, fileSizeMB });
        }

        JTable table = new JTable(model);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (row >= 0 && col >= 0) {
                        String filePath = (String) table.getValueAt(row, 1);
                        final File selectedFile = new File(filePath + "\\" + table.getValueAt(row, 0));

table.addMouseListener(new MouseAdapter() {
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON3) {
      JPopupMenu popup = new JPopupMenu();
      JMenuItem openItem = new JMenuItem("Open");
      JMenuItem renameItem = new JMenuItem("Rename");
      JMenuItem moveItem = new JMenuItem("Move");
      JMenuItem deleteItem = new JMenuItem("Delete");

      openItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          try {
            Desktop.getDesktop().open(selectedFile);
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }
      });

      renameItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          String newName = JOptionPane.showInputDialog(frame, "Enter new name:", "Rename", JOptionPane.QUESTION_MESSAGE);
          if (newName != null && !newName.isEmpty()) {
            File renamedFile = new File(selectedFile.getParentFile() + "\\" + newName);
            if (selectedFile.renameTo(renamedFile)) {
              model.setValueAt(newName, row, 0);
            } else {
              JOptionPane.showMessageDialog(frame, "Failed to rename file", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      });

      moveItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          int returnValue = fileChooser.showOpenDialog(frame);
          if (returnValue == JFileChooser.APPROVE_OPTION) {
            File destination = fileChooser.getSelectedFile();
            File movedFile = new File(destination.getPath() + "\\" + selectedFile.getName());
            if (selectedFile.renameTo(movedFile)) {
              model.removeRow(row);
            } else {
              JOptionPane.showMessageDialog(frame, "Failed to move file", "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
        }
      });

      deleteItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the file?");
            if (confirm == JOptionPane.YES_OPTION) {
            selectedFile.delete();
            model.removeRow(row);
            }
            }
            });

            popupMenu.add(openItem);
            popupMenu.add(renameItem);
            popupMenu.add(moveItem);
            popupMenu.add(deleteItem);

            table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
            int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
            table.changeSelection(row, column, false, false);
            popupMenu.show(table, e.getX(), e.getY());
}
}
});

        frame.add(new JScrollPane(table));
        frame.setTitle("File Information");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        }
}
        


                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
/*import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FileInfoJTable {
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\Kanak RT\\Desktop\\Tag Folder";

        File directory = new File(directoryPath);

        File[] files = directory.listFiles();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Filename");
        model.addColumn("Path");
        model.addColumn("Type");
        model.addColumn("Created");
        model.addColumn("Modified");
        model.addColumn("Size (MB)");

        for (File file : files) {
            Path filePath = file.toPath();

            BasicFileAttributes attr = null;
            try {
                attr = java.nio.file.Files.readAttributes(filePath, BasicFileAttributes.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            double fileSizeMB = (double) file.length() / (1024 * 1024);

            String fileType = "";
            if (file.isDirectory()) {
                fileType = "Directory";
            } else {
                int i = file.getName().lastIndexOf('.');
                if (i >= 0) {
                    fileType = file.getName().substring(i + 1);
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            String createdDate = dateFormat.format(attr.creationTime().toMillis());
            String modifiedDate = dateFormat.format(attr.lastModifiedTime().toMillis());

            model.addRow(new Object[] { file.getName(), filePath, fileType, createdDate, modifiedDate, fileSizeMB });
        }

        JTable table = new JTable(model);

        JFrame frame = new JFrame();

        frame.add(new JScrollPane(table));

        frame.setTitle("File Information");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}*/

