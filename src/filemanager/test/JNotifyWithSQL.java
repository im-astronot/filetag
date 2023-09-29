package filemanager.test;

import java.io.File;
import java.util.regex.Pattern;
import net.contentobjects.jnotify.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;

public class JNotifyWithSQL {

    public static void main(String[] args) throws Exception {
        
        // The system being monitored
        File[] roots = File.listRoots();

        // Regular expression to match the file names to ignore
        String regex = "(.*TMP.*|.*lock.*|.*idx.*|.*val.*|.*ldb.*|.*log.*|.*store.*|.*shm.*|.*etl.*|.*tmp)";
        Pattern pattern = Pattern.compile(regex);

        for (File root : roots) {
            String dir = root.getAbsolutePath();
            // Add the watch on the directory
            int mask = JNotify.FILE_CREATED | 
                       JNotify.FILE_DELETED | 
                       JNotify.FILE_MODIFIED | 
                       JNotify.FILE_RENAMED;
            boolean watchSubtree = true;
            int watchID = JNotify.addWatch(dir, mask, watchSubtree, new Listener(pattern));
        }

        // Wait for events every 500 milliseconds
        while (true) {
            Thread.sleep(1000);
        }
    }
}

class Listener implements JNotifyListener {
    private final Pattern pattern;
    private String previousFullFilePath;
    private long previousModificationTime;
    private long previousCreationTime;

    public Listener(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        String renamedFilePath = rootPath + newName;
        String oldFileName = new File(oldName).getName();
        String newFileName = new File(newName).getName();
        
        if (!pattern.matcher(newName).matches()) 
        {
            
            System.out.println("File renamed from: " + oldFileName + " to: " + newFileName + " at path: " + renamedFilePath);
            
            // SQL Query for File Renaming  
            try {
                // create a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:sqlite:filetaginfo.db");
            
                Statement statement = connection.createStatement();
                statement.executeUpdate("UPDATE file_tag SET file_name = '" + newFileName + "', file_path = '" + renamedFilePath + "' WHERE file_name = '" + oldFileName + "'");
            
                statement.close();
                connection.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            } 
        }
    }

    @Override
    public void fileModified(int wd, String rootPath, String name) {
        if (!pattern.matcher(name).matches()) 
        {
            String fullFilePath = rootPath + name;
            File file = new File(fullFilePath);
            previousFullFilePath = fullFilePath;
            previousModificationTime = file.lastModified();
            previousCreationTime = file.lastModified();
            
            System.out.println("File has been modified" + fullFilePath); 
           
        }
    }
    
    @Override
    public void fileDeleted(int wd, String rootPath, String name) {
        if (!pattern.matcher(name).matches()) 
        {
            String fullFilePath = rootPath + name;
            File file = new File(fullFilePath);
            previousFullFilePath = fullFilePath;
            previousModificationTime = file.lastModified();
            previousCreationTime = file.lastModified();
            
            String fileName = new File(name).getName();
            
            System.out.println("File deleted: " + fileName + " from " + fullFilePath);
            
            // SQL Query for File Deleting
            try {
                // create a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:sqlite:filetaginfo.db");
            
                Statement statement = connection.createStatement();
                statement.executeUpdate("DELETE from file_tag WHERE file_name = '" + fileName +"'");
            
                statement.close();
                connection.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        }
    }

    @Override
    public void fileCreated(int wd, String rootPath, String name) {
        String fullFilePath = rootPath + name;
        String fileName = new File(name).getName();
        
        if (!pattern.matcher(name).matches()) {
        System.out.println("The file: " + fileName + " has been moved to " + fullFilePath);
        
            // SQL Query for File Moving 
            try {
                // create a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:sqlite:filetaginfo.db");
            
                Statement statement = connection.createStatement();
                statement.executeUpdate("UPDATE file_tag SET file_path = '" + fullFilePath + "' WHERE file_name = '" + fileName + "'");
            
                statement.close();
                connection.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
