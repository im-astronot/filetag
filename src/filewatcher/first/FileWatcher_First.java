package filewatcher.first;

import java.io.File;
import net.contentobjects.jnotify.*;

public class FileWatcher_First {

    public static void main(String[] args) throws Exception {
        
        // The directory to be monitored
        String dir = "C:\\Users\\Kanak RT\\Desktop\\Tag Folder";

        // Add the watch on the directory
        int mask = JNotify.FILE_CREATED  | 
                   JNotify.FILE_DELETED  | 
                   JNotify.FILE_MODIFIED | 
                   JNotify.FILE_RENAMED;
        boolean watchSubtree = true;
        int watchID = JNotify.addWatch(dir, mask, watchSubtree, new Listener());

        // Wait for events
        while (true){
            Thread.sleep(1000);
        }
    }
}

class Listener implements JNotifyListener {
    private String previousFileName;
    private String previousFilePath;

    @Override
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        System.out.println("File renamed from: " + (new File(oldName)).getName() + " to: " + (new File(newName)).getName() + " at path: " + rootPath + newName);
    }

    @Override
    public void fileModified(int wd, String rootPath, String name) {
        previousFileName = name;
        previousFilePath = rootPath;
    }

    @Override
    public void fileDeleted(int wd, String rootPath, String name) {
        System.out.println("File deleted: " + (new File(name)).getName() + " at path: " + rootPath + name);
    }

    @Override
    public void fileCreated(int wd, String rootPath, String name) {
        previousFileName = name;
        previousFilePath = rootPath;
        if (previousFileName != null && rootPath.equals(previousFilePath) && !previousFileName.equals(name)) {
            System.out.println("File moved from: " + previousFilePath + previousFileName + " to: " + rootPath + name);
        }
        
    }
}
