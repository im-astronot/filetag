package filewatcher.second;

import java.io.File;
import net.contentobjects.jnotify.*;

public class File_Watcher_IfElse {

    public static void main(String[] args) throws Exception {
        String previousFullFilePath = null;
        long previousModificationTime = 0;
        
        // The directory being monitored
        String dir = "C:";

        // Add the watch on the directory
        int mask = JNotify.FILE_CREATED  | 
                   JNotify.FILE_DELETED  | 
                   JNotify.FILE_MODIFIED | 
                   JNotify.FILE_RENAMED;
        boolean watchSubtree = true;
        int watchID = JNotify.addWatch(dir, mask, watchSubtree, new Listener());
        
        // The system being monitored
        /*File[] roots = File.listRoots();
        
        for (File root : roots) {
            String dir = root.getAbsolutePath();
            // Add the watch on the directory
            int mask = JNotify.FILE_CREATED  | 
                        JNotify.FILE_DELETED  | 
                        JNotify.FILE_MODIFIED | 
                        JNotify.FILE_RENAMED;
            boolean watchSubtree = true;
            int watchID = JNotify.addWatch(dir, mask, watchSubtree, new Listener());
        }*/

        // Wait for events every 500 milliseconds
        while (true){
            Thread.sleep(500);
        }
    }
}

class Listener implements JNotifyListener {
    private String previousFullFilePath;
    private long previousModificationTime;
    private long previousCreationTime;

    @Override
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        //System.out.println("File renamed from: " + oldName + " to: " + newName + " at path: " + rootPath);
        
        if (    !newName.contains("TMP") && !newName.contains("store_new") 
                && !newName.contains("log") && !newName.contains("LOG") 
                && !newName.endsWith(".tmp") && !newName.contains("TMP") 
                && !newName.contains("lock") && !newName.contains("idx") 
                && !newName.contains("val") && !newName.contains("ldb") 
                && !newName.contains("log") && !newName.contains("store") 
                && !newName.contains("shm") && !newName.contains("etl")
                && !newName.endsWith(".temp")
            ) {
            System.out.println("File renamed from: " + (new File(oldName)).getName() + " to: " + (new File(newName)).getName() + " at path: " + rootPath + newName);
        }
    }

    @Override
    public void fileModified(int wd, String rootPath, String name) {
        
        if (    !name.contains("TMP") && !name.contains("lock") 
                && !name.contains("idx") && !name.contains("val") 
                && !name.contains("ldb") && !name.contains("log") 
                && !name.contains("store") && !name.contains("shm") 
                && !name.contains("etl") && !name.endsWith("tmp")
            ) {
        String fullFilePath = name;
        File file = new File(fullFilePath);
        previousFullFilePath = fullFilePath;
        previousModificationTime = file.lastModified();
        previousCreationTime = file.lastModified();
        }
    }

    @Override
    public void fileDeleted(int wd, String rootPath, String name) {
        String fullFilePath = name;
        File file = new File(fullFilePath);
        previousFullFilePath = fullFilePath;
        previousModificationTime = file.lastModified();
        previousCreationTime = file.lastModified();
        
        if (    !name.contains("TMP") && !name.contains("lock") 
                && !name.contains("idx") && !name.contains("val") 
                && !name.contains("ldb") && !name.contains("log") 
                && !name.contains("store") && !name.contains("shm") 
                && !name.contains("etl") && !name.endsWith("tmp")
            ) {
        System.out.println("File deleted: " + (new File(name)).getName() + " from " + rootPath + "\\" + name);
        }
    }

    @Override
    public void fileCreated(int wd, String rootPath, String name) {
        String fullFilePath = rootPath + name;
        File file = new File(fullFilePath);
        long creationTime = file.lastModified();
        
        
       // if (previousFullFilePath != null && !previousFullFilePath.equals(fullFilePath) && previousCreationTime == previousModificationTime && previousCreationTime == creationTime
                /*&& !name.contains("TMP") && !name.contains("lock") 
                && !name.contains("idx") && !name.contains("val") 
                && !name.contains("ldb") && !name.contains("Tlog") 
                && !name.contains("store") && !name.contains("shm") 
                && !name.contains("etl") && !name.endsWith("tmp")*/
         //   ) {
         //   System.out.println("The file: " + (new File(name)).getName() + " has been moved to " + fullFilePath);   
      //  }
        
        if (previousFullFilePath != null && !previousFullFilePath.equals(fullFilePath) && previousCreationTime == previousModificationTime && previousCreationTime == creationTime) {
            System.out.println("The file: " + (new File(name)).getName() + " has been moved to " + fullFilePath);
        }
        
        previousFullFilePath = fullFilePath;
        previousCreationTime = creationTime;
    }
}
