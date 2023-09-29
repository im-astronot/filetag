package filewatcher.third;

import java.io.File;
import java.util.regex.Pattern;
import net.contentobjects.jnotify.*;

public class FileWatcherWithRegex {

    public static void main(String[] args) throws Exception {
        
        // The system being monitored
        File[] roots = File.listRoots();

        // Regular expression to match the file names to ignore
        String regex = "(.*TMP.*|.*lock.*|.*idx.*|.*val.*|.*ldb.*|.*log.*|.*store.*|.*shm.*|.*etl.*|.*tmp*)";
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
            Thread.sleep(100);
        }
    }

    class previousFilePathMap {

        public previousFilePathMap() {
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
        if (!pattern.matcher(newName).matches()) 
        {
            System.out.println("File renamed from: " + (new File(oldName)).getName() + " to: " + (new File(newName)).getName() + " at path: " + rootPath + newName);
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
            //System.out.println("File has been modified" + fullFilePath); 
            //System.out.println("The file: " + (new File(name)).getName() + " has been moved to " + fullFilePath);
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
            System.out.println("File deleted: " + (new File(name)).getName() + " from " + fullFilePath);
        }
    }

    @Override
    public void fileCreated(int wd, String rootPath, String name) {
        String fullFilePath = rootPath + name;
        if (!pattern.matcher(name).matches()) {
        System.out.println("The file: " + (new File(name)).getName() + " has been moved to " + fullFilePath);
        }
    }

}