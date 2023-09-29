package other.poc;

import java.io.File;
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
}

