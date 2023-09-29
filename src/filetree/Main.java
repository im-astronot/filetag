package filetree;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
        
    }

    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container container = jframe.getContentPane();
        container.setLayout(new BorderLayout());
        FileTree fileTree = new FileTree();
        fileTree.setShowHiddenFiles(false);
        fileTree.setDeleteEnabled(true);
        JScrollPane scrollPane = new JScrollPane(fileTree);
        container.add(scrollPane, BorderLayout.CENTER);
        jframe.setSize(300, 450);
        jframe.setLocationByPlatform(true);
        jframe.setVisible(true);   
    }
    
}
