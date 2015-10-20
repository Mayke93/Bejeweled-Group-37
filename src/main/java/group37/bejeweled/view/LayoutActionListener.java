package main.java.group37.bejeweled.view;

import main.java.group37.bejeweled.Launcher;
import main.java.group37.bejeweled.model.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LayoutActionListener implements ActionListener{

  public LayoutActionListener() { }
  
  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == Launcher.ls.back) {
      Logger.log("back clicked");
      handleBack();
    }
    if (event.getSource() == Launcher.startscreen.layOut) {
      Logger.log("Layout clicked");
      handleLayout();
    }
  }
  
  
  /**
   * This method handles the action taken when the quit game button is clicked.
   */
  public void handleBack() {
    Launcher.launcher.getContentPane().remove(Launcher.ls);
    Launcher.launcher.getContentPane().add(Launcher.startscreen);
    
    Launcher.launcher.getContentPane().validate();
    Launcher.launcher.getContentPane().repaint();
  }

  
  /**
   * This method handles the action taken when the layout button is clicked.
   */
  public void handleLayout() {
    Launcher.launcher.getContentPane().remove(Launcher.startscreen);

    Launcher.launcher.getContentPane().add(Launcher.ls);
    
    Launcher.launcher.getContentPane().validate();
    Launcher.launcher.getContentPane().repaint();
  }

}
