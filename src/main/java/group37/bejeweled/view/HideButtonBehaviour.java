package main.java.group37.bejeweled.view;

import java.awt.Component;

import javax.swing.JButton;

public class HideButtonBehaviour implements HideComponentBehaviour {

  
  public void hide() {
    
  }

  @Override
  public void hide(Component comp) {
    if (comp instanceof JButton) {
      comp.setVisible(false);
    }   
  }
  
}
