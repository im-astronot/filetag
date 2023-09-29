
package file.tagging.swing.poc;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Component;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;
import net.contentobjects.jnotify.JNotifyListener;

public class POC extends javax.swing.JFrame {

    public POC() {
        initComponents();

    }
    
    



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        fileTree2 = new jtree.FileTree();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(fileTree2);

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setAutoscrolls(true);
        jComboBox1.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(355, 355, 355)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(337, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws JNotifyException, InterruptedException {
        
        try {
            // Set FlatLaf look and feel
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POC().setVisible(true);
            }
        });
        
        
        // code monitors the whole system
        File[] roots = File.listRoots();

        for (File root : roots) {
            String dir = root.getAbsolutePath();
            // Add the watch on the directory
            int mask = JNotify.FILE_CREATED | 
                       JNotify.FILE_DELETED | 
                       JNotify.FILE_MODIFIED | 
                       JNotify.FILE_RENAMED;
            boolean watchSubtree = true;
            int watchID = JNotify.addWatch(dir, mask, watchSubtree, new Listener());
        }
        
        // monitors the mentioned directory, comment above code if you wanr to check this
        /*String dir = "C:";

        // Add the watch on the directory
        int mask = JNotify.FILE_CREATED  | 
                   JNotify.FILE_DELETED  | 
                   JNotify.FILE_MODIFIED | 
                   JNotify.FILE_RENAMED;
        boolean watchSubtree = true;
        int watchID = JNotify.addWatch(dir, mask, watchSubtree, new filewatcher.second.Listener());*/
        
        
        
        

        // Wait for events every 500 milliseconds
        while (true) {
            Thread.sleep(1000);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private jtree.FileTree fileTree2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

// JNotify Listener
class Listener implements JNotifyListener {
        private String previousFullFilePath;
        private long previousModificationTime;
        private long previousCreationTime;
        
   

    @Override
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        String renamedFilePath = rootPath + newName;
        String oldFileName = new File(oldName).getName();
        String newFileName = new File(newName).getName();
        

            
            System.out.println("File renamed from: " + oldFileName + " to: " + newFileName + " at path: " + renamedFilePath);
            
            
        
    }

    @Override
    public void fileModified(int wd, String rootPath, String name) {

            String fullFilePath = rootPath + name;
            File file = new File(fullFilePath);
            previousFullFilePath = fullFilePath;
            previousModificationTime = file.lastModified();
            previousCreationTime = file.lastModified();          
        
    }
    
    @Override
    public void fileDeleted(int wd, String rootPath, String name) {

            String fullFilePath = rootPath + name;
            File file = new File(fullFilePath);
            previousFullFilePath = fullFilePath;
            previousModificationTime = file.lastModified();
            previousCreationTime = file.lastModified();
            
            String fileName = new File(name).getName();
            
            System.out.println("File deleted: " + fileName + " from " + fullFilePath);
            
    }

    @Override
    public void fileCreated(int wd, String rootPath, String name) {
        String fullFilePath = rootPath + name;
        String fileName = new File(name).getName();

        System.out.println("The file: " + fileName + " has been moved to " + fullFilePath);

    }
}