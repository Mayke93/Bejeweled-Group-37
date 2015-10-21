package main.java.group37.bejeweled.view;


import java.awt.Component;

import javax.swing.JLabel;

public class HideLabelBehaviour implements HideComponentBehaviour {
  
  /**
   * hides a label.
   */
  public void hide(Component comp) {
    if (comp instanceof JLabel) {
      comp.setVisible(false);
    }
  }
  

}
