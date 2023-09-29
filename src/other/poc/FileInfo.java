package other.poc;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class FileInfo {
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\Kanak RT\\Desktop\\Tag Folder";
        
        File directory = new File(directoryPath);

        File[] files = directory.listFiles();

        System.out.println("Filename\tPath\tType\tCreated\tModified\tSize (MB)");

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

            System.out.println(file.getName() + "\t" + filePath + "\t" + fileType + "\t" + createdDate + "\t" + modifiedDate + "\t" + fileSizeMB);
        }
    }
}
