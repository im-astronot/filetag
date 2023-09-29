
package filemanager.test;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.event.*;
import javax.swing.*;

public class JPopupMenuwithSubMenuTest extends JFrame {
   private JPopupMenu popup;
   private JMenu subMenu;
   //private JMenu subsubMenu;
   
   public JPopupMenuwithSubMenuTest() {
      
      popup = new JPopupMenu();
      popup.add(new JMenuItem("Add Tag"));
      popup.add(new JMenuItem("Update Tag"));
      
      subMenu = new JMenu("Delete Tag");
      subMenu.add("Tag 1");
      subMenu.add("Tag 2");
      subMenu.add("Tag 3");
      subMenu.add("Tag 3");
      
      /*subMenu = new JMenu("Tag 1");
      subsubMenu.add("Item 1");
      subsubMenu.add("Item 2");
      subsubMenu.add("Item 3");
      subsubMenu.add("Item 4");*/
      
      popup.addSeparator();
      popup.add(subMenu);
      addMouseListener(new MouseAdapter() {
         public void mouseReleased(MouseEvent me) {
            showPopup(me);
         }
         
         public void showPopup(MouseEvent me) {
            if(me.isPopupTrigger()) {
                popup.show(me.getComponent(), me.getX(), me.getY());
                
            }
            
         }
         /*public void showsubPopup(MouseEvent me) {
            if(me.isPopupTrigger()) {
                subMenu.show(me.getComponent(), me.getX(), me.getY());
                
            }
            
         }*/
      }) ;
      
      setSize(400, 275);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
   }
   
   public static void main(String args[]) {
       int themeIndex = 7;
        try {
            if (themeIndex == 1){
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            else if(themeIndex ==2){
                UIManager.setLookAndFeel( new FlatLightLaf());
            }
            else if(themeIndex ==3){
                UIManager.setLookAndFeel( new FlatDarkLaf());
            }
            else if(themeIndex ==4){
                UIManager.setLookAndFeel( new FlatDarculaLaf());
            }
            else if(themeIndex ==5){
                UIManager.setLookAndFeel( new FlatIntelliJLaf());
            }
            else if(themeIndex ==6){
                UIManager.setLookAndFeel( new FlatMacLightLaf());
            }
            else if(themeIndex ==7){
                UIManager.setLookAndFeel( new FlatMacDarkLaf());
            }
            } 
        catch(Exception weTried) {
            }
      new JPopupMenuwithSubMenuTest();
      
   }
}
